package com.cheny.gof.observer;

import java.util.Vector;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Observable {

    private Vector obs;
    private boolean changed = false;


    public Observable(){
        this.obs = new Vector();
    }

    public synchronized void addObserver(Observer o){
        if(null == o){
            throw new NullPointerException();
        }
        if(!obs.contains(o)){
            obs.add(o);
        }
    }

    public void removeObserver(Observer o){
        obs.removeElement(o);
    }

    public void notifyObservers(){
        notifyObservers(null);
    }

    public void notifyObservers(Object arg){
        Object[] local;
        synchronized (this){
            if(!changed){
                return;
            }
            local = obs.toArray();
            clearChanged();
        }
        for(int i = local.length - 1;i>=0;i--){
            ((Observer)local[i]).update(this,arg);
        }
    }

    public synchronized void clearChanged(){
        changed = false;
    }

    public synchronized void setChanged(boolean changed){
        this.changed = changed;
    }

    public synchronized boolean hasChanged(){
        return changed;
    }

    public synchronized int countObservers(){
        return obs.size();
    }
}

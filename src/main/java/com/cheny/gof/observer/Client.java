package com.cheny.gof.observer;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        Observable o = new Observable();
        o.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object args) {
                System.out.println("observer1 被触发!");
            }
        });
        o.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object args) {
                System.out.println("observer2 被触发!");
            }
        });
        o.notifyObservers();
        o.setChanged(true);
        o.notifyObservers();

    }
}

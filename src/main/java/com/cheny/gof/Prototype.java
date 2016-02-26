package com.cheny.gof;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Prototype implements Cloneable {

    @Override
    public Prototype clone() {
        Prototype p = null;
        try{
            p = (Prototype)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return p;
    }
}

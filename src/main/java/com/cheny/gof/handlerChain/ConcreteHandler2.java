package com.cheny.gof.handlerChain;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcreteHandler2 extends Handler {
    @Override
    public void handle(RequestType requestType) {
        if(requestType == RequestType.TYPE2){
            System.out.println("handler2 handling type2");
        }else{
            System.out.print("handle2 can't handle type:" + requestType.toString() + ",");
            if(null != this.getNextHandler()){
                System.out.println("so transform it to next handler :" + this.getNextHandler().getClass().getSimpleName());
                this.getNextHandler().handle(requestType);
            }else{
                System.out.println("but can not found a suit handler.");
            }
        }
    }
}

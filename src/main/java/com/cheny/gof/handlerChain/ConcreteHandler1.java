package com.cheny.gof.handlerChain;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcreteHandler1 extends Handler {
    @Override
    public void handle(RequestType requestType) {
        if(requestType == RequestType.TYPE1){
            System.out.println("handler1 handling type1");
        }else{
            System.out.print("handle1 can't handle type:" + requestType.toString() + ",");
            if(null != this.getNextHandler()){
                System.out.println("so transform it to next handler :" + this.getNextHandler().getClass().getSimpleName());
                this.getNextHandler().handle(requestType);
            }else{
                System.out.println("but can not found a suit handler.");
            }
        }
    }
}

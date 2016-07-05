package com.cheny;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Test {
    public void  test(){
        System.out.println("Hello");

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for(int i=0;i<stackTraceElements.length;i++){
            StackTraceElement ste = stackTraceElements[i];
            System.out.println(ste.getClassName() +" ,"+ste.getMethodName());

        }
    }

    public static void main(String[] args) {
        String fullClassName = "com.qianmi.pc.api.ms.doc.impl.MsApiDocQueryProviderImpl";
        String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1,fullClassName.length()-4);
        System.out.println(className);
    }
}

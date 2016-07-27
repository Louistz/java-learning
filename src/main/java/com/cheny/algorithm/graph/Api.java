package com.cheny.algorithm.graph;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Api {

    private String className;

    private String methodName;

    private long invoke = 0L;

    public Api(String className,String methodName,long invoke){
        this.className = className;
        this.methodName = methodName;
        this.invoke = invoke;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getInvoke() {
        return invoke;
    }

    public void setInvoke(long invoke) {
        this.invoke = invoke;
    }
}

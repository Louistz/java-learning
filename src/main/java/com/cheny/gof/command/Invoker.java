package com.cheny.gof.command;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Invoker {

    private Command command = null;

    public void setCommand(Command command){
        this.command = command;
    }

    public void runCommand(){
        command.execute();
    }
}

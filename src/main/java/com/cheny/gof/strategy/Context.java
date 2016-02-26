package com.cheny.gof.strategy;


/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Context {

    private IStrategy strategy;

    public void setStrategy(IStrategy strategy){
        this.strategy = strategy;
    }

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        strategy.algorithmInterface();
    }

    public static void main(String[] args) {
        IStrategy strategy1 = new ConcreteStrategy1();
        IStrategy strategy2 = new ConcreteStrategy2();
        IStrategy strategy3 = new ConcreteStrategy3();

        Context context = new Context(strategy1);
        context.execute();
        context.setStrategy(strategy2);
        context.execute();
        context.setStrategy(strategy3);
        context.execute();
    }
}

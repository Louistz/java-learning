package com.cheny.gof.component;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Leaf extends Component {

    @Override
    public void operation() {
        System.out.println("this is a leaf.");
    }
}

package com.cheny.gof.component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Composite extends Component {

    private List<Component> components = new ArrayList<>();

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return components.get(i);
    }

    @Override
    public void operation() {
        if(null != this.components && this.components.size() >0){
            for(Component c : this.components){
                c.operation();
            }
        }
    }
}

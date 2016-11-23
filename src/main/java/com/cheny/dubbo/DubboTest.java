package com.cheny.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DubboTest {

    public static void main(String[] args) {
        ApplicationConfig app = new ApplicationConfig("test-dubbo-app");
        RegistryConfig registry = new RegistryConfig("test-dubbo-registry");
        registry.setAddress("172.19.65.33:2181");

    }
}

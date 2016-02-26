package com.cheny.zkeeper;

/**
 * <p>zookeeper服务</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public enum ZookeeperServer {


    SERVER1("localhost",2181),
    SERVER2("localhost",2182),
    SERVER3("localhost",2183);

    private ZookeeperServer(String host,int port){
        this.host = host;
        this.port = port;
    }

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toServerString(){
        return this.host+":"+this.port;
    }
}

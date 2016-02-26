package com.cheny.zkeeper;

/**
 * <p>zookeeper目录(默认分配16个node根节点，分作不同的用途)</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public enum ZookeeperNode {
    NODE0("/node0"),
    NODE1("/node1"),
    NODE2("/node2"),
    NODE3("/node3"),
    NODE4("/node4"),
    NODE5("/node5"),
    NODE6("/node6"),
    NODE7("/node7"),
    NODE8("/node8"),
    NODE9("/node9"),
    NODE10("/node10"),
    NODE11("/node11"),
    NODE12("/node12"),
    NODE13("/node13"),
    NODE14("/node14"),
    NODE15("/node15");

    private String nodePath;
    private ZookeeperNode(String nodePath){
        this.nodePath = nodePath;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }
}

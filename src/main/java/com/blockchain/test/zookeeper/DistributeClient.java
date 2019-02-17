package com.blockchain.test.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {
    private ZooKeeper zkClient;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient client = new DistributeClient();
        //1.连接zookeeper集群
        client.getConnect();
        //2.注册监听
        client.getChildren();
        //3.业务代码
        client.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {
        List<String> childrens = zkClient.getChildren("/servers", true);
        ArrayList<String> hosts = new ArrayList<>();
        for (String children:childrens) {
            byte[] data = zkClient.getData("/servers/"+children, false, null);
            hosts.add(new String(data));
        }
        //将所有的主机名称打印
        System.out.println(hosts);
    }


    private String connectString = "192.168.199.144:2181,192.168.199.176:2181,192.168.199.101:2181";

    private int sessionTimeOut = 2000;

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

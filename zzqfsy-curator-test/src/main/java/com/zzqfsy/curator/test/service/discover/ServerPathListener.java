package com.zzqfsy.curator.test.service.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServerPathListener {
    private Logger logger = LoggerFactory.getLogger(ServerPathListener.class);
    private static String ZK_PATH = "/service/tomcat";

    private CuratorFramework client;
    private PathChildrenCache cache;

    public ServerPathListener(CuratorFramework client) {
        this.client = client;
    }

    public void start(){
        watchServerPath();
    }

    public void close(){
        if (cache != null)
            try {
                cache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void watchServerPath(){
        cache = new PathChildrenCache(client, ZK_PATH,true);
        cache.getListenable().addListener((client,event)->{
            switch (event.getType()) {
                case CHILD_ADDED:
                    //deal server add
                    System.out.println("child added");
                    ChildData childData = event.getData();
                    System.out.println(new String(childData.getData()));
                    System.out.println("---------------------");
                    break;
                case CHILD_REMOVED:
                    //deal server remove
                    System.out.println("child remove");
                    ChildData childData1 = event.getData();
                    System.out.println(new String(childData1.getData()));
                    System.out.println("---------------------");
                    break;
                default:
                    break;
            }
        });
        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

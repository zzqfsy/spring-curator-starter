package com.zzqfsy.curator.test.service.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NodeListener {
    private Logger logger = LoggerFactory.getLogger(NodeListener.class);
    private static String ZK_PATH = "/config";

    private CuratorFramework client;

    public NodeListener(CuratorFramework client) {
        this.client = client;
    }

    public void start(){
        watchNodeChangeDataEvent();

        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String message, Throwable e) {
                logger.info("CuratorFramework unhandledError: {}", message);
            }
        });
    }

    public void close(){
        //remove listener
    }

    public void watchNodeChangeDataEvent(){
        //final NodeCache cache = new NodeCache(client, ZK_PATH);
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                logger.info("CuratorFramework state changed: {}", newState);
                if(newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED){
                    final NodeCache cache = new NodeCache(client, ZK_PATH);
                    cache.getListenable().addListener(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            //进行数据变更的相关操作
                            byte[] data = cache.getCurrentData().getData();
                            if (data != null) {
                                logger.info("changed data: " + new String(data));
                            }
                        }
                    });
                    try {
                        cache.start(true);
                    } catch (Exception e) {
                        logger.error("Start NodeCache error for path: {}, error info: {}", ZK_PATH, e.getMessage());
                    }
                }
            }
        });
    }
}

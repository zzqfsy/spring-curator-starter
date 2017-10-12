package com.zzqfsy.curator.test.config;

import com.zzqfsy.curator.test.service.config.NodeListener;
import com.zzqfsy.curator.test.service.discover.Server1;
import com.zzqfsy.curator.test.service.discover.Server2;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(NodeListener.class)
    public NodeListener nodeListener(@Autowired CuratorFramework client){
        return new NodeListener(client);
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(NodeListener.class)
    public Server1 server1(@Autowired CuratorFramework client){
        return new Server1(client);
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(NodeListener.class)
    public Server2 server2(@Autowired CuratorFramework client){
        return new Server2(client);
    }
}

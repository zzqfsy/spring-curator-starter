package com.zzqfsy.curator.test.config;

import com.zzqfsy.curator.test.service.config.NodeListener;
import com.zzqfsy.curator.test.service.discover.Client1;
import com.zzqfsy.curator.test.service.discover.Server1;
import com.zzqfsy.curator.test.service.discover.Server2;
import com.zzqfsy.curator.test.service.discover.ServerPathListener;
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
    @ConditionalOnMissingBean(Server1.class)
    public Server1 server1(@Autowired CuratorFramework client){
        return new Server1(client);
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(Server2.class)
    public Server2 server2(@Autowired CuratorFramework client){
        return new Server2(client);
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(Client1.class)
    public Client1 client1(@Autowired CuratorFramework client){
        return new Client1(client);
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean(ServerPathListener.class)
    public ServerPathListener serverPathListener(@Autowired CuratorFramework client){
        return new ServerPathListener(client);
    }
}

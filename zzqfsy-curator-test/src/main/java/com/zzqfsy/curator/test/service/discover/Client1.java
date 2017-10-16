package com.zzqfsy.curator.test.service.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.strategies.RandomStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

public class Client1 {
    private Logger logger = LoggerFactory.getLogger(Server1.class);
    private static final String serviceName = "tomcat";

    private ServiceDiscovery<ServiceDetail> serviceDiscovery;
    private ServiceProvider<ServiceDetail>  provider;
    private CuratorFramework client;

    public Client1(CuratorFramework client) {
        this.client = client;
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(client)
                .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                .build();

        provider = serviceDiscovery.serviceProviderBuilder()
                .serviceName(serviceName)
                .providerStrategy(new RandomStrategy<ServiceDetail>())
                .build();
    }

    public void start(){
        try {
            if (serviceDiscovery != null) {
                serviceDiscovery.start();
                provider.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            if (serviceDiscovery != null) {
                serviceDiscovery.close();
                provider.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getServers(){
        Collection<ServiceInstance<ServiceDetail>> services = null;
        try {
            services = serviceDiscovery.queryForInstances(serviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(ServiceInstance<ServiceDetail> service : services) {
            System.out.println(service.getPayload());
            System.out.println(service.getAddress() + "\t" + service.getPort());
            System.out.println("---------------------");
        }
        return services;
    }

    public Object getInstance(){
        try {
            return provider.getInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public Object getAllInstance(){
        try {
            return provider.getAllInstances();
        } catch (Exception e) {
            return null;
        }
    }
}

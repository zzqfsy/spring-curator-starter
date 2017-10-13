package com.zzqfsy.curator.test.service.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

public class Client1 {
    private Logger logger = LoggerFactory.getLogger(Server1.class);

    private ServiceDiscovery<ServiceDetail> serviceDiscovery;
    private CuratorFramework client;

    public Client1(CuratorFramework client) {
        this.client = client;
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(client)
                .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                .build();
    }

    public void start(){
        try {
            if (serviceDiscovery != null)
                serviceDiscovery.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            if (serviceDiscovery != null)
                serviceDiscovery.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getServers(){
        Collection<ServiceInstance<ServiceDetail>> services = null;
        try {
            services = serviceDiscovery.queryForInstances("tomcat");
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
}

package com.zzqfsy.curator.test.service.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Server2 {
    private Logger logger = LoggerFactory.getLogger(Server2.class);

    private ServiceDiscovery<ServiceDetail> serviceDiscovery;
    private CuratorFramework client;

    public Server2(CuratorFramework client) {
        try {
            ServiceInstanceBuilder<ServiceDetail> sib = ServiceInstance.builder();
            sib.address("192.168.1.100");
            sib.port(8866);
            sib.name("tomcat");
            sib.payload(new ServiceDetail("主站web程序", 2));

            ServiceInstance<ServiceDetail> instance = sib.build();

            serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                    .client(client)
                    .serializer(new JsonInstanceSerializer<ServiceDetail>(ServiceDetail.class))
                    .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                    .build();
            //服务注册
            serviceDiscovery.registerService(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            serviceDiscovery.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            serviceDiscovery.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

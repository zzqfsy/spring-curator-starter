package com.zzqfsy.curator.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetACLBuilder;
import org.apache.zookeeper.data.ACL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudTest {

    @Autowired
    CuratorFramework client;

    private static String ZK_PATH = "/crud";
    @Test
    public void crudTest() throws Exception {
        // Client API test
        print("ls", "/");
        print(client.getChildren().forPath("/"));
        GetACLBuilder getACLBuilder = client.getACL();
        List<ACL> acls = getACLBuilder.forPath("/");

        // 2.1 Create node
        String data1 = "data1";
        print("create", ZK_PATH, data1);
        client.create()
                .creatingParentContainersIfNeeded()
                .withACL(acls, true)
                .forPath(ZK_PATH, data1.getBytes());

        // 2.2 Get node and data
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.3 Modify data
        String data2 = "data2";
        print("set", ZK_PATH, data2);
        client.setData().forPath(ZK_PATH, data2.getBytes());
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.4 Remove node
        print("delete", ZK_PATH);
        client.delete().forPath(ZK_PATH);
        print("ls", "/");
        print(client.getChildren().forPath("/"));
        int a = 123;
    }

    private static void print(String... cmds) {
        StringBuilder text = new StringBuilder("$ ");
        for (String cmd : cmds) {
            text.append(cmd).append(" ");
        }
        System.out.println(text.toString());
    }

    private static void print(Object result) {
        System.out.println(
                result instanceof byte[]
                        ? new String((byte[]) result)
                        : result);
    }
}

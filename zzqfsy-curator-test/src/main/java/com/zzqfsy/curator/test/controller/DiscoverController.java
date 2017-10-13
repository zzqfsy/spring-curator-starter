package com.zzqfsy.curator.test.controller;

import com.zzqfsy.curator.test.service.discover.Client1;
import com.zzqfsy.curator.test.service.discover.Server1;
import com.zzqfsy.curator.test.service.discover.Server2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/discover")
public class DiscoverController {

    @Autowired
    private Server1 server1;
    @Autowired
    private Server2 server2;
    @Autowired
    private Client1 client1;

    @RequestMapping(path = "/servers")
    @ResponseBody
    public Object getServers(){
        return client1.getServers();
    }

    @RequestMapping(path = "/servers/start")
    public String startServer(@RequestParam("serverName") String serverName){
        if (StringUtils.isEmpty(serverName)) return "fail";

        if ("server1".equals(serverName))
            server1.start();
        else if ("server2".equals(serverName))
            server2.start();

        return "success";
    }

    @RequestMapping(path = "/servers/close")
    public String closeServer(@RequestParam("serverName") String serverName){
        if (StringUtils.isEmpty(serverName)) return "fail";

        if ("server1".equals(serverName))
            server1.close();
        else if ("server2".equals(serverName))
            server2.close();

        return "success";
    }
}

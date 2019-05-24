package com.microservicestest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.*;

import com.microservicestest.exceptions.NotFoundException;
import com.microservicestest.services.RemoteService;

@SpringBootApplication
@ComponentScan("com.microservicestest")
@RestController
public class APIServer {

    @Autowired
    private RemoteService remoteService;

    public static void main(String[] args) {
        SpringApplication.run(APIServer.class, args);
    }

    @ResponseBody
    @RequestMapping(value = "/servidor-remoto/{idList}", method = { RequestMethod.GET })
    public String getFromService(@PathVariable(value = "idList") String id) throws NotFoundException {
        try {
            return this.remoteService.getElementById(id);
        } catch (Exception ex) {
            throw new NotFoundException("No se ha encontrado el elemento con id " + id);
        }
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

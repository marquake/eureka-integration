package com.remoteservice.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.*;

import java.util.*;

import javax.annotation.PostConstruct;

import com.remoteservice.exceptions.NotFoundException;

@SpringBootApplication
@ComponentScan("com.remoteservice")
@RestController
public class RemoteServer {

    private Map<String, String> resources=new HashMap<>();

    @PostConstruct
    void init(){
        resources.put("abc", "Recurso remoto abc");
        resources.put("123", "Lo mismo pero con números");
        resources.put("recurso", "Se me acaban las ideas para identificadores");
        resources.put("microservicio", "Esto mismo.");
        resources.put("random", String.valueOf( Math.random()*100 ));
    }

    public static void main(String[] args) {
        SpringApplication.run(RemoteServer.class, args);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = { RequestMethod.POST })
    public String getFromService(@RequestParam(value = "idList") String id) throws NotFoundException {
        if(resources.containsKey(id)){
            return resources.get(id);
        }
        throw new NotFoundException("No existe el recurso con ese identificador");
    }

}

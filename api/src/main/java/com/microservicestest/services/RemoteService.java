package com.microservicestest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;;

@Service
public class RemoteService {
    private static final String URL_SERVICE="http://remote-service-1";
    
    @Autowired
    private RestTemplate restTemplate;

    public String getElementById(String id){

        String result="";

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);   
        map.add("idList", id);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        try{
            ResponseEntity<String> response=restTemplate.exchange(URL_SERVICE, HttpMethod.POST, request , String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                result=response.getBody();
            }

        }catch(HttpClientErrorException ex){ //Cuando se recibe algún error 4xx
            result=ex.getResponseBodyAsString();
            ex.printStackTrace();
        }catch(HttpServerErrorException ex){ //Cuando se recibe algún error 5xx
            result=ex.getResponseBodyAsString();
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return result;
    }
};

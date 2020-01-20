package com.demo.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController 
public class HystrixController{

    @GetMapping("/data")    
    @HystrixCommand(fallbackMethod="getBackData")
    /*
    * A fallback method should be defined in the same class where is HystrixCommand.
     * Also a fallback method should have same signature to a method which was invoked as hystrix command.
    */
    public String getData(@RequestParam(defaultValue="2") String id) {

        if(id.equals("1")){
            throw new RuntimeException("Some error");
        }
        return "Original data";
    }


    @GetMapping("/data/timeout")    
    @HystrixCommand(fallbackMethod="getBackData",commandProperties={
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")  //Fallback if current method takes more than 1 sec
        //ref:https://github.com/Netflix/Hystrix/wiki/Configuration
    })
    /*
    * A fallback method should be defined in the same class where is HystrixCommand.
     * Also a fallback method should have same signature to a method which was invoked as hystrix command.
    */
    public String getDataWithTimeout(@RequestParam(defaultValue="2") String id)  throws InterruptedException{

        Thread.sleep(3000);

        return "Original data";
    }


    @GetMapping("/data/backup")    
    public String getBackData(@RequestParam(defaultValue="2") String id){

        return "From backup data store";
    }

}
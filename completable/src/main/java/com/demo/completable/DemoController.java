package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CustomService startup;
    
    @GetMapping(path = "/data")
    public @ResponseBody CompletableFuture<Data> getData() throws InterruptedException, ExecutionException {


        LOGGER.info( "Start- T1:"+ Thread.currentThread().getId());


        //Gets called on another thread. Start and End time difference would be 5 though each thread waits for 5 seconds, shows they are executed in parallel, also 
        //thread id shows the different thread ids.
        CompletableFuture<Data>  func3= startup.getData();
        CompletableFuture<Data> func= startup.getData();
        CompletableFuture<Data> func2= startup.getData();

         CompletableFuture.allOf( func,func2,func3).join();

         LOGGER.info( "End- T1:"+ Thread.currentThread().getId());


         return func;
    }

}
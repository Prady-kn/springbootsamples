package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomService.class);


    @PostConstruct
    public void init() throws InterruptedException, ExecutionException {
     // init code goes here
   //  Data r=getData().get();

  }


  @Async
  public CompletableFuture<Data> getData() throws InterruptedException {

    LOGGER.info( "T3:"+ Thread.currentThread().getId());

      Data rep=new Data();
      rep.setCounter(1);
      Thread.sleep(5000);


      return CompletableFuture.completedFuture(rep);

  }


}
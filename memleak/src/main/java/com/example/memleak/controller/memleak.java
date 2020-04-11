package com.example.memleak.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class memleak{

static ArrayList<String> mylist=new ArrayList<String>();

    @GetMapping(path="/memleak/hw")
    public String Hello(@RequestParam(value="msg") String msg){
      
        for(int i=0;i<10;i++){
            msg+=msg;
            System.out.println("i:" + i +" Length: " +msg.length());
        }

        mylist.add(msg);

        return "Success,  count:" + mylist.size() ;
    }

}
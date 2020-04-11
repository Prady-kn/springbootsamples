package com.example.rxjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rx.Observable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger LOGGER= LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		AtomicInteger counter=new AtomicInteger();
		ArrayList<Integer> results=new ArrayList<>();

		Observable.range(1, 5)
		.map(i -> i *100)
		.doOnNext(i -> {
			counter.addAndGet(i);
			LOGGER.info("Processing {} on thread {}", i,Thread.currentThread().getName());
		})
		.collect(() ->  results,ArrayList::add)	
		//.last();
		.subscribe(a -> {
			LOGGER.info("Val:{}",a);
		});


		//Group by sample
		Observable.range(0, 10)
		.groupBy(i -> (i %2)==0 ? "EVEN" : "ODD")
		.flatMap(grp-> grp.reduce( (a,b)-> a + b).map(sum-> "Group " + grp.getKey() + " sum is :"+sum ))
		.subscribe(System.out::println);




		LOGGER.info("counter:{}",counter);

	}

}

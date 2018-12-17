package com.dc.security.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfiguration {

	@Bean("consumerThreadPool")
	public ThreadPoolExecutor getThreadPoooled(){
		ThreadPoolExecutor exe = new ThreadPoolExecutor(10, 20, 0L ,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
    	return exe;
	}
	
	@Bean("consumerService")
	public ExecutorService getExecutorService(){
		ExecutorService pool = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(10), new ThreadFactory() {
		          AtomicInteger count = new AtomicInteger(0);
					
					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						return new Thread(r,"ThreadPoooled_Test_"+count.getAndIncrement());
					}
				}, new ThreadPoolExecutor.AbortPolicy());
		return pool;
	}
}

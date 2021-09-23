package com.example.demo.asyncTest.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class AsyncService {

  @Autowired DatService datService;

  public String getResult() throws InterruptedException {
    log.info("getResult Method, Thread Name: " + Thread.currentThread().getName());
    return first() + "  " + second();
  }

  private String first() throws InterruptedException {
    log.info("first Method, Thread Name: " + Thread.currentThread().getName());
    Thread.sleep(1000L);
    return "Hello";
  }

  private String second() throws InterruptedException {
    log.info("second Method, Thread Name: " + Thread.currentThread().getName());
    Thread.sleep(2000L);
    return "World";
  }

  public String getResultAsync() throws ExecutionException, InterruptedException {
    log.info("getResultAsync Method, Thread Name: " + Thread.currentThread().getName());
    //    var firstString = datService.getFirstString();
    //    var secondString = datService.getSecondString();

    var firstString = getFirstString();
    var secondString = getSecondString();

    CompletableFuture.allOf(firstString, secondString).join();
    return firstString.get() + " " + secondString.get();
  }

  private CompletableFuture<String> getFirstString() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return first();
          } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
          }
        },
        executor());
  }

  private CompletableFuture<String> getSecondString() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return second();
          } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
          }
        },
        executor());
  }

  private Executor executor() {
    var executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(5);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("MyThread-");
    executor.initialize();
    return executor;
  }
}

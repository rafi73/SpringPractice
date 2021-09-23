package com.example.demo.asyncTest.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class DatService {
  // @Async
  public CompletableFuture<String> getFirstString() {
    log.info("getFirstString Method, Thread Name: " + Thread.currentThread().getName());

    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return CompletableFuture.completedFuture("Hello");
  }

  // @Async
  public CompletableFuture<String> getSecondString() {
    log.info("getSecondString Method, Thread Name: " + Thread.currentThread().getName());

    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return CompletableFuture.completedFuture("World");
  }
}

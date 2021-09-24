package com.example.demo.asyncTest.testActual.service.impl;

import com.example.demo.asyncTest.demilib.MysteriousService;
import com.example.demo.asyncTest.testActual.service.MysteriousServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class MysteriousServiceWrapperImpl implements MysteriousServiceWrapper {
  @Autowired MysteriousService mysteriousService;

  @Override
  @Async("our-pool")
  public CompletableFuture<String> firstMessage() throws InterruptedException {
    var result = mysteriousService.firstMessage();
    return CompletableFuture.completedFuture(result);
  }

  @Override
  @Async("our-pool")
  public CompletableFuture<String> secondMessage() throws InterruptedException {
    var result = mysteriousService.secondMessage();
    return CompletableFuture.completedFuture(result);
  }

  @Override
  @Async("our-pool")
  public CompletableFuture<String> thirdMessage() throws InterruptedException {
    var result = mysteriousService.thirdMessage();
    return CompletableFuture.completedFuture(result);
  }
}

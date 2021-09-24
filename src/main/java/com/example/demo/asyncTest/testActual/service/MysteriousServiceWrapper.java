package com.example.demo.asyncTest.testActual.service;

import java.util.concurrent.CompletableFuture;

public interface MysteriousServiceWrapper {
  CompletableFuture<String> firstMessage() throws InterruptedException;

  CompletableFuture<String> secondMessage() throws InterruptedException;

  CompletableFuture<String> thirdMessage() throws InterruptedException;
}

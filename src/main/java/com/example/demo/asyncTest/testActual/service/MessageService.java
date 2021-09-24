package com.example.demo.asyncTest.testActual.service;

import java.util.concurrent.ExecutionException;

public interface MessageService {
  String getMessage() throws InterruptedException;

  String getMessageAsync() throws InterruptedException, ExecutionException;
}

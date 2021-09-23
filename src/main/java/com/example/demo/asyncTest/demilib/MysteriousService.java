package com.example.demo.asyncTest.demilib;

public interface MysteriousService {
  String firstMessage() throws InterruptedException;

  String secondMessage() throws InterruptedException;

  String thirdMessage() throws InterruptedException;
}

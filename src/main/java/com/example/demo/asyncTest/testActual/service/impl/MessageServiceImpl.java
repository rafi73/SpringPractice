package com.example.demo.asyncTest.testActual.service.impl;

import com.example.demo.asyncTest.demilib.MysteriousService;
import com.example.demo.asyncTest.testActual.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
  @Autowired MysteriousService mysteriousService;

  @Override
  public String getMessage() throws InterruptedException {
    log.info("getMessage method, thread name: {}", Thread.currentThread().getName());
    var firstMsg = mysteriousService.firstMessage();
    var secondMsg = mysteriousService.secondMessage();
    var thirdMsg = mysteriousService.thirdMessage();
    return firstMsg + secondMsg + thirdMsg;
  }
}

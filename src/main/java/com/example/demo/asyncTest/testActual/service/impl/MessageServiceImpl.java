package com.example.demo.asyncTest.testActual.service.impl;

import com.example.demo.asyncTest.demilib.MysteriousService;
import com.example.demo.asyncTest.testActual.service.MessageService;
import com.example.demo.asyncTest.testActual.service.MysteriousServiceWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.function.Function;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
  @Autowired MysteriousService mysteriousService;
  @Autowired Executor executor;
  @Autowired MysteriousServiceWrapper mysteriousServiceWrapper;

  @Override
  public String getMessage() throws InterruptedException {
    log.info("getMessage method, thread name: {}", Thread.currentThread().getName());
    var firstMsg = mysteriousService.firstMessage(); // db call
    var secondMsg = mysteriousService.secondMessage(); // api call
    var thirdMsg = mysteriousService.thirdMessage(); // service call
    return firstMsg + secondMsg + thirdMsg;
  }

  @Override
  public String getMessageAsync() throws InterruptedException, ExecutionException {
    // nio-8080-exec
    log.info("getMessageAsync method, thread name: {}", Thread.currentThread().getName());
    var firstMsgAsync = mysteriousServiceWrapper.firstMessage();
    var secondMsgAsync = mysteriousServiceWrapper.secondMessage();
    var thirdMsgAsync = mysteriousServiceWrapper.thirdMessage();

    CompletableFuture.allOf(firstMsgAsync, secondMsgAsync, thirdMsgAsync)
        .join(); // nameio-8080-exec, 3, 2, 2

    var firstMsg = firstMsgAsync.get(); // nio-8080-exec
    var secondMsg = secondMsgAsync.get(); // nio-8080-exec
    var thirdMsg = thirdMsgAsync.get(); // nio-8080-exec

    return firstMsg + secondMsg + thirdMsg;
  }
}

package com.example.demo.asyncTest.demilib.impl;

import com.example.demo.asyncTest.demilib.MysteriousService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
@Slf4j
public class MysteriousServiceImpl implements MysteriousService {
  @Override
  public String firstMessage() throws InterruptedException {
    log.info("firstMessage method, thread name: {}", Thread.currentThread().getName());
    Thread.sleep(2000L);
    return "Hello ";
  }

  @Override
  public String secondMessage() throws InterruptedException {
    log.info("secondMessage method, thread name: {}", Thread.currentThread().getName());
    Thread.sleep(2000L);
    return "World. ";
  }

  @Override
  public String thirdMessage() throws InterruptedException {
    log.info("thirdMessage method, thread name: {}", Thread.currentThread().getName());
    Thread.sleep(2000L);
    return "Today is : " + Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();
  }
}

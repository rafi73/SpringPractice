package com.example.demo.asyncTest.testActual;

import com.example.demo.asyncTest.testActual.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/message")
@Slf4j
public class AsyncTestController {
  @Autowired MessageService messageService;

  @GetMapping("/normal")
  public Object get() throws InterruptedException {
    // MDC.put();
    Instant start = Instant.now();
    var result = messageService.getMessage();
    Instant end = Instant.now();
    log.info("Request processing time: " + Duration.between(start, end).getSeconds());
    // MDC.clear();
    return result;
  }

  @GetMapping("/async")
  public Object getAsync() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();
    var result = messageService.getMessageAsync();
    Instant end = Instant.now();
    log.info("Request processing time: " + Duration.between(start, end).getSeconds());
    return result;
  }
}

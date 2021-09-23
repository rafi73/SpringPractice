package com.example.demo.asyncTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/performance")
@Slf4j
public class AsyncController {
  @Autowired AsyncService asyncService;

  @GetMapping("/async")
  public Object test() throws ExecutionException, InterruptedException {
    var startTime = Instant.now();
    var result = asyncService.getResultAsync();
    var endTime = Instant.now();
    log.info("Request Completion Time:" + Duration.between(startTime, endTime).toMillis());
    return result;
  }

  @GetMapping("/sync")
  public Object testSync() throws InterruptedException {
    var startTime = Instant.now();
    var result = asyncService.getResult();
    var endTime = Instant.now();
    log.info("Request Completion Time:" + Duration.between(startTime, endTime).toMillis());
    return result;
  }
}

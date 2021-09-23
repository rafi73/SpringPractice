package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
  Integer sharedVariable = 50;

  @PostMapping("/time")
  public ResponseEntity<ResponseDto> getTime(
      @RequestHeader(value = "AccessKey") String accesskey, @RequestBody RequestForm form) {
    log.info("Access key: {} , Data: {}", accesskey, form);
    ResponseDto responseDto = new ResponseDto();
    responseDto.eventId = new Random().nextInt();
    responseDto.message = "Hello wolrd";
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/def")
  public ResponseEntity<String> get() throws InterruptedException {
    // shared variable update
    Thread.sleep(500);
    synchronized (this) {
      sharedVariable++;
    }
    return new ResponseEntity<>("Hello World, hash=" + this.hashCode(), HttpStatus.OK);
  }

  @GetMapping("/abc")
  public ResponseEntity<String> getT() {
    // read shared variable

    var ret = "Abc =" + sharedVariable + " Controller hash: " + this.hashCode();
    return new ResponseEntity<>(ret, HttpStatus.OK);
  }
}

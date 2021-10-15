package com.example.demo.expections;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
  private List<ErrorBody> errors;
  private ZonedDateTime timestamp;

  public ErrorResponse(ErrorBody error) {
    errors = new ArrayList<>(List.of(error));
    timestamp = ZonedDateTime.now();
  }

  public ErrorResponse(List<ErrorBody> errors) {
    this.errors = errors;
    timestamp = ZonedDateTime.now();
  }
}

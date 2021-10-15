package com.example.demo.expections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBody {
  private String code;
  private String message;
  private String info;
}

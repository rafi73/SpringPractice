package com.example.demo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestForm {
  private Long serviceGuId;
  private String serviceReportingTime;
  private Boolean healthStatus;
  private String errorReason;
}

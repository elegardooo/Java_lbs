package com.lagodich.textqrconvertor.service;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestCounterService {

  private RequestCounterService() {}
  private static AtomicInteger requestCount = new AtomicInteger(0);

  public static synchronized void incrementRequestCount() {
    requestCount.incrementAndGet();
  }

  public static synchronized void getRequestCount() {
    log.info("Total request count: " + requestCount.get());
  }

}

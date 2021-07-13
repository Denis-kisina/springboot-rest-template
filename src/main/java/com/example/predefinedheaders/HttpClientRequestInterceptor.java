package com.example.predefinedheaders;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Configuration
@RequiredArgsConstructor
public class HttpClientRequestInterceptor implements ClientHttpRequestInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientRequestInterceptor.class);

  private final String headerName;
  private final String headerValue;

  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

    httpRequest.getHeaders().set(headerName, headerValue);
    logRequestDetails(httpRequest);
    return clientHttpRequestExecution.execute(httpRequest, bytes);
  }

  private void logRequestDetails(HttpRequest request) {
    LOGGER.info("Request Headers: {}", request.getHeaders());
    LOGGER.info("Request Method: {}", request.getMethod());
    LOGGER.info("Request URI: {}", request.getURI());
  }
}

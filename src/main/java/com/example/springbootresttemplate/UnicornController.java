package com.example.springbootresttemplate;

import java.util.Arrays;
import java.util.List;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequiredArgsConstructor
class UnicornController {

  private static final String API = "/api";
  private static final String UNICORNS = "/unicorns";
  private static final String UNICORNS_BY_ENTITY = "/unicornsByEntity";
  private static final String UNICORNS_BY_OBJECT = "/unicornsByObject";
  private static final String API_UNICORNS_BY_ENTITY = API + UNICORNS_BY_ENTITY;
  private static final String API_UNICORNS_BY_OBJECT = API + UNICORNS_BY_OBJECT;

  private RestTemplate restTemplate;

  @PostMapping(value = API_UNICORNS_BY_ENTITY,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UnicornResponse> createUnicornByEntity(@RequestBody UnicornDTo unicornDTo) throws RestClientException, JsonProcessingException {
    return restTemplate.postForEntity(
        "https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns",
        unicornDTo,
        UnicornResponse.class);
  }

  @PostMapping(value = API_UNICORNS_BY_OBJECT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public UnicornResponse createUnicornByObject(@RequestBody UnicornDTo unicornDTo) throws RestClientException, JsonProcessingException {
    return restTemplate.postForObject(
        "https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns",
        unicornDTo,
        UnicornResponse.class);
  }

  @GetMapping(API_UNICORNS_BY_ENTITY + "/{id}")
  public ResponseEntity<String> getUnicornByIdByEntity(@PathVariable final String id) {
    return restTemplate.getForEntity(
        "https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns" + id,
        String.class);
  }

  @GetMapping(API_UNICORNS_BY_OBJECT)
  public List<UnicornResponse> getUnicornByObject() {
    return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns",
        UnicornResponse[].class)));
  }

  @PutMapping(value = UNICORNS + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUnicorn(@PathVariable final String id, @RequestBody UnicornDTo unicornDTo) {
    restTemplate.put(
        "https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns",
        unicornDTo
    );
  }

  @DeleteMapping(UNICORNS + "/{id}")
  public void deleteUnicornByIdByEntity(@PathVariable final String id) {
    restTemplate.delete("https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns" + id);
  }

  @PutMapping(value = UNICORNS + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUnicornUsingExchange(@PathVariable final String id, @RequestBody UnicornDTo unicornDTo) {
    restTemplate.exchange(
        "https://crudcrud.com/api/72dbefb3917c4ce1b7bb17776fcf98e9/unicorns" + id,
        HttpMethod.PUT,
        new HttpEntity<>(unicornDTo),
        Void.class);
  }
}

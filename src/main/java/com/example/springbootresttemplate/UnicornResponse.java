package com.example.springbootresttemplate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class UnicornResponse {

  private String _id;
  private String name;
  private int age;
  private String colour;
}

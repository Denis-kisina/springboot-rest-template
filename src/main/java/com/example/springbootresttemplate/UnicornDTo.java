package com.example.springbootresttemplate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class UnicornDTo {

  private String name;
  private int age;
  private String colour;
}

package com.kimtaehyun84.springboot_webservice.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;



}

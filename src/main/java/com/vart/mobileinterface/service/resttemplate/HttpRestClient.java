package com.vart.mobileinterface.service.resttemplate;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

public interface HttpRestClient {
    <T, V> T executeRequest(MultiValueMap<String, String> headers, String URL, HttpMethod method, V requestBody, Class<T> responseDtoClass);
}
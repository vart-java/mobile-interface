package com.vart.mobileinterface.service.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vart.mobileinterface.viber.exception.DownloadDataParseException;
import com.vart.mobileinterface.viber.exception.ExecuteRequestHttpClientException;
import com.vart.mobileinterface.viber.exception.ResourceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestTemplateHttpRestClient implements HttpRestClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public <T, V> T executeRequest(
            MultiValueMap<String, String> headers,
            String URL,
            HttpMethod httpMethod,
            V requestBody,
            Class<T> responseDtoClass) {

        HttpEntity<V> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(URL, httpMethod, requestEntity, String.class);
            return mapSuccessResponse(responseEntity.getBody(), responseDtoClass);
        } catch (ResourceAccessException rae) {
            log.error("Exception during get access to resource");
            throw new ResourceUnavailableException(rae.getMessage());
        } catch (HttpClientErrorException hcee) {
            log.error("Exception in HTTP client");
            throw new ExecuteRequestHttpClientException(hcee.getMessage());
        }
    }

    private <T> T mapSuccessResponse(String responseBody, Class<T> responseDtoClass) {
        try {
            return objectMapper.readValue(responseBody, responseDtoClass);
        } catch (JsonProcessingException e) {
            log.error("Exception during data parse");
            throw new DownloadDataParseException(e.getMessage());
        }
    }
}

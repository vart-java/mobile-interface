package com.vart.mobileinterface.viber.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vart.mobileinterface.service.resttemplate.RestTemplateHttpRestClient;
import com.vart.mobileinterface.viber.dto.SetWebhookResponse;
import com.vart.mobileinterface.viber.exception.AddingViberWebHookException;
import com.vart.mobileinterface.viber.service.ViberHookSetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Service
public class ViberHookSetterImpl implements ViberHookSetter {
    @Value("${viber.set.hook.url}")
    private String viberSetHookUrl;
    @Value("${viber.auth.token}")
    private String viberAuthToken;
    @Value("${viber.event.types}")
    private String[] eventTypes;
    private final RestTemplateHttpRestClient restClient;

    @Override
    public void addWebHook(String url) {
        log.info("Start adding webhook with url " + url);
        SetWebhookResponse response = restClient.executeRequest(
                getHeaders(),
                viberSetHookUrl,
                HttpMethod.POST,
                getAddRequestBody(url),
                SetWebhookResponse.class
        );
        validateResponse(response);
        log.info("After add Viber return next response " + response);
    }

    @Override
    public void deleteWebHook() {
        log.info("Start deleting webhook");
        SetWebhookResponse response = restClient.executeRequest(
                getHeaders(),
                viberSetHookUrl,
                HttpMethod.POST,
                getDeleteRequestBody(),
                SetWebhookResponse.class
        );
        validateResponse(response);
        log.info("After delete Viber return next response " + response);
    }

    private ObjectNode getAddRequestBody(String url) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("url", url);
        requestBody.putPOJO("event_types", eventTypes);
        requestBody.put("send_name", true);
        requestBody.put("send_photo", true);
        return requestBody;
    }

    private ObjectNode getDeleteRequestBody() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("url", "");
        return requestBody;
    }

    private void validateResponse(SetWebhookResponse setWebhookResponse) {
        if (setWebhookResponse == null
                || setWebhookResponse.getStatus() == null
                || setWebhookResponse.getEventTypes() == null
                || setWebhookResponse.getStatusMessage() == null) {
            throw new AddingViberWebHookException("Viber return body with null value");
        }
        if (setWebhookResponse.getStatus() != 0)
            throw new AddingViberWebHookException("Viber return body with status " + setWebhookResponse.getStatus());
        if (!setWebhookResponse.getStatusMessage().equals("ok"))
            throw new AddingViberWebHookException("Viber return body with status message " + setWebhookResponse.getStatusMessage());
        if (setWebhookResponse.getEventTypes() != eventTypes)
            throw new AddingViberWebHookException("Viber return body with event types " + Arrays.toString(setWebhookResponse.getEventTypes()));
    }

    private MultiValueMap<String, String> getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("X-Viber-Auth-Token", viberAuthToken);
        return headers;
    }
}

package com.vart.mobileinterface.viber.dto;

import lombok.Data;

@Data
public class SetWebhookCallback {
    private String event;
    private Long timestamp;
    private Long messageToken;
}

package com.vart.mobileinterface.viber.dto;

import lombok.Data;

@Data
public class SetWebhookResponse {
    private Integer status;
    private String statusMessage;
    private String chatHostname;
    private String[] eventTypes;
}

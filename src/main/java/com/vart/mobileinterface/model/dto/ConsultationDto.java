package com.vart.mobileinterface.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultationDto {
    private long id;
    private String subject;
    private String userName;
    private int phoneNumber;
}
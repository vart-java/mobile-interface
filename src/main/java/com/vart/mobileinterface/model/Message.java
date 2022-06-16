package com.vart.mobileinterface.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Message {

    @Id
    private String id;

    private String createDate;
    private String value;
}

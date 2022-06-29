package com.vart.mobileinterface.service.kafka;

import com.vart.mobileinterface.model.Message;
import com.vart.mobileinterface.model.dto.ConsultationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageConsumer {
    @KafkaListener(topics = "consultation", groupId = "mobile")
    public void listen(ConsultationDto consultationDto) {
        log.info("Received: " + consultationDto);
    }
}

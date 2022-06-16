package com.vart.mobileinterface.scheduled;

import com.vart.mobileinterface.model.Message;
import com.vart.mobileinterface.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class TestClass {
    private final MessageRepository messageRepository;

//    @Scheduled(fixedDelay = 10000)
    public void testMethod () {
        messageRepository.save(Message.builder()
                        .createDate(LocalDateTime.now().toString())
                        .value("Hello world!")
                .build());
        System.out.println(messageRepository.findAll());
    }

}

package com.vart.mobileinterface.viber.controller;

import com.vart.mobileinterface.viber.dto.SetWebhookCallback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viber/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok("");
    }


    @PostMapping
    public ResponseEntity<String> postPing(@RequestBody SetWebhookCallback setWebhookCallback) {
        if (!setWebhookCallback.getEvent().equals("webhook") ||
                setWebhookCallback.getTimestamp() == null ||
                setWebhookCallback.getMessageToken() == null) {
            return ResponseEntity.badRequest().body("");
        } else return ResponseEntity.ok("");
    }
}

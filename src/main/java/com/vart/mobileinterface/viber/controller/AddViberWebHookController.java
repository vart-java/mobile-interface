package com.vart.mobileinterface.viber.controller;

import com.vart.mobileinterface.viber.dto.SetWebhookCallback;
import com.vart.mobileinterface.viber.dto.SetWebhookRequest;
import com.vart.mobileinterface.viber.service.ViberHookSetter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/viber/register")
public class AddViberWebHookController {
    private final ViberHookSetter viberHookSetter;

    @PostMapping
    public ResponseEntity<String> addHook(@RequestBody SetWebhookRequest setWebhookRequest) {
        viberHookSetter.addWebHook(setWebhookRequest.getUrl());
        return ResponseEntity.ok("success");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHook() {
        viberHookSetter.deleteWebHook();
        return ResponseEntity.ok("success");
    }
}

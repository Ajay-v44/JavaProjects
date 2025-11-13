package com.ecomm.backend.Controllers;

import com.ecomm.backend.Services.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {
    @Autowired
    ChatBotService chatBotService;
    @GetMapping("/ask")
    public ResponseEntity<String> askBot(@RequestParam String message){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(chatBotService.getBotResponse(message));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

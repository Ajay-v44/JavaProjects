package com.spring.AI.Controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatClient chatClient;

    private final OpenAiImageModel openAiImageModel;

    public AiController(OpenAiChatModel chatModel, OpenAiImageModel openAiImageModel) {
        chatClient = ChatClient.create(chatModel);
        this.openAiImageModel = openAiImageModel;
    }
//    public AiController(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }

    @GetMapping("/ask")
    public Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        try {
            return Map.of("completion", Objects.requireNonNull(chatClient.prompt().user(message).call().content()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Map.of("Exception", "Please Try Again");
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateImage(@RequestParam String prompt) {
        try {
            ImagePrompt imagePrompt = new ImagePrompt(prompt, OpenAiImageOptions.builder()
                    .quality("hd")
                    .height(1024)
                    .width(1024)
                    .style("natural")
                    .build());
            ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
            return ResponseEntity.status(HttpStatus.OK).body(imageResponse.getResult().getOutput().getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

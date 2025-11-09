package com.spring.AI.Controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ai/ollama")
public class OllamaController {
    private final ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/ask")
    public Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        try {
            return Map.of("completion", Objects.requireNonNull(Objects.requireNonNull(chatClient.prompt().user(message).call().content()).replace("\n", " ")   // or System.lineSeparator() if you want platform-specific
                    .replace("\t", "    ")));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Map.of("Exception", "Please Try Again");
        }
    }

    @PostMapping("/reccomend_movie")
    public ResponseEntity<String> reccomend(@RequestParam String language,@RequestParam String genre ,@RequestParam int year){
        try {
            String prompt= """
                    Recommend a list of movies based on the following criteria:
                    - Language: {language}
                    - Genre: {genre}
                    - Release Year: {year}
                    For each recommended movie, include the following details:
                    - Movie Title
                    - Director's Name
                    - Main Cast (at least 3 actors)
                    - Brief Description or Plot Summary
                    - IMDb Rating
                    - Movie Length (in minutes)
                    Please return the results in a structured format (e.g., JSON or table) for easy parsing."
                    """;
            PromptTemplate promptTemplate=new PromptTemplate(prompt);
            Prompt pmt=promptTemplate.create(Map.of("language",language,"genre",genre,"year",year));
            return ResponseEntity.status(HttpStatus.OK).body(chatClient.prompt(pmt).call().content());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception\", \"Please Try Again");
        }
    }
}

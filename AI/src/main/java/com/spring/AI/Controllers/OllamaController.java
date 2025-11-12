package com.spring.AI.Controllers;

import com.spring.AI.DTO.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ai/ollama")
public class OllamaController {
    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    @Autowired
    public VectorStore vectorStore;

    public OllamaController(@Qualifier("ollamaEmbeddingModel") EmbeddingModel embeddingModel, OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
        this.embeddingModel = embeddingModel;
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
    public ResponseEntity<String> reccomend(@RequestParam String language, @RequestParam String genre, @RequestParam int year) {
        try {
            String prompt = """
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
            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            Prompt pmt = promptTemplate.create(Map.of("language", language, "genre", genre, "year", year));
            return ResponseEntity.status(HttpStatus.OK).body(chatClient.prompt(pmt).call().content());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception\", \"Please Try Again");
        }
    }

    @PostMapping("/embedd")
    public ResponseEntity<float[]> embeddText(@RequestParam String text) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(embeddingModel.embed(text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/product")
    public List<Document> getProducts(@RequestParam String text) {
        //  return vectorStore.similaritySearch(text);
        return vectorStore.similaritySearch(SearchRequest.builder().query(text).topK(2).build());
    }

    @PostMapping("/ask/rag")
    public String askQuestion(@RequestParam String query) {
        return chatClient
                .prompt(query)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
    }

    @GetMapping("/getActorMovies/{actorName}")
    public List<String> getParticularActorMovies(@PathVariable String actorName) {
        List<String> movies = chatClient.prompt()
                .user(u -> u.text("List top 5 movies of {name}").param("name", actorName))
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));

        return movies;
    }

    @GetMapping("/getMovie/beanOC")
    public Movie beanOC(@RequestParam String actorName){
        BeanOutputConverter<Movie> opCon=new BeanOutputConverter<Movie>(Movie.class);

        Movie movie = chatClient.prompt()
                .user(u -> u.text("Get me the best movie of {name}").param("name", actorName))
                .call()
                .entity(new BeanOutputConverter<Movie>(Movie.class));

        return movie;
    }
    @GetMapping("/moviesList")
    public List<Movie> getMovieList(@RequestParam String name){

        BeanOutputConverter<Movie> opCon=new BeanOutputConverter<Movie>(Movie.class);

        List<Movie> movies = chatClient.prompt()
                .user(u -> u.text("Top 5 movies of {name}").param("name", name))
                .call()
                .entity(new BeanOutputConverter<List<Movie>>(new ParameterizedTypeReference<List<Movie>>() {}));

        return movies;
    }
}

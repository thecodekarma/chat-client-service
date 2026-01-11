package dev.ankumar.chat_client_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatClientServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ChatClient.Builder builder;

    @Autowired
    private ChatClient chatClient;

    @BeforeEach
    void setUp() {
        // reset interactions between tests
        reset(chatClient, builder);
        // builder.build() already wired in TestConfig to return chatClient
    }

    @Test
    void askMe_withCustomMessage_returnsContent() {
        when(chatClient.prompt("Hello").call().content()).thenReturn("Hi there!");
        ResponseEntity<String> resp = restTemplate.getForEntity("/ask-me?message=Hello", String.class);
        assertEquals("Hi there!", resp.getBody());
    }

    @Test
    void askMe_withDefaultMessage_returnsJoke() {
        String defaultMsg = "Tell me a joke";
        when(chatClient.prompt(defaultMsg).call().content()).thenReturn("A programmer walked into a bar...");
        ResponseEntity<String> resp = restTemplate.getForEntity("/ask-me", String.class);
        assertEquals("A programmer walked into a bar...", resp.getBody());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ChatClient chatClient() {
            return mock(ChatClient.class, RETURNS_DEEP_STUBS);
        }

        @Bean
        ChatClient.Builder chatClientBuilder(ChatClient chatClient) {
            ChatClient.Builder b = mock(ChatClient.Builder.class, RETURNS_DEEP_STUBS);
            when(b.build()).thenReturn(chatClient);
            return b;
        }
    }

}

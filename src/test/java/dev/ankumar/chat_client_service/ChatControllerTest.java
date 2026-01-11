package dev.ankumar.chat_client_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    private ChatClient chatClient;
    private ChatController controller;

    @BeforeEach
    void setUp() {
        ChatClient.Builder builder = mock(ChatClient.Builder.class);
        chatClient = mock(ChatClient.class, RETURNS_DEEP_STUBS);
        when(builder.build()).thenReturn(chatClient);
        controller = new ChatController(builder);
    }

    @Test
    void askMe_withCustomMessage_returnsContent() {
        when(chatClient.prompt("Hello").call().content()).thenReturn("Hi there!");
        String resp = controller.askMe("Hello");
        assertEquals("Hi there!", resp);
        verify(chatClient, atLeastOnce()).prompt("Hello");
    }

    @Test
    void askMe_withDefaultMessage_returnsJoke() {
        String defaultMsg = "Tell me a joke";
        when(chatClient.prompt(defaultMsg).call().content()).thenReturn("A programmer walked into a bar...");
        String resp = controller.askMe(defaultMsg);
        assertEquals("A programmer walked into a bar...", resp);
        verify(chatClient, atLeastOnce()).prompt(defaultMsg);
    }
}

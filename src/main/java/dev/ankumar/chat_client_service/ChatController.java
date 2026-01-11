package dev.ankumar.chat_client_service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/ask-me")
    public String askMe(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt(message).call().content();
    }
}

package com.osir.aiservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai/user") // TODO 加了/user
public class OrderingAssistantController {

    private final ChatClient orderingAssistantChatClient;

    public OrderingAssistantController(@Qualifier("orderingAssistantChatClient") ChatClient orderingAssistantChatClient) {
        this.orderingAssistantChatClient = orderingAssistantChatClient;
    }

    @GetMapping(value = "/service", produces = "text/html;charset=utf-8")
    public String aiOrderingAssistantService(@RequestParam("prompt")String prompt,@RequestParam("chatId") String conversationId) {
        return orderingAssistantChatClient.prompt()
                .user(prompt)
                .advisors(a-> a.param(ChatMemory.CONVERSATION_ID, conversationId))
//                .stream()
                .call()
                .content();
    }

}

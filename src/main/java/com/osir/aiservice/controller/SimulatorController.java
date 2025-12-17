package com.osir.aiservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/ai")
@RestController
public class SimulatorController {

    private final ChatClient openAiChatClient;

    public SimulatorController(@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

    /**
     * 模拟器游戏
     * @return 流式输出
     */
    @GetMapping(value = "/game", produces = "text/html; charset=utf-8")
    public Flux<String> chat(@RequestParam("prompt") String prompt, @RequestParam("chatId") String conversationId){
        return openAiChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content();
    }

}

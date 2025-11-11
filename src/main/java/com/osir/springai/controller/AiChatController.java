package com.osir.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class AiChatController {

    private final ChatClient chatClient;

    @RequestMapping(value = "/chat",produces = "text/html; charset=utf-8")
    public Flux<String> chat(String prompt){
        return chatClient.prompt()
                .user(prompt)
                .stream()   //  流式输出
                .content();
    }


}

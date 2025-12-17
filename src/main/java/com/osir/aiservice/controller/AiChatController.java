package com.osir.aiservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiChatController {

    private final ChatClient ollamaChatClient;

    public AiChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient){
        this.ollamaChatClient = ollamaChatClient;
    }

    /**
     * ai聊天
     * @param prompt 提示词
     * @param conversationId 会话id
     * @return 流式输出
     */
    @PostMapping(value = "/chat",produces = "text/html; charset=utf-8")
    public Flux<String> chat(@RequestParam("prompt") String prompt, @RequestParam("chatId") String conversationId){
        return ollamaChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()   //  流式输出
                .content();
    }


}

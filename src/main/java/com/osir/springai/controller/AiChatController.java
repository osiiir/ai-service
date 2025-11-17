package com.osir.springai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class AiChatController {

    private final ChatClient chatClient;

    /**
     * ai聊天
     * @param prompt 提示词
     * @param conversationId 会话id
     * @return 流式输出
     */
    @PostMapping(value = "/chat",produces = "text/html; charset=utf-8")
    public Flux<String> chat(String prompt,@RequestParam("chatId") String conversationId){
        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()   //  流式输出
                .content();
    }


}

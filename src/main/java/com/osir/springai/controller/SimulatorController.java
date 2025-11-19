package com.osir.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RequestMapping("/ai")
@RestController
public class SimulatorController {

    // 测试过@RequiredArgsConstructor是能正确注入openAiChatClient的，即使有多个ChatClient类型的Bean
    // 是根据bean的名称和属性名称匹配来注入的，把变量名改成其它的就会报错。
    private final ChatClient openAiChatClient;

    /**
     * 模拟器游戏
     * @return 流式输出
     */
    @GetMapping(value = "/game", produces = "text/html; charset=utf-8")
    public Flux<String> chat(String prompt, @RequestParam("chatId") String conversationId){
        return openAiChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content();
    }

}

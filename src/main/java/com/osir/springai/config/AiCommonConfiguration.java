package com.osir.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AiCommonConfiguration {

    @Bean
    public ChatClient OllamaChatClient(OllamaChatModel model){
        return ChatClient.builder(model)
                .defaultSystem("你是热心助手小梅，请以小梅的身份回答问题。")
                .defaultAdvisors(new SimpleLoggerAdvisor()) // 控制台输出日志
                .build();
    }

}

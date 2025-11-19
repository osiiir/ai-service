package com.osir.springai.config;


import com.osir.springai.mapper.AiChatMessageMapper;
import com.osir.springai.memory.AiChatMemory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class AiCommonConfiguration {

    @Bean
    @Primary
    public ChatMemory commonChatMemory(AiChatMessageMapper aiChatMessageMapper){
        return new AiChatMemory(aiChatMessageMapper);
    }

    /**
     * ai聊天客户端
     * @param model
     * @param chatMemory
     * @return
     */
    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel model, ChatMemory chatMemory){
        return ChatClient.builder(model)
                .defaultSystem("你是无所不知的智者小梅，请以智者小梅的身份回答问题。")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(), // 控制台输出日志
                        MessageChatMemoryAdvisor.builder(chatMemory).build()  // 会话记忆
                )
                .build();
    }




}

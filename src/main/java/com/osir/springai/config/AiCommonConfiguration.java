package com.osir.springai.config;


import com.osir.springai.constant.SimulatorSystemConstants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AiCommonConfiguration {

    /**
     * ai聊天客户端
     * @param model
     * @param chatMemory
     * @return
     */
    @Bean("ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel model,
                                       @Qualifier("aiChatMemory") ChatMemory chatMemory){
        return ChatClient.builder(model)
                .defaultSystem("你是无所不知的智者小梅，请以智者小梅的身份回答问题。")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(), // 控制台输出日志
                        MessageChatMemoryAdvisor.builder(chatMemory).build()  // 会话记忆
                )
                .build();
    }


    /**
     * 模拟器的ai客户端
     * @param model
     * @param chatMemory
     * @return
     */
    @Bean("openAiChatClient")
    public ChatClient openAiChatClient(OpenAiChatModel model,
                                       @Qualifier("simulatorChatMemory") ChatMemory chatMemory){
        return ChatClient.builder(model)
                .defaultSystem(SimulatorSystemConstants.GAME_SYSTEM_PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }




}

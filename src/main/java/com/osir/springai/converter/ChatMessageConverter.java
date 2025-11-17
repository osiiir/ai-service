package com.osir.springai.converter;

import cn.hutool.json.JSONUtil;
import com.osir.springai.entity.AiChatMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChatMessageConverter {

        public Message convertToMessage(AiChatMessage aiChatMessage){
            Message message = null;
            // SYSTEM
            if(aiChatMessage.getMessageType() == 0){
                message = SystemMessage.builder()
                        .text(aiChatMessage.getContent())
                        .metadata(JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class))
                        .build();
            }
            // USER
            else if(aiChatMessage.getMessageType() == 1){
                message = UserMessage.builder()
                        .text(aiChatMessage.getContent())
                        .metadata(JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class))
                        .build();
            }
            // ASSISTANT
            else if(aiChatMessage.getMessageType() == 2){
                message = new AssistantMessage(aiChatMessage.getContent(), JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class));
            }
            return message;
        }

}

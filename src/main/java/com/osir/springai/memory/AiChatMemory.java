package com.osir.springai.memory;

import cn.hutool.json.JSONUtil;
import com.osir.springai.entity.AiChatMessage;
import com.osir.springai.mapper.AiChatMessageMapper;
import com.osir.springai.memory.mould.AbstractChatMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AiChatMemory extends AbstractChatMemory {

    private final AiChatMessageMapper aiChatMessageMapper;

    @Override
    protected void insertBatch(List<?> chatMessageList) {
        List<AiChatMessage> list = chatMessageList.stream()
                .map(chatMessage -> (AiChatMessage) chatMessage)
                .toList();
        aiChatMessageMapper.insertBatch(list);
    }

    @Override
    protected List<?> selectListByConversationId(String conversationId) {
        return aiChatMessageMapper.selectListByConversationId(conversationId);
    }

    @Override
    protected void deleteByConversationId(String conversationId) {
        aiChatMessageMapper.deleteByConversationId(conversationId);
    }

    @Override
    protected Object createChatMessage(String conversationId, Message message) {
        AiChatMessage aiChatMessage = new AiChatMessage();
        aiChatMessage.setConversationId(conversationId);
        aiChatMessage.setContent(message.getText());
        aiChatMessage.setMessageType(getOriginMessageType(message));
        aiChatMessage.setMetadata(JSONUtil.toJsonStr(message.getMetadata()));
        return aiChatMessage;
    }

    @Override
    protected Message convertToMessage(Object chatMessage) {
        AiChatMessage aiChatMessage = (AiChatMessage) chatMessage;
        Message message = switch (aiChatMessage.getMessageType()) {
            case 0 -> SystemMessage.builder()
                    .text(aiChatMessage.getContent())
                    .metadata(JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class))
                    .build();
            case 1 -> UserMessage.builder()
                    .text(aiChatMessage.getContent())
                    .metadata(JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class))
                    .build();
            case 2 -> new AssistantMessage(aiChatMessage.getContent(), JSONUtil.toBean(aiChatMessage.getMetadata(), Map.class));
            default -> null;
        };
        return message;
    }

}

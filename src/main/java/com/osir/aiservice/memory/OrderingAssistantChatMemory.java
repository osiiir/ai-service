package com.osir.aiservice.memory;

import cn.hutool.json.JSONUtil;
import com.osir.aiservice.mapper.OrderingAssistantChatMessageMapper;
import com.osir.aiservice.memory.mould.AbstractChatMemory;
import com.osir.aiservice.pojo.entity.AssistantChatMessage;
import com.osir.commonservice.utils.LoginUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OrderingAssistantChatMemory extends AbstractChatMemory {

    private final OrderingAssistantChatMessageMapper orderingAssistantChatMessageMapper;

    @Override
    protected void insertBatch(List<?> chatMessageList) {
        List<AssistantChatMessage> list = chatMessageList.stream()
                .map(chatMessage -> (AssistantChatMessage) chatMessage)
                .toList();
        orderingAssistantChatMessageMapper.insertBatch(list);
    }

    @Override
    protected List<?> selectListByConversationId(String conversationId) {
        return orderingAssistantChatMessageMapper.selectListByConversationId(conversationId);
    }

    @Override
    protected void deleteByConversationId(String conversationId) {
        orderingAssistantChatMessageMapper.deleteByConversationId(conversationId);
    }

    @Override
    protected Object createChatMessage(String conversationId, Message message) {
        AssistantChatMessage assistantChatMessage = new AssistantChatMessage();
        assistantChatMessage.setConversationId(conversationId);
        assistantChatMessage.setContent(message.getText());
        assistantChatMessage.setMessageType(getOriginMessageType(message));
        assistantChatMessage.setMetadata(JSONUtil.toJsonStr(message.getMetadata()));
        assistantChatMessage.setUserId(LoginUserContext.getUserId());
        return assistantChatMessage;
    }

    @Override
    protected Message convertToMessage(Object chatMessage) {
        AssistantChatMessage assistantChatMessage = (AssistantChatMessage) chatMessage;
        Message message = switch (assistantChatMessage.getMessageType()) {
            case 0 -> SystemMessage.builder()
                    .text(assistantChatMessage.getContent())
                    .metadata(JSONUtil.toBean(assistantChatMessage.getMetadata(), Map.class))
                    .build();
            case 1 -> UserMessage.builder()
                    .text(assistantChatMessage.getContent())
                    .metadata(JSONUtil.toBean(assistantChatMessage.getMetadata(), Map.class))
                    .build();
            case 2 -> new AssistantMessage(assistantChatMessage.getContent(), JSONUtil.toBean(assistantChatMessage.getMetadata(), Map.class));
            default -> null;
        };
        return message;
    }

}

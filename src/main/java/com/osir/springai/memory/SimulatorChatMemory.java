package com.osir.springai.memory;

import cn.hutool.json.JSONUtil;
import com.osir.springai.entity.AiChatMessage;
import com.osir.springai.entity.SimulatorChatMessage;
import com.osir.springai.mapper.AiChatMessageMapper;
import com.osir.springai.mapper.SimulatorChatMessageMapper;
import com.osir.springai.memory.mould.AbstractChatMemory;
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
public class SimulatorChatMemory extends AbstractChatMemory {

    private final SimulatorChatMessageMapper simulatorChatMessageMapper;

    @Override
    protected void insertBatch(List<?> chatMessageList) {
        List<SimulatorChatMessage> list = chatMessageList.stream()
                .map(chatMessage -> (SimulatorChatMessage) chatMessage)
                .toList();
        simulatorChatMessageMapper.insertBatch(list);
    }

    @Override
    protected List<?> selectListByConversationId(String conversationId) {
        return simulatorChatMessageMapper.selectListByConversationId(conversationId);
    }

    @Override
    protected void deleteByConversationId(String conversationId) {
        simulatorChatMessageMapper.deleteByConversationId(conversationId);
    }

    @Override
    protected Object createChatMessage(String conversationId, Message message) {
        SimulatorChatMessage simulatorChatMessage = new SimulatorChatMessage();
        simulatorChatMessage.setConversationId(conversationId);
        simulatorChatMessage.setContent(message.getText());
        simulatorChatMessage.setMessageType(getOriginMessageType(message));
        simulatorChatMessage.setMetadata(JSONUtil.toJsonStr(message.getMetadata()));
        return simulatorChatMessage;
    }

    @Override
    protected Message convertToMessage(Object chatMessage) {
        SimulatorChatMessage simulatorChatMessage = (SimulatorChatMessage) chatMessage;
        Message message = switch (simulatorChatMessage.getMessageType()) {
            case 0 -> SystemMessage.builder()
                    .text(simulatorChatMessage.getContent())
                    .metadata(JSONUtil.toBean(simulatorChatMessage.getMetadata(), Map.class))
                    .build();
            case 1 -> UserMessage.builder()
                    .text(simulatorChatMessage.getContent())
                    .metadata(JSONUtil.toBean(simulatorChatMessage.getMetadata(), Map.class))
                    .build();
            case 2 -> new AssistantMessage(simulatorChatMessage.getContent(), JSONUtil.toBean(simulatorChatMessage.getMetadata(), Map.class));
            default -> null;
        };
        return message;
    }

}

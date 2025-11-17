package com.osir.springai.service.memory;

import cn.hutool.json.JSONUtil;
import com.osir.springai.converter.ChatMessageConverter;
import com.osir.springai.entity.AiChatMessage;
import com.osir.springai.mapper.AiChatMessageMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AiChatMemory implements ChatMemory {

    private final AiChatMessageMapper aiChatMessageMapper;
    private final ChatMessageConverter chatMessageConverter;

    @Override
    public void add(@NonNull String conversationId, @NonNull List<Message> messages) {
        List<AiChatMessage> aiChatMessageList = new ArrayList<>();
        for (Message message : messages) {
            AiChatMessage aiChatMessage = new AiChatMessage();
            aiChatMessage.setConversationId(conversationId);
            aiChatMessage.setContent(message.getText());
            aiChatMessage.setMessageType(getOriginMessageType(message));
            aiChatMessage.setMetadata(JSONUtil.toJsonStr(message.getMetadata()));
            aiChatMessageList.add(aiChatMessage);
        }
        aiChatMessageMapper.insertBatch(aiChatMessageList);
    }

    @Override
    public List<Message> get(@NonNull String conversationId) {
        List<AiChatMessage> list = aiChatMessageMapper.selectListByConversationId(conversationId);
        List<Message> messageList = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            for (AiChatMessage aiChatMessage : list){
                messageList.add(chatMessageConverter.convertToMessage(aiChatMessage));
            }
        }
        return messageList;
    }

    @Override
    public void clear(@NonNull String conversationId) {
        aiChatMessageMapper.deleteByConversationId(conversationId);
    }

    private int getOriginMessageType(Message message) {
        if (message.getMessageType() == MessageType.SYSTEM) {
            return 0;
        } else if (message.getMessageType() == MessageType.USER) {
            return 1;
        } else if (message.getMessageType() == MessageType.ASSISTANT) {
            return 2;
        } else return -1;
    }

}

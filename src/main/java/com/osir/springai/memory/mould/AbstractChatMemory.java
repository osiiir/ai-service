package com.osir.springai.memory.mould;


import lombok.NonNull;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;


import java.util.ArrayList;
import java.util.List;

// 模板类
public abstract class AbstractChatMemory implements ChatMemory {

    protected abstract void insertBatch(List<?> chatMessageList);

    protected abstract List<?> selectListByConversationId(String conversationId);

    protected abstract void deleteByConversationId(String conversationId);

    protected abstract Object createChatMessage(String conversationId, Message message);

    protected abstract Message convertToMessage(Object chatMessage);

    @Override
    public void add(@NonNull String conversationId, @NonNull List<Message> messages) {
        List<Object> chatMessageList = new ArrayList<>();
        for (Message message : messages) {
            Object object = createChatMessage(conversationId, message);
            chatMessageList.add(object);
        }
        insertBatch(chatMessageList);
    }

    @Override
    public List<Message> get(@NonNull String conversationId) {
        List<?> list = selectListByConversationId(conversationId);
        List<Message> messageList = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            for (Object obj : list){
                messageList.add(convertToMessage(obj));
            }
        }
        return messageList;
    }

    @Override
    public void clear(@NonNull String conversationId) {
        deleteByConversationId(conversationId);
    }

    protected int getOriginMessageType(Message message) {
        if (message.getMessageType() == MessageType.SYSTEM) {
            return 0;
        } else if (message.getMessageType() == MessageType.USER) {
            return 1;
        } else if (message.getMessageType() == MessageType.ASSISTANT) {
            return 2;
        } else return -1;
    }

}

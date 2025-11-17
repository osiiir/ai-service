package com.osir.springai.service;

import com.osir.springai.entity.vo.MessageVo;
import org.springframework.ai.chat.messages.Message;

import java.util.List;

public interface AiChatService {

    List<String> selectConversationIds();

    List<MessageVo> selectByConversationId(String conversationId);
}

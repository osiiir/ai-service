package com.osir.aiservice.service;

import com.osir.aiservice.pojo.vo.MessageVo;

import java.util.List;

public interface AiChatService {

    List<String> selectConversationIds();

    List<MessageVo> selectByConversationId(String conversationId);
}

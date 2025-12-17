package com.osir.aiservice.service.impl;

import com.osir.aiservice.pojo.entity.AiChatMessage;
import com.osir.aiservice.pojo.vo.MessageVo;
import com.osir.aiservice.mapper.AiChatMessageMapper;
import com.osir.aiservice.service.AiChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AiChatServiceImpl implements AiChatService {

    private final AiChatMessageMapper aiChatMessageMapper;

    @Override
    public List<String> selectConversationIds() {
        return aiChatMessageMapper.selectConversationIds();
    }

    @Override
    public List<MessageVo> selectByConversationId(String conversationId) {
        List<AiChatMessage> aiChatMessages = aiChatMessageMapper.selectListByConversationId(conversationId);
        return aiChatMessages.stream().map(MessageVo::new).toList();
    }
}

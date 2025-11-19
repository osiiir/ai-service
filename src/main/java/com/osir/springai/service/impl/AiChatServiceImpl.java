package com.osir.springai.service.impl;

import com.osir.springai.entity.AiChatMessage;
import com.osir.springai.entity.vo.MessageVo;
import com.osir.springai.mapper.AiChatMessageMapper;
import com.osir.springai.service.AiChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

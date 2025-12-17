package com.osir.aiservice.controller;

import com.osir.aiservice.pojo.vo.MessageVo;
import com.osir.aiservice.service.AiChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ai/history")
@RestController
public class AiHistoryController {

    private final AiChatService aiChatService;

    /**
     * 获取历史会话
     * @param type 会话类型：chat, service, pdf
     * @return 会话列表
     */
    @GetMapping("/{type}")
    public List<String> chat(@PathVariable("type") String type){
        if("chat".equals(type)){
            return aiChatService.selectConversationIds();
        }
        else if("service".equals(type)){
            return List.of();
        }
        else if ("pdf".equals(type)){
            return List.of();
        }
        else return List.of();
    }

    /**
     * 获取历史会话详情
     * @param type 会话类型：chat, service, pdf
     * @param conversationId 会话id
     * @return 会话详情
     */
    @GetMapping("/{type}/{chatId}")
    public List<MessageVo> chat(@PathVariable("type") String type, @PathVariable("chatId") String conversationId){
        if("chat".equals(type)){
            return aiChatService.selectByConversationId(conversationId);
        }
        else if("service".equals(type)){
            return List.of();
        }
        else if ("pdf".equals(type)){
            return List.of();
        }
        else return List.of();
    }

}

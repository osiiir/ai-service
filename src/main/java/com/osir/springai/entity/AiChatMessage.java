package com.osir.springai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiChatMessage{

    private long id;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型：0:system，1:user，2:assistant
     */
    private int messageType;

    /**
     * 元数据
     */
    private String metadata;

}

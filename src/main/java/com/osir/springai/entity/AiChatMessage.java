package com.osir.springai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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

    /**
     * 消息创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端传入时间格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 序列化和反序列化
    private LocalDateTime createTime;

    /**
     * 用户Id
     */
    private Long userId;

}

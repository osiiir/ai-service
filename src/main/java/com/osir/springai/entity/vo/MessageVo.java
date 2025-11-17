package com.osir.springai.entity.vo;

import com.osir.springai.entity.AiChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageVo {

    private String content;

    private String role;

    public MessageVo(Message message){
        this.content = message.getText();
        this.role = switch (message.getMessageType()){
            case USER -> "user";
            case ASSISTANT -> "assistant";
            case SYSTEM -> "system";
            default -> "unknown";
        };
    }

    public MessageVo(AiChatMessage aiChatMessage){
        this.content = aiChatMessage.getContent();
        this.role = switch (aiChatMessage.getMessageType()){
            case 0 -> "system";
            case 1 -> "user";
            case 2 -> "assistant";
            default -> "unknown";
        };
    }

}

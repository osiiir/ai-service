package com.osir.springai.mapper;

import com.osir.springai.entity.AiChatMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AiChatMessageMapper {

    /**
     * 批量插入会话消息
     * @param aiChatMessageList 会话消息列表
     */
    void insertBatch(List<AiChatMessage> aiChatMessageList);

    /**
     * 根据会话id删除会话消息
     * @param conversationId 会话id
     */
    @Delete("delete from ai_chat_message where conversation_id = #{conversationId}")
    void deleteByConversationId(String conversationId);

    /**
     * 根据会话id查询会话消息
     * @param conversationId 会话id
     * @return 会话消息列表
     */
    @Select("select * from ai_chat_message where conversation_id = #{conversationId} order by create_time")
    List<AiChatMessage> selectListByConversationId(String conversationId);

    /**
     * 查询所有会话id
     * @return 会话id列表
     */
    @Select("select distinct conversation_id from ai_chat_message")
    List<String> selectConversationIds();

}

package com.osir.aiservice.mapper;

import com.osir.aiservice.pojo.entity.AiChatMessage;
import com.osir.aiservice.pojo.entity.AssistantChatMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderingAssistantChatMessageMapper {

    /**
     * 批量插入会话消息
     *
     * @param assistantChatMessageList 会话消息列表
     */
    void insertBatch(@Param("assistantChatMessageList") List<AssistantChatMessage> assistantChatMessageList);

    /**
     * 根据会话id删除会话消息
     *
     * @param conversationId 会话id
     */
    @Delete("delete from assistant_chat_message where conversation_id = #{conversationId}")
    void deleteByConversationId(@Param("conversationId") String conversationId);

    /**
     * 根据会话id查询会话消息
     *
     * @param conversationId 会话id
     * @return 会话消息列表
     */
    @Select("select id,conversation_id,content,message_type,metadata,user_id from assistant_chat_message " +
            "where conversation_id = #{conversationId} " +
            "order by create_time")
    List<AssistantChatMessage> selectListByConversationId(@Param("conversationId") String conversationId);

    /**
     * 查询所有会话id
     *
     * @return 会话id列表
     */
    @Select("select distinct conversation_id " +
            "from assistant_chat_message " +
            "group by conversation_id " +
            "order by max(create_time) desc")
    List<String> selectConversationIds();

}

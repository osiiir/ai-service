package com.osir.springai.mapper;

import com.osir.springai.entity.AiChatMessage;
import com.osir.springai.entity.SimulatorChatMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SimulatorChatMessageMapper {

    /**
     * 批量插入会话消息
     *
     * @param simulatorChatMessageList 会话消息列表
     */
    void insertBatch(List<SimulatorChatMessage> simulatorChatMessageList);

    /**
     * 根据会话id删除会话消息
     *
     * @param conversationId 会话id
     */
    @Delete("delete from simulator_chat_message where conversation_id = #{conversationId}")
    void deleteByConversationId(String conversationId);

    /**
     * 根据会话id查询会话消息
     *
     * @param conversationId 会话id
     * @return 会话消息列表
     */
    @Select("select id,conversation_id,content,message_type,metadata,user_id from simulator_chat_message " +
            "where conversation_id = #{conversationId} " +
            "order by create_time")
    List<SimulatorChatMessage> selectListByConversationId(String conversationId);

    /**
     * 查询所有会话id
     *
     * @return 会话id列表
     */
    @Select("select distinct conversation_id " +
            "from simulator_chat_message " +
            "group by conversation_id " +
            "order by max(create_time) desc")
    List<String> selectConversationIds();

}

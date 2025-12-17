package com.osir.aiservice.tools;

import com.osir.aiservice.feign.UserServiceFeignClient;
import com.osir.commonservice.utils.LoginUserContext;
import com.osir.takeoutpojo.entity.AddressBook;
import com.osir.takeoutpojo.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("userTool")
public class UserTool {

    private final UserServiceFeignClient userServiceFeignClient;

    @Tool(description = "获取当前登录用户的id")
    Long getUserId(){
        log.info("AI_ORDERING_ASSISTANT---获取当前登录用户的id");
        return LoginUserContext.getUserId();
    }

    @Tool(description = "查询当前登录用户的所有地址信息")
    Result listAddressBook(){
        log.info("AI_ORDERING_ASSISTANT---查询当前登录用户所有的地址信息");
        return userServiceFeignClient.list();
    }

    @Tool(description = "新增地址")
    Result save(@ToolParam (required = false, description = "地址信息") AddressBook addressBook){
        log.info("AI_ORDERING_ASSISTANT---新增地址");
        return userServiceFeignClient.save(addressBook);
    }

    @Tool(description = "根据id查询地址")
    Result getById(@ToolParam (required = false, description = "地址id") Long id){
        log.info("AI_ORDERING_ASSISTANT---根据id查询地址:{}", id);
        return userServiceFeignClient.getById(id);
    }

    @Tool(description = "修改地址信息")
    Result update(@ToolParam (required = false, description = "地址信息") AddressBook addressBook){
        log.info("AI_ORDERING_ASSISTANT---修改地址信息:{}", addressBook);
        return userServiceFeignClient.update(addressBook);
    }

    @Tool(description = "设置默认地址")
    Result setDefault(@ToolParam (required = false, description = "地址信息") AddressBook addressBook){
        log.info("AI_ORDERING_ASSISTANT---设置默认地址:{}", addressBook);
        return userServiceFeignClient.setDefault(addressBook);
    }

    @Tool(description = "删除地址")
    Result deleteById(@ToolParam (required = false, description = "地址id") Long id){
        log.info("AI_ORDERING_ASSISTANT---删除地址:{}", id);
        return userServiceFeignClient.deleteById(id);
    }

    @Tool(description = "查询默认地址")
    Result getDefault(){
        log.info("AI_ORDERING_ASSISTANT---查询默认地址");
        return userServiceFeignClient.getDefault();
    }

}

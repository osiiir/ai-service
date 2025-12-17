package com.osir.aiservice.tools;

import com.osir.aiservice.feign.CartServiceFeignClient;
import com.osir.takeoutpojo.dto.ShoppingCartDTO;
import com.osir.takeoutpojo.entity.ShoppingCart;
import com.osir.takeoutpojo.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component("cartTool")
public class CartTool {

    private final CartServiceFeignClient cartServiceFeignClient;

    @Tool(description = "添加菜品或套餐到购物车")
    Result addDishOrSetmeal(@ToolParam(required = false, description = "插入数据参数") ShoppingCartDTO shoppingCartDTO){
        log.info("AI_ORDERING_ASSISTANT---添加菜品或套餐到购物车:{}", shoppingCartDTO);
        return cartServiceFeignClient.add(shoppingCartDTO);
    }

    @Tool(description = "批量添加到购物车")
    Result insertBatch(@ToolParam(required = false, description = "批量插入数据参数") List<ShoppingCart> shoppingCartList){
        log.info("AI_ORDERING_ASSISTANT---批量添加到购物车:{}", shoppingCartList);
        return cartServiceFeignClient.insertBatch(shoppingCartList);
    }

    @Tool(description = "查看购物车")
    Result listCart(){
        log.info("AI_ORDERING_ASSISTANT---查看购物车");
        return cartServiceFeignClient.list();
    }

    @Tool(description = "从购物车删除菜品(&口味)或套餐")
    Result sub(@ToolParam(required = false, description = "删除数据参数") ShoppingCartDTO shoppingCartDTO){
        log.info("AI_ORDERING_ASSISTANT---从购物车删除菜品(&口味)或套餐:{}", shoppingCartDTO);
        return cartServiceFeignClient.sub(shoppingCartDTO);
    }

    @Tool(description = "清空购物车")
    Result clean(){
        log.info("AI_ORDERING_ASSISTANT---清空购物车");
        return cartServiceFeignClient.clean();
    }

}

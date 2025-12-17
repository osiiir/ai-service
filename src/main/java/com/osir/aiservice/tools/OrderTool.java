package com.osir.aiservice.tools;

import com.osir.aiservice.feign.OrderServiceFeignClient;
import com.osir.takeoutpojo.dto.OrdersPageQueryDTO;
import com.osir.takeoutpojo.dto.OrdersPaymentDTO;
import com.osir.takeoutpojo.dto.OrdersSubmitDTO;
import com.osir.takeoutpojo.entity.AddressBook;
import com.osir.takeoutpojo.entity.ShoppingCart;
import com.osir.takeoutpojo.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("orderTool")
public class OrderTool {

    private final OrderServiceFeignClient orderServiceFeignClient;

    // 获取打包费
    @Tool(description = "获取打包费")
    int getPackFee(@ToolParam (required = false, description = "购物车信息") ShoppingCart shoppingCart){
        log.info("AI_ORDERING_ASSISTANT---获取打包费");
        // TODO 暂时固定为1元,后期根据具体商品打包费计算..
        return 1;
    }
    @Tool(description = "获取配送费")
    int getDeliveryFee(@ToolParam (required = false, description = "地址") AddressBook address){
        log.info("AI_ORDERING_ASSISTANT---获取配送费:{}", address);
        // TODO 暂时固定为6元,后期可以根据距离计算..
        return 6;
    }

    @Tool(description = "用户下单")
    Result submit(@ToolParam (required = false, description = "下单参数") OrdersSubmitDTO ordersSubmitDTO){
        log.info("AI_ORDERING_ASSISTANT---用户下单:{}", ordersSubmitDTO);
        return orderServiceFeignClient.submit(ordersSubmitDTO);
    }

    @Tool(description = "订单支付")
    Result payment(@ToolParam (required = false, description = "支付参数") OrdersPaymentDTO ordersPaymentDTO) throws Exception{
        return orderServiceFeignClient.payment(ordersPaymentDTO);
    }

    @Tool(description = "查询历史订单")
    Result getPage(@ToolParam (required = false, description = "查询参数") OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("AI_ORDERING_ASSISTANT---查询历史订单:{}", ordersPageQueryDTO);
        return orderServiceFeignClient.getPage(ordersPageQueryDTO);
    }

    @Tool(description = "根据订单号查询订单")
    Result getOrder(@ToolParam (required = false, description = "订单号") String orderNumber){
        Long id = orderServiceFeignClient.getOrderIdByNumber(orderNumber);
        log.info("AI_ORDERING_ASSISTANT---查询订单:{}", id);
        return orderServiceFeignClient.getOrder(id);
    }

    @Tool(description = "根据订单号再来一单")
    Result repetition(@ToolParam (required = false, description = "订单号") String orderNumber){
        Long id = orderServiceFeignClient.getOrderIdByNumber(orderNumber);
        log.info("AI_ORDERING_ASSISTANT---根据订单id再来一单:{}", id);
        return orderServiceFeignClient.repetition(id);
    }

    @Tool(description = "根据订单号取消订单")
    Result cancel(@ToolParam(required = false, description = "订单号") String orderNumber){
        Long id = orderServiceFeignClient.getOrderIdByNumber(orderNumber);
        log.info("AI_ORDERING_ASSISTANT---取消订单:{}", id);
        return orderServiceFeignClient.cancel(id);
    }

    @Tool(description = "根据订单号订单催单")
    Result reminder(@ToolParam (required = false, description = "订单号") String orderNumber){
        Long id = orderServiceFeignClient.getOrderIdByNumber(orderNumber);
        log.info("AI_ORDERING_ASSISTANT---订单催单:{}", id);
        return orderServiceFeignClient.reminder(id);
    }

}

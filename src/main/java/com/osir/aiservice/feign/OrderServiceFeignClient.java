package com.osir.aiservice.feign;

import com.osir.takeoutpojo.dto.OrdersPageQueryDTO;
import com.osir.takeoutpojo.dto.OrdersPaymentDTO;
import com.osir.takeoutpojo.dto.OrdersSubmitDTO;
import com.osir.takeoutpojo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service", path = "/order/user/order")
public interface OrderServiceFeignClient {

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    Result submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    // 跳过微信支付功能
    @PutMapping("/payment")
    Result payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception;


    /**
     * 查询订单
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    Result getOrder(@PathVariable("id") Long id);

    /**
     * 查询历史订单
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/historyOrders")
    // mark: 在普通的Controller中，使用@GetMapping+复杂参数可以+@RequestBody也可以
    // 但是对于feign调用，限制更加严格，@GetMapping是不能+@RequestBody的。
    // 但是如果直接使用@GetMapping+复杂参数，会报405错误.
    // 那么需要@SpringQueryMap注解,例如：OrdersPageQueryDTO{page=1, pageSize=10} 转换为 ?page=1&pageSize=10
    Result getPage(@SpringQueryMap OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 再来一单
     * @param id
     * @return
     */
    @PostMapping("/repetition/{id}")
    Result repetition(@PathVariable("id") Long id);

    /**
     * 取消订单
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    Result cancel(@PathVariable("id") Long id);

    /**
     * 订单催单
     * @param id
     * @return
     */
    @GetMapping("/reminder/{id}")
    Result reminder(@PathVariable("id") Long id);

    @GetMapping("/getOrderId")
    // mark: feign调用比较严格，得使用@RequestParam注解，否则不知道为什么会报POST xxx错误
    Long getOrderIdByNumber(@RequestParam("number") String number);

}

package com.osir.aiservice.feign;

import com.osir.takeoutpojo.dto.ShoppingCartDTO;
import com.osir.takeoutpojo.entity.ShoppingCart;
import com.osir.takeoutpojo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "cart-service", path = "/cart/user/shoppingCart")
public interface CartServiceFeignClient {

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    Result add(@RequestBody ShoppingCartDTO shoppingCartDTO);

    /**
     * 批量添加到购物车
     * @param shoppingCartList
     * @return
     */
    @PostMapping("/insertBatch")
    Result insertBatch(@RequestBody List<ShoppingCart> shoppingCartList);

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    Result list();

    /**
     * 从购物车删除菜品(&口味)或套餐
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    Result clean();

}

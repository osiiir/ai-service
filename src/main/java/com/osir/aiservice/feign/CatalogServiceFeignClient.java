package com.osir.aiservice.feign;

import com.osir.takeoutpojo.entity.Dish;
import com.osir.takeoutpojo.entity.Setmeal;
import com.osir.takeoutpojo.result.Result;
import com.osir.takeoutpojo.vo.DishItemVO;
import com.osir.takeoutpojo.vo.DishVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "catalog-service", path = "/catalog")
public interface CatalogServiceFeignClient {

    /**
     * 查询所有分类
     * @param type 分类类型：1 菜品分类 2 套餐分类
     * @return
     */
    @GetMapping("/user/category/list")
    Result listCategory(@RequestParam("type") Integer type);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/user/dish/list")
    Result listDish(@RequestParam("categoryId") Long categoryId);

    /**
     * 条件查询
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/user/setmeal/list")
    Result listSetmeal(@RequestParam("categoryId") Long categoryId);

    /**
     * 根据套餐id查询包含的菜品列表
     * @param id
     * @return
     */
    @GetMapping("/user/setmeal/dish/{id}")
    Result dishList(@PathVariable("id") Long id);

}

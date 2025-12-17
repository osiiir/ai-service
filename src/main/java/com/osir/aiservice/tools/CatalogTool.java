package com.osir.aiservice.tools;

import com.osir.aiservice.feign.CatalogServiceFeignClient;
import com.osir.takeoutpojo.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Component("catalogTool")
public class CatalogTool {

    private final CatalogServiceFeignClient catalogServiceFeignClient;

    @Tool(description = "查询所有分类")
    Result listCategory(@ToolParam (required = false, description = "分类类型：1 菜品分类 2 套餐分类") Integer type){
        log.info("AI_ORDERING_ASSISTANT---查询所有分类:{}", type);
        return catalogServiceFeignClient.listCategory(type);
    }

    @Tool(description = "根据分类id查询菜品")
    Result listDish(@ToolParam (required = false, description = "分类id") Long categoryId){
        log.info("AI_ORDERING_ASSISTANT---根据分类id查询菜品:{}", categoryId);
        return catalogServiceFeignClient.listDish(categoryId);
    }

    @Tool(description = "根据分类id查询套餐")
    Result listSetmeal(@ToolParam (required = false, description = "分类id") Long categoryId){
        log.info("AI_ORDERING_ASSISTANT---根据分类id查询套餐:{}", categoryId);
        return catalogServiceFeignClient.listSetmeal(categoryId);
    }

    @Tool(description = "根据套餐id查询包含的菜品列表")
    Result dishList(@ToolParam (required = false, description = "套餐id") Long id){
        log.info("AI_ORDERING_ASSISTANT---根据套餐id查询包含的菜品列表:{}", id);
        return catalogServiceFeignClient.dishList(id);
    }

}

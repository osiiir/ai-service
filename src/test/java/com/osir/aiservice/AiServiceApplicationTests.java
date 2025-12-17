package com.osir.aiservice;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class AiServiceApplicationTests {


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("age",18);
        map.put("sex","male");
        map.put("name","osir");
        map.put("hobby","football");

        String jsonStr = JSONUtil.toJsonStr(map);
        Map<String,Object> bean = JSONUtil.toBean(jsonStr, Map.class);
        System.out.println(bean);
        Set<String> strings = bean.keySet();
        for (String string : strings) {
            System.out.println(string);
        }

    }
    @Test
    void commonTest() {


    }

}

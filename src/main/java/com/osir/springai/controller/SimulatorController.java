package com.osir.springai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/ai")
@RestController
public class SimulatorController {

    /**
     * 哄哄模拟器游戏
     * @return
     */
    @GetMapping(value = "/game", produces = "text/html; charset=utf-8")
    public Flux<String> chat(){
        return "hello world";
    }

}

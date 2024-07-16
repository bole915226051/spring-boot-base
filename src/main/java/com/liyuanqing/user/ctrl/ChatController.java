package com.liyuanqing.user.ctrl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {
    @PostMapping(value = "/chatDemo", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter chatDemo(@RequestBody JSONObject req) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    sseEmitter.send(SseEmitter.event().data(i));
                    log.info("sseEmitter.send([{}]);", i);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sseEmitter.complete();
        }).start();
        return sseEmitter;
    }
}

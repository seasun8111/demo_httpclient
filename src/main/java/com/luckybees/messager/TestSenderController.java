package com.luckybees.messager;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
public class TestSenderController {

    @Resource
    private SenderWithCallback sendWithCallback;



    @RequestMapping(value="/orgInterface")
    public String orgInterface(@RequestBody String s) {
        System.out.println(s);
        return "ok";
    }

    @RequestMapping(value="/send")
    public String sendMessage(@RequestParam(name="withCallback",required = false) boolean withCallback){

        sendWithCallback.send();

        return "Success";
    }

    @RequestMapping(value="/sendWithRetry")
    public String sendMessageWithRetry(@RequestParam(name="withRetry",required = false) boolean withCallback){

        sendWithCallback.send();

        return "Success";
    }




}
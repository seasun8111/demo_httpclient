package com.luckybees.demo_httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckybees.beichen.dto.CallbackDTO;
import com.luckybees.beichen.dto.CallbackDTOBizParams;
import com.luckybees.beichen.dto.OrganResponseDTO;
import com.luckybees.commons.AesECBUtil;
import com.luckybees.commons.POJOConverter;
import com.luckybees.commons.RSACoderUtil;
import com.luckybees.demo_mysql.dao.ProductRepository;
import com.luckybees.demo_mysql.po.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/testHttpClient", produces = "application/json;charset=UTF-8")
@Slf4j
public class TestHttpClient {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    RabbitTemplate restTemplate;


    @Autowired
    ProductRepository productRepository;


    /*
    |参数 |值类型| 必传| 备注
    |:------------- |:---------------:| -------------:| :-------------:|
    success| boolean |Y |接⼝处理是否成功
    code| string| Y| 失败码
    msg |string |Y| 失败原因
    data| object| N| 业务数据
    costTime| long |Y |响应时⻓，毫秒
    */
    @Value("${crypto.pub_key}")
    String pubKey;



    @Autowired
    POJOConverter pojoConverter;

    @RequestMapping(value="/organInterface", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public OrganResponseDTO organInterface(@RequestBody CallbackDTO json) {

        log.info("收到json");
        ObjectMapper mapper = new ObjectMapper();
        OrganResponseDTO organResponseDTO = new OrganResponseDTO();
        organResponseDTO.setCode("500");
        organResponseDTO.setCostTime(233L);
        organResponseDTO.setMsg("失败");
        organResponseDTO.setSuccess(Boolean.FALSE);
        System.out.println(json);
        log.info("打印原始JSON========"+json);
        try {

            String bizParamsEn = json.getBizParams();
            String aesKeyEn = json.getAesKey();
            String productId = json.getProductId();
            Product product = productRepository.findByVKey(productId);
            String publicKey = product.getRsaPublicKey();
            String aesKeyDe = RSACoderUtil.decryptAesKey(aesKeyEn, publicKey );
            String bizParamDe = AesECBUtil.decrypt(bizParamsEn, aesKeyDe );
            log.info(bizParamDe);
            ObjectMapper objectMapper = new ObjectMapper();
            CallbackDTOBizParams callbackDTOBizParams =
                    objectMapper.readValue(bizParamDe, CallbackDTOBizParams.class);

            System.out.println(callbackDTOBizParams);
            String needSign = bizParamDe+json.getTimestamp();
            Boolean verifyResult = RSACoderUtil.verify(
                    needSign.getBytes(),
                    product.getRsaPublicKey(),
                    json.getSign());
            log.info("====  verifyResult  ====  "+verifyResult);

            if(verifyResult) {
                organResponseDTO.setCode("200");
                organResponseDTO.setCostTime(233L);
                organResponseDTO.setMsg("成功");
                organResponseDTO.setSuccess(Boolean.TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("返回： " + organResponseDTO);
            return organResponseDTO;
        }
    }


    @RequestMapping(value="/organInterfaceFailed")
    public OrganResponseDTO organInterfaceFailed(@RequestBody String json) {

        if(retryTime < 8) {
            retryTime++;
            OrganResponseDTO organResponseDTO = new OrganResponseDTO();
            organResponseDTO.setCode("500");
            organResponseDTO.setCostTime(233L);
            organResponseDTO.setMsg("失败");
            organResponseDTO.setSuccess(Boolean.FALSE);
            System.out.println(organResponseDTO);
            return organResponseDTO;
        } else {
            OrganResponseDTO organResponseDTO = new OrganResponseDTO();
            organResponseDTO.setCode("200");
            organResponseDTO.setCostTime(233L);
            organResponseDTO.setMsg("成功");
            organResponseDTO.setSuccess(Boolean.TRUE);
            System.out.println(organResponseDTO);
            return organResponseDTO;
        }
    }


    int retryTime = 0;



    /*
    |参数 |值类型| 必传| 备注
    |:------------- |:---------------:| -------------:| :-------------:|
    success| boolean |Y |接⼝处理是否成功
    code| string| Y| 失败码
    msg |string |Y| 失败原因
    data| object| N| 业务数据
    costTime| long |Y |响应时⻓，毫秒
    */

    @RequestMapping(value="/organInterfaceRetrytest")
    public String organInterfaceRetrytest(@RequestBody  String json)  {



//        System.out.println("模拟机构收到 》》 "+json);
        System.out.println(json);


        if(retryTime < 8) {
            retryTime++;
        } else {
            return  "      ok    ";

        }

        return "";
    }


}

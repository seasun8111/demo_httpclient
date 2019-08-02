package com.luckybees.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckybees.beichen.dto.CallbackDTO;
import com.luckybees.beichen.dto.CallbackDTOBizParams;
import com.luckybees.beichen.dto.MsgDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class POJOConverter {


    @Value("${crypto.prv_key}")
    private String privateKey;



    public CallbackDTO msgDTO2CallBackDTO(MsgDTO msgDTO) throws Exception {
        CallbackDTO callbackDTO =  new CallbackDTO();
        CallbackDTOBizParams callbackDTOBizParams = new CallbackDTOBizParams();
        callbackDTOBizParams.setIdCard(msgDTO.getIdCard());
        callbackDTOBizParams.setMobile(msgDTO.getMobile());
        callbackDTOBizParams.setName(msgDTO.getName());
        callbackDTOBizParams.setOrganizationId(msgDTO.getOrganizationKey());
        callbackDTOBizParams.setProductId(msgDTO.getProductKey());
        callbackDTOBizParams.setDecisions(msgDTO.getDecision());
        callbackDTOBizParams.setSn(msgDTO.getSn());
        ObjectMapper objectMapper =new ObjectMapper();
        callbackDTO.setBizParams(objectMapper.writeValueAsString(callbackDTOBizParams));
        callbackDTO.setTimestamp(java.util.Calendar.getInstance().getTimeInMillis());
        ObjectMapper mapper = new ObjectMapper();

        String callbackDTOBizParamsString = mapper.writeValueAsString(callbackDTOBizParams);



        String sign = RSACoderUtil.getSign(new String[]{callbackDTOBizParamsString, String.valueOf(callbackDTO.getTimestamp())},  privateKey);

        callbackDTO.setSign(sign);

        return callbackDTO;
    }
}

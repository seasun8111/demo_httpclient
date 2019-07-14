package com.luckybees.beichen.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/*
*
*
 {
    "organizationId":"机构ID",
    "productId":"产品号",
    "sn":"流水号",
    "name","姓名",
    "mobile_number":"手机号",
    "id_card":"身份证",
    "decisions":
        {
            "score":514,
            "result":"pass"|"deny",
        },
    "timestamp": 1562227102000
}
*
* */

@Data
@NoArgsConstructor
public class CallbackDTOBizParams implements Serializable {

    private String organizationId;
    private String productId;
    private String sn;
    private String name;
    private String mobile;
    private String idCard;
    private HashMap decisions;

}
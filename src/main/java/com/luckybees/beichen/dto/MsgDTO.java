package com.luckybees.beichen.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/*
*
*

{
	"organizationKey":"机构key",
	"productKey":"产品key",
	"sn":"流水号",
	"name","姓名",
	"mobile_number":"手机号",
	"id_card":"身份证",
	"decision":
		{
			"score":232,
			"result":pass,
		},
	"timestamp": 1562227102000 #推送时间
}

*
* */
@Data
@NoArgsConstructor
public class MsgDTO {

    private String ref;
    private  String organizationKey;
    private  String productKey;
    private  String sn;
    private  String name;
    private  String mobile;
    private  String idCard;
    private HashMap decision;
    private Long timestamp;


}

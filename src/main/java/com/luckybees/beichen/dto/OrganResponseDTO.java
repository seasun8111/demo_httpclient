package com.luckybees.beichen.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/*
    |参数 |值类型| 必传| 备注
    |:------------- |:---------------:| -------------:| :-------------:|
    success| boolean |Y |接⼝处理是否成功
    code| string| Y| 失败码
    msg |string |Y| 失败原因
    data| object| N| 业务数据
    costTime| long |Y |响应时⻓，毫秒
    */


@Data
@NoArgsConstructor
public class OrganResponseDTO {

    Boolean success;
    String code;
    String msg;
    Object data;
    Long costTime;


}

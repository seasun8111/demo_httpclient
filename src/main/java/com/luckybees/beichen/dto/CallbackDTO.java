package com.luckybees.beichen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class CallbackDTO implements Serializable {

    private String sign;
    private Long timestamp;

    private CallbackDTOBizParams bizParams;

}

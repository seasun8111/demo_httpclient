package com.luckybees.beichen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class CallbackDTO implements Serializable {


    private String productId;
    private String aesKey;
    private String sign;
    private Long timestamp;

    private String bizParams;

}

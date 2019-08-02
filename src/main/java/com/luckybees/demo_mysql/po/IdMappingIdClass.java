package com.luckybees.demo_mysql.po;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IdMappingIdClass implements Serializable {

    private String vId;
    private String vKey;
    private String type;

}

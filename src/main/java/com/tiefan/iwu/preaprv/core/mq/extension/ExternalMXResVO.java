package com.tiefan.iwu.preaprv.core.mq.extension;

import com.tiefan.iwu.preaprv.enums.CreditRuleEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExternalMXResVO implements Serializable {
    private static final long serialVersionUID = 4094988898745310196L;
//    private static final long serialVersionUID = 4094982L;
    private int score = 0;
    private int ispDataExpire = 0;
    private String result = CreditRuleEnum.UNKNOWN.toString();
    private String errCode;
    private String errMsg;
    private String requestNo;
    private String taskId;
    private String ref;
    private String name;
    private String mobile;
    private String idCard;
}

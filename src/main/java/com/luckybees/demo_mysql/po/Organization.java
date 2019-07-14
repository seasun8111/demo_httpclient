package com.luckybees.demo_mysql.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 定远
 * @date 2019/7/9 16:07
 */

@Entity(name="beichen_organization_info")
@Table(name = "beichen_organization_info")
@Data
@NoArgsConstructor
public class Organization implements Serializable {
    private static final long serialVersionUID = 3537921742065870582L;

    @Id
    @Column(length = 16)
    private String id;


    @Column(length = 16)
    private String name;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Column(length = 256)
    private String callbackUrl;

}
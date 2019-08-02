package com.luckybees.demo_mysql.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;


@Entity(name="beichen_product_info")
@Table(name = "beichen_product_info")
@Data
@NoArgsConstructor
public class Product {

    private static final long serialVersionUID = 3537921742065870583L;

    @Id
    @Column(length = 16)
    private String id;

    @Column(length = 16, nullable = false)
    private String name;

    @JoinColumn(name="organizationId")
    @ManyToOne
    private Organization organization;

    @Column(length = 256, nullable = false)
    private String callbackUrl;

    @Column(length = 256, nullable = false)
    private String comments;

    @Column(length = 16, nullable = false)
    private String vKey;


    @Column(length = 200, nullable = false)
    private String rsaPublicKey;

    @Column(length = 500, nullable = false)
    private String rsaPrivateKey;

    @Column(length = 128, nullable = false)
    private String aesKey;



    @NonNull
//    @Column(name="createDate", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    @Column(name="createDate", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '建立时间'")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @NonNull
//    @Column(name="updateDate", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    @Column(name="updateDate", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

}

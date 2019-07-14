package com.luckybees.demo_mysql.po;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(length = 16)
    private String name;

    @JoinColumn(name="organizationId")
    @ManyToOne
    private Organization organization;

    @Column(length = 256)
    private String callbackUrl;

    @Column(length = 256)
    private String comments;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

}

package com.luckybees.demo_mysql.po;

import com.luckybees.demo_mysql.bizStatus.LoanHistory;
import com.luckybees.demo_mysql.bizStatus.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 定远
 * @date 2019/7/9 16:07
 */

@Entity(name="beichen_report_process_status")
@Table(name = "beichen_report_process_status",
        indexes = {
            @Index(name="ref_index_name",
                columnList = "ref",
                unique = true),
            @Index(name="status_index_name",
                    columnList = "status"),
            @Index(name="result_index_name",
                    columnList = "result")})
@Data
@NoArgsConstructor
public class ReportProcessStatus implements Serializable {
    private static final long serialVersionUID = 3537921742065870581L;

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, columnDefinition = "bigint(20) NOT NULL AUTO_INCREMENT")
    private Long id;

    @NonNull
    @Column(length = 64, nullable = false)
    private String ref;
    //FIRST | AGAIN

    @NonNull
    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanHistory loanHistory;

    @NonNull
    @ManyToOne
    @JoinColumn(name="organizationId", nullable = false)
    private Organization organization;

    @NonNull
    @ManyToOne
    @JoinColumn(name="productId", nullable = false)
    private Product product;

    @NonNull
    @Column(length = 128, nullable = false)
    private String channelName;

    @NonNull
    @Column(length = 64, nullable = false)
    private String sn;

    @NonNull
    @Column(length = 16, nullable = false)
    private String name;

    @NonNull
    @Column(length = 32, nullable = false)
    private String mobile;


    @Column(length = 64, nullable = false)
    private String idCard;

    @NonNull
    @Column(length = 256, nullable = false)
    private String fileUrl;

    @NonNull
    @Column(length = 16, nullable = false, columnDefinition="varchar(16) DEFAULT 'PADDING'")
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(nullable = false)
    private Integer score;

    @Column(length = 16, nullable = false, columnDefinition="varchar(16) DEFAULT 'PADDING'")
    private String result;


//    @Column(name="createDate", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    @Column(name="createDate", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '建立时间'")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

//    @Column(name="updateDate", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    @Column(name="updateDate", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;
}
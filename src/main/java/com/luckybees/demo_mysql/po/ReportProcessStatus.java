package com.luckybees.demo_mysql.po;

import com.luckybees.demo_mysql.bizStatus.LoanHistory;
import com.luckybees.demo_mysql.bizStatus.ReportStatus;
import lombok.*;

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
    private Long id;


    @NonNull
    @Column(length = 64)
    private String ref;
    //FIRST | AGAIN
    @NonNull
    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    private LoanHistory loanHistory;
    @NonNull
    @ManyToOne
    @JoinColumn(name="organizationId")
    private Organization organization;
//    @NonNull
//    @Column(length = 16)
//    private String organizationId;
//    @NonNull
//    @Column(length = 16)
//    private String productId;
    @NonNull
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    @NonNull
    @Column(length = 16)
    private String sn;
    @NonNull
    @Column(length = 16)
    private String name;
    @NonNull
    @Column(length = 32)
    private String mobile;
    @NonNull
    @Column(length = 32)
    private String idCard;
    @NonNull
    @Column(length = 256)
    private String fileUrl;
    @NonNull
    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column
    private Integer score;
    @Column
    private String result;
    @NonNull
    @Column
    private Date createDate;
    @Column
    private Date updateDate;



}
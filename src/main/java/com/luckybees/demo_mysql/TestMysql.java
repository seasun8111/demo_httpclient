package com.luckybees.demo_mysql;

import com.luckybees.demo_mysql.bizStatus.LoanHistory;
import com.luckybees.demo_mysql.bizStatus.ReportStatus;
import com.luckybees.demo_mysql.dao.OrganizationRepository;
import com.luckybees.demo_mysql.dao.ProductRepository;
import com.luckybees.demo_mysql.dao.ReportRepository;
import com.luckybees.demo_mysql.po.Organization;
import com.luckybees.demo_mysql.po.Product;
import com.luckybees.demo_mysql.po.ReportProcessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/testDb", produces = "application/json;charset=UTF-8")
public class TestMysql {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value="/save", produces = "application/json;charset=UTF-8")
    public String save(){


        Organization organization =new Organization();
        Product product = new Product();


        organization.setId("JG00001");
        organization.setCallbackUrl("https://www.baidu.com");
        organization.setName("机构名1");
        organization.setCreateDate(new java.util.Date());


        organizationRepository.saveAndFlush(organization);

        product.setCallbackUrl("https://www.baidu.com");
        product.setId("CP00001");
        product.setName("洋钱罐");
        product.setCreateDate(new java.util.Date());
        product.setComments("Comments");
        product.setOrganization(organization);


        productRepository.saveAndFlush(product);


        ReportProcessStatus cr = new ReportProcessStatus();
        cr.setIdCard("222");
        cr.setFileUrl("222");
        cr.setLoanHistory(LoanHistory.FIRST);
        cr.setCreateDate(new java.util.Date());
        cr.setRef("ref");
        cr.setMobile("13777777777");
        cr.setName("vincent");

        cr.setOrganization(organization);
        cr.setProduct(product);
//        cr.setOrganizationId("ornaization id");
//        cr.setProductId("porductId");
        cr.setResult("PASS");
        cr.setScore(2343);
        cr.setStatus(ReportStatus.PADDING);
        cr.setSn("snsnsns");


        reportRepository.saveAndFlush(cr);
        reportRepository.findAll().forEach(x ->{
            System.out.println(x);
        });

        return "";
    }



    @RequestMapping(value="/update", produces = "application/json;charset=UTF-8")
    public String update(){

        ReportProcessStatus cr = reportRepository.getOne((long) 1);

        cr.setStatus(ReportStatus.DONE);
        reportRepository.saveAndFlush(cr);



        return reportRepository.getOne(1L).toString();

    }


}

package com.luckybees.demo_mysql.dao;

import com.luckybees.demo_mysql.po.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
package com.luckybees.demo_mysql.dao;

import com.luckybees.demo_mysql.po.ReportProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportProcessStatus, Long> { }


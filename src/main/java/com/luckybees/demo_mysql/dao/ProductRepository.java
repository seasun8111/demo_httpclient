package com.luckybees.demo_mysql.dao;

import com.luckybees.demo_mysql.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> { }

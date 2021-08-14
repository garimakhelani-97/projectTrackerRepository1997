package com.smrc.mdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
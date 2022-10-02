package com.crud.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.example.model.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long>{

	void deleteBycustomerid(long customerid);

	Optional<CustomerDetails> findByCustomerid(long customerid);

	CustomerDetails findBycustomerid(long customerid);

}

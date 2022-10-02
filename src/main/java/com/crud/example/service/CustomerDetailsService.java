package com.crud.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.example.model.CustomerDetails;
import com.crud.example.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
	
	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	
	public void save(CustomerDetails details ) {
		
		customerDetailsRepository.save(details);
	}
	
	// get all
	public List<CustomerDetails> listAll(){
		
		return (List<CustomerDetails>) customerDetailsRepository.findAll();		
	}
			
	//delete by id
	public void deleteByCustomerid(long customerid) {
		
		customerDetailsRepository.deleteBycustomerid(customerid);
	}
			
			//getA single customer details
			public Optional<CustomerDetails> findByCustomerid(long customerid) {
				
				return customerDetailsRepository.findByCustomerid(customerid);
			}
			
			// update 
			
			public CustomerDetails update(long customerid,CustomerDetails details)
			{
				
				CustomerDetails custdetails=customerDetailsRepository.findBycustomerid(customerid);
				custdetails.setCustomername(details.getCustomername());
				custdetails.setCustomeremail(details.getCustomeremail());
				custdetails.setCustomernumber(details.getCustomernumber());
				custdetails.setLocation(details.getLocation());
	
				return customerDetailsRepository.save(custdetails);
			}
			
			public void delete()
			{
				customerDetailsRepository.deleteAll();
			}

}

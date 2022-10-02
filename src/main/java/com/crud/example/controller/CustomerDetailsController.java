package com.crud.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.example.exception.BadRequestException;
import com.crud.example.model.CustomerDetails;
import com.crud.example.repository.CustomerDetailsRepository;
import com.crud.example.service.CustomerDetailsService;

@RestController
public class CustomerDetailsController {

	@Autowired
	 private CustomerDetailsService customerDetailsService;
	
	@Autowired
	private CustomerDetailsRepository repo;
	
	@RequestMapping(value = "/AddDetails",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createEmployee(@RequestBody CustomerDetails customerDetails) {
		
		try {
			Optional<CustomerDetails> details=repo.findByCustomerid(customerDetails.getCustomerid());
			if(!details.isPresent())
			{
				customerDetailsService.save(customerDetails);
			return new ResponseEntity<>("successfully created",HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("employee already exists",HttpStatus.CONFLICT);
			}
		}
		catch(BadRequestException e) {
			 return new ResponseEntity<>("you are trying to add null value",HttpStatus.BAD_REQUEST);
		}
		catch(DataIntegrityViolationException e) {
			//throw new DataIntegrityViolationException("customer already exists",e);
			return new ResponseEntity<>("employee already exists with this mail id ",HttpStatus.CONFLICT);
		}
	
	}
	

	@RequestMapping(value = "/listAll",method=RequestMethod.GET)
	public List<CustomerDetails> listAll()
	{
		if(customerDetailsService.listAll()==null)
		{
			throw new NullPointerException("no employee were found");
		}
		System.out.println("getting records");
		
		return customerDetailsService.listAll();
	}
	
	@RequestMapping(value = "/deleteAll",method=RequestMethod.DELETE)
	public String deleteAll()
	{
		customerDetailsService.delete();
		return "all records deleted successfully";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateEmployee(@RequestBody CustomerDetails details, @RequestParam long customerid ) {
		customerDetailsService.update(customerid, details);
			return new ResponseEntity<>("updated successfully",HttpStatus.OK);			
			
		}	
	//get single customer details
			@RequestMapping(value = "/getCustomer",method=RequestMethod.PUT)
			public CustomerDetails getCustomer(@RequestParam long customerid) {
				System.out.println("getting record!!!!");
				return customerDetailsService.findByCustomerid(customerid)
					.orElseThrow(()-> new UsernameNotFoundException("Customer Does not exist wit the id   "+customerid));
			}
			

			//delete single employee details
			@RequestMapping(value = "/deletecustomer",method=RequestMethod.DELETE)
			public ResponseEntity<?> delete(@RequestParam long customerid) {
				Optional<CustomerDetails> details=customerDetailsService.findByCustomerid(customerid);
				if(details.isPresent())
				{
					customerDetailsService.deleteByCustomerid(customerid);
				return new ResponseEntity<>("deleted succesfully ",HttpStatus.OK);
				}
				else {
				return new ResponseEntity<>("employee not found ",HttpStatus.NOT_FOUND);
				}
			}
}

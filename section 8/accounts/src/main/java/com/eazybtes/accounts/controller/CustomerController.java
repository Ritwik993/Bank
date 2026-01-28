package com.eazybtes.accounts.controller;

import com.eazybtes.accounts.dto.CustomerDetailsDto;
import com.eazybtes.accounts.service.ICustomersService;
import com.eazybtes.accounts.service.impl.CustomersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {
    private ICustomersService iCustomersService;

    public  CustomerController(ICustomersService iCustomersService){
        this.iCustomersService=iCustomersService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(String mobileNumber){
        CustomerDetailsDto customerDetailsDto=iCustomersService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}

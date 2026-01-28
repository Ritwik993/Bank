package com.eazybtes.accounts.service.impl;

import com.eazybtes.accounts.dto.AccountsDto;
import com.eazybtes.accounts.dto.CustomerDetailsDto;
import com.eazybtes.accounts.dto.LoansDto;
import com.eazybtes.accounts.entity.Accounts;
import com.eazybtes.accounts.entity.Customer;
import com.eazybtes.accounts.exception.ResourceNotFoundException;
import com.eazybtes.accounts.mapper.AccountsMapper;
import com.eazybtes.accounts.mapper.CustomerMapper;
import com.eazybtes.accounts.repository.AccountsRepository;
import com.eazybtes.accounts.repository.CustomerRepository;
import com.eazybtes.accounts.service.ICustomersService;
import com.eazybtes.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {
    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts","customerId",customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        AccountsDto accountsDto= AccountsMapper.mapToAccountsDto(accounts,new AccountsDto());
        customerDetailsDto.setAccountsDto(accountsDto);
        LoansDto loansDto=loansFeignClient.fetchLoanDetails(mobileNumber).getBody();
        customerDetailsDto.setLoansDto(loansDto);
        return customerDetailsDto;
    }
}

package com.eazybtes.accounts.service.impl;

import com.eazybtes.accounts.constants.AccountsConstants;
import com.eazybtes.accounts.dto.AccountsDto;
import com.eazybtes.accounts.dto.CustomerDto;
import com.eazybtes.accounts.entity.Accounts;
import com.eazybtes.accounts.entity.Customer;
import com.eazybtes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybtes.accounts.exception.ResourceNotFoundException;
import com.eazybtes.accounts.mapper.AccountsMapper;
import com.eazybtes.accounts.repository.AccountsRepository;
import com.eazybtes.accounts.repository.CustomerRepository;
import com.eazybtes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.eazybtes.accounts.mapper.CustomerMapper.mapToCustomer;
import static com.eazybtes.accounts.mapper.CustomerMapper.mapToCustomerDto;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer=mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already exists with the given mobile number "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        Accounts accounts=createNewAccount(savedCustomer);
        accountsRepository.save(accounts);
    }



    private Accounts createNewAccount(Customer customer){
        Accounts accounts=new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber=1000000000L+new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("Anonymous");
        return accounts;
    }



    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Accounts","Customer Id",customer.getCustomerId().toString())
        );

        CustomerDto customerDto=mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        return customerDto;
    }


}

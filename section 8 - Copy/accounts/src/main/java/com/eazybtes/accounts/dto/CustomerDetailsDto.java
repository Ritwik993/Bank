package com.eazybtes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDetailsDto {
    @NotEmpty(message = "Name cannot be empty or null")
    @Size(min=5,max = 30, message = "The name of customer should be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;

    private LoansDto loansDto;
}

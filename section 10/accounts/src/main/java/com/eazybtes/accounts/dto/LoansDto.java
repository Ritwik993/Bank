package com.eazybtes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {
    @NotEmpty(message = "Mobile number cannot be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be empty or null")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Loan number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty or null")
    private String loanType;

    @Positive(message = "Total loan should be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Total amount paid should be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private int outstandingAmount;
}

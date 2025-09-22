package com.empresa.toolsapi.validation;

import com.empresa.toolsapi.entity.Customer;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.repository.CustomerRepository;
import com.empresa.toolsapi.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidation {

    private final CustomerRepository customerRepository;

    public Customer existsDni(String dni){
        return customerRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.DNI_UNREGISTERED));
    }
}

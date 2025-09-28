package com.empresa.toolsapi.config;

import com.empresa.toolsapi.entity.Customer;
import com.empresa.toolsapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        //initCustomer();
    }

    private void initCustomer(){
        if(customerRepository.count() == 0){

            Customer customer1 = Customer.builder()
                    .dni("33344455")
                    .fullName("Pedro Moreno")
                    .phone("909012345")
                    .email("prueba@gmail.com")
                    .address("Esta es mi direccion actual")
                    .build();

            customerRepository.save(customer1);
        }
    }

}

package com.empresa.toolsapi.config;

import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Customer;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.repository.CategoryRepository;
import com.empresa.toolsapi.repository.CustomerRepository;
import com.empresa.toolsapi.repository.SectionRepository;
import com.empresa.toolsapi.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final SectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository personRepository;
    private final TicketRepository ticketRepository;



    @Override
    public void run(String... args) throws Exception {
/*
        initSections();
        initCategories();
        initCustomer();
        //initTicket();

 */
    }

    private void initTicket(){
        //ticketRepository.deleteAll();
    }
    private void initSections(){
        if (sectionRepository.count() == 0){

            Section s1 = Section.builder()
                    .name("A-1")
                    .description("primera")
                    .build();
            sectionRepository.save(s1);
        }
    }
    private void initCategories(){
        if (categoryRepository.count() == 0){

            Category c1 = Category.builder()
                    .name("Manual")
                    .build();

            categoryRepository.save(c1);
        }
    }

    private void initCustomer(){
        if(personRepository.count() == 0){

            Customer customer1 = Customer.builder()
                    .dni("33344455")
                    .fullName("Pedro Moreno")
                    .phone("909012345")
                    .email("prueba@gmail.com")
                    .address("Esta es mi direccion actual")
                    .build();

            personRepository.save(customer1);
        }
    }

}

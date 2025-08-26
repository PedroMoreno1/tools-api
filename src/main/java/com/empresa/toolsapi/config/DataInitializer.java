package com.empresa.toolsapi.config;

import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Person;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.repository.CategoryRepository;
import com.empresa.toolsapi.repository.PersonRepository;
import com.empresa.toolsapi.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;
    private final PersonRepository personRepository;
    /*private final ToolTicketRepository ticketRepository;*/

    @Override
    public void run(String... args) throws Exception {

        initSections();
        initCategories();
        initPerson();
        /*deleteTicket();*/
    }

    /*private void deleteTicket(){
        if (ticketRepository.count() >= 1){
            ticketRepository.deleteAll();
        }
    }*/
    private void initSections(){
        if (sectionRepository.count() == 0){

            Section s1 = Section.builder()
                    .name("A-1")
                    .description("no tiene, ok?")
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

    private void initPerson(){
        if(personRepository.count() == 0){

            Person p1 = Person.builder()
                    .dni("33344455")
                    .name("Pedro")
                    .build();

            personRepository.save(p1);
        }
    }
}

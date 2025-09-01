package com.empresa.toolsapi.validation;

import com.empresa.toolsapi.entity.Person;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonValidation {

    private final PersonRepository personRepository;

    public Person existsPerson(String dni){
        return personRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no registrada"));
    }
}

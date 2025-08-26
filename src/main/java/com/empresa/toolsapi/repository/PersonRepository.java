package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByDni(String dni);
}

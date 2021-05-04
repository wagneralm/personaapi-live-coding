package com.wagner.personapi.controller;

import com.wagner.personapi.dto.request.PersonDTO;
import com.wagner.personapi.dto.response.MessageResponseDTO;
import com.wagner.personapi.exception.PersonNotFoundException;
import com.wagner.personapi.repository.PersonRepository;
import com.wagner.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@AllArgsConstructor))// cria todos os construtores
public class PersonController {

    private PersonRepository personRepository;

    private PersonService personService;

//    @Autowired -> @AllArgsConstructor(onConstructor = @__(@AllArgsConstructor))
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}

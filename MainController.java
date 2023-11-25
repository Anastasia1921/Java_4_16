package ru.myHome.untitled_4_16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

//    @PostMapping("/persons/{id}")
//    public Person updateNewPerson(@PathVariable int id, @RequestBody Person person) {
//        Person person2 = new Person(person.getId(), person.getFirstname(), person.getSurname(),person.getLastname(),person.getBirthday());
//        repository.save(person2);
//        return person2;
//    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        if (status == HttpStatus.OK ) {
            Person person2 = repository.findById(id).get();
            person2.setFirstname(person.getFirstname());
            person2.setSurname(person.getSurname());
            person2.setLastname(person.getLastname());
            person2.setBirthday(person.getBirthday());
            return new ResponseEntity(repository.save(person2), status);
        }
        else {
            Person person2 = new Person(person.getFirstname(), person.getSurname(), person.getLastname(), person.getBirthday());
            return new ResponseEntity(repository.save(person2), status);
        }
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }
}
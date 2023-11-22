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

//    private List<Person> persons = new ArrayList<>(Arrays.asList(
//            new Person(1, "Ivan", "Ivanovich", "Ivanov", LocalDate.of(1999, 2,3)),
//            new Person(2, "Петр", "Петрович", "Петров", LocalDate.of(2002, 2,2)),
//            new Person(3, "Евгений", "Васильевич", "Васин", LocalDate.of(2005, 4,8)),
//            new Person(4, "Максим", "Яковлевич", "Окопский", LocalDate.of(1978, 6,5))
//    ));

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
        repository.deleteById(id);
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(person), status);
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
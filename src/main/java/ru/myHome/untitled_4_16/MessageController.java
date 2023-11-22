package ru.myHome.untitled_4_16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;
//    private Message message;
//
//    private List<Message> messages = new ArrayList<>(Arrays.asList(
//            new Message(1, "OK", "All good", LocalDateTime.of(1999, 2,3, 10, 32, 00)),
//            new Message(2, "ERROR", "It is error", LocalDateTime.of(2002, 2,2, 12, 45, 00)),
//            new Message(3, "HELP", "For help read the tutorial", LocalDateTime.of(2005, 4,8, 21, 30, 00)),
//            new Message(4, "DAMAGE", "everything is bad, nothing works", LocalDateTime.of(1978, 6,5, 22, 00, 00))
//    ));

    //получение сообщения по id
    @GetMapping("/messages/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }

    //добавление нового сообщения
    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }


    //удаление сообщения по id
    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }


    //обновление информации в сообщении по id
    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        repository.deleteById(id);  // ???
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(message), status);
    }

    //вывести список сообщений
    @GetMapping("/messages")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/message")
    public String helloMessage() {
        return "Its message!";
    }

}

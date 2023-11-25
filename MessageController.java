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
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        if (status == HttpStatus.OK ) {
            Message message2 = repository.findById(id).get();
            message2.setText(message.getText());
            message2.setTitle(message.getTitle());
            message2.setTime(message.getTime());
            return new ResponseEntity(repository.save(message2), status);
        }
        else {
            Message message2 = new Message(message.getText(), message.getTitle(), message.getTime());
            return new ResponseEntity(repository.save(message2), status);
        }
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

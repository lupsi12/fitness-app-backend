package com.example.demo.controllers;

import com.example.demo.entities.message;
import com.example.demo.requests.MessageCreateRequest;
import com.example.demo.responses.messageResponses;
import com.example.demo.services.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class messageController {
    @Autowired
    private messageService messageService;

    public messageController(messageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping
    public List<messageResponses> getAllMessages(@RequestParam Optional<Long> clientId, @RequestParam Optional<Long> coachId){
        return messageService.getAllMessages(clientId,coachId);
    }
    @PostMapping
    public message createOneMessage(@RequestBody MessageCreateRequest messageCreateRequest){
        return messageService.saveOneMessage(messageCreateRequest);
    }
    @GetMapping("/{messageId}")
    public message getOneMessage(@PathVariable Long messageId){
        return messageService.getOneMessage(messageId);
    }
    @PutMapping("/{messageId}")
    public message updateOneMessage(@PathVariable Long messageId,@RequestBody message newMessage){
        return messageService.updateOneMessage(messageId,newMessage);
    }
    @DeleteMapping("/{messageId}")
    public void  deleteOneMessage(@PathVariable Long messageId){
        messageService.deleteOneMessage(messageId);
    }

}

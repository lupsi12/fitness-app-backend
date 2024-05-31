package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.coach;
import com.example.demo.entities.message;
import com.example.demo.repos.MessageRepository;
import com.example.demo.requests.MessageCreateRequest;
import com.example.demo.responses.messageResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class messageService {
    private MessageRepository messageRepository;
    private clientService clientService;
    private coachService coachService;

    public messageService(MessageRepository messageRepository, com.example.demo.services.clientService clientService, com.example.demo.services.coachService coachService) {
        this.messageRepository = messageRepository;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public List<messageResponses> getAllMessages(Optional<Long> clientId, Optional<Long> coachId) {
        List<message> messages;
        if(clientId.isPresent() && coachId.isPresent()){
            messages = messageRepository.findByClientIdAndCoachId(clientId.get(),coachId.get());
        } else if (clientId.isPresent()) {
            messages = messageRepository.findByClientId(clientId.get());
        }
        else if (coachId.isPresent()) {
            messages = messageRepository.findByCoachId(coachId.get());
        }else
            messages = messageRepository.findAll();
        return messages.stream().map(message -> new messageResponses(message)).collect(Collectors.toList());
    }

    public message saveOneMessage(MessageCreateRequest messageCreateRequest) {
        client client = clientService.getOneClient(messageCreateRequest.getClient_id());
        coach coach = coachService.getOneCoach(messageCreateRequest.getCoach_id());
        if(client != null && coach != null){
            message toSave = new message();
            toSave.setClient(client);
            toSave.setCoach(coach);
            toSave.setMessagetext(messageCreateRequest.getMessagetext());
            toSave.setSend(messageCreateRequest.getSend());
            toSave.setId(messageCreateRequest.getId());
            return messageRepository.save(toSave);
        }else
            return null;
    }
    public message getOneMessage(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public message updateOneMessage(Long messageId, message newMessage) {
        Optional<message> message = messageRepository.findById(messageId);
        if(message.isPresent()){
            message foundMessage = message.get();
            foundMessage.setClient(newMessage.getClient());
            foundMessage.setCoach(newMessage.getCoach());
            foundMessage.setSend(newMessage.getSend()) ;
            foundMessage.setMessagetext(newMessage.getMessagetext());
            messageRepository.save(foundMessage);
            return foundMessage;
        }else
            return null;
    }

    public void deleteOneMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }


}

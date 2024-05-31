package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.user;
import com.example.demo.repos.ClientRepository;
import com.example.demo.requests.ClientCreateRequest;
import com.example.demo.responses.adminResponses;
import com.example.demo.responses.clientResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class clientService {
    private ClientRepository clientRepository;
    private userService userService;

    public clientService(ClientRepository clientRepository, userService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public List<clientResponses> getAllClient(Optional<Long> userId) {
        List<client> list;
        if(userId.isPresent()){
            list = clientRepository.findByUserId(userId);
        }
         else
             list = clientRepository.findAll();
        return list.stream().map(p -> new clientResponses(p)).collect(Collectors.toList());
    }

    public client saveOneClient(ClientCreateRequest clientCreateRequest) {
        user user = userService.getOneUser(clientCreateRequest.getUser_id());
        if(user == null)
            return null;
        client toSave = new client();
        toSave.setUser(user);
        toSave.setId(clientCreateRequest.getId());
        return clientRepository.save(toSave);
    }

    public client getOneClient(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    public client updateOneClient(Long clientId, client newClient) {
        Optional<client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            client foundClient = client.get();
            foundClient.setUser(newClient.getUser());
            clientRepository.save(foundClient);
            return foundClient;
        }
        else
            return null;
    }

    public void deleteOneClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}

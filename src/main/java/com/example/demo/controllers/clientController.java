package com.example.demo.controllers;

import com.example.demo.entities.client;
import com.example.demo.repos.ClientRepository;
import com.example.demo.requests.ClientCreateRequest;
import com.example.demo.responses.clientResponses;
import com.example.demo.services.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class clientController {
    @Autowired
    private clientService clientService;

    public clientController(clientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<clientResponses> getAllClient(@RequestParam Optional<Long> userId){
        return clientService.getAllClient(userId);
    }
    @PostMapping
    public  client createClient(@RequestBody ClientCreateRequest clientCreateRequest){
        return clientService.saveOneClient(clientCreateRequest);
    }
    @GetMapping("/{clientId}")
    public client getOneClient(@PathVariable Long clientId){
        return clientService.getOneClient(clientId);
    }
    @PutMapping("/{clientId}")
    public client updateOneClient(@PathVariable Long clientId ,@RequestBody client newClient){
        return clientService.updateOneClient(clientId,newClient);
    }
    @DeleteMapping("/{clientId}")
    public void deleteOneClient(@PathVariable Long clientId){
        clientService.deleteOneClient(clientId);
    }

}

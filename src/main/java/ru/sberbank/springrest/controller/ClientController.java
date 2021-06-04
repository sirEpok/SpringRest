package ru.sberbank.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.springrest.dao.ClientDAO;
import ru.sberbank.springrest.model.Client;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientDAO clientDAO;

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientDAO.findByID(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity create(@RequestBody Client client) {
        try {
            clientDAO.save(client);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Critical error");
        }
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() {
        final List<Client> clients = clientDAO.findAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity delete(@PathVariable Integer id, Client client) {
        try {
            clientDAO.remove(id, client);
            return ResponseEntity.ok("User was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete error");
        }
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
        final boolean updated = clientDAO.update(id, client);

        return updated
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "clients/{id}/showCards")
    public ResponseEntity<List<Client>> showCards(@PathVariable(name = "id") int id) {
        final List<Client> clients = clientDAO.showCards(id);

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
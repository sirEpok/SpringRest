package ru.sberbank.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.springrest.dao.BankCardDAO;
import ru.sberbank.springrest.model.BankCard;

import java.util.List;

@RestController
public class BankCardController {
    @Autowired
    private BankCardDAO bankCardDAO;

    @GetMapping(value = "/bank_card/{id}")
    public ResponseEntity<BankCard> read(@PathVariable(name = "id") int id) {
        final BankCard bc = bankCardDAO.findByID(id);

        return bc != null
                ? new ResponseEntity<>(bc, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/bank_card")
    public ResponseEntity create(@RequestBody BankCard bc) {
        try {
            bankCardDAO.save(bc);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Critical error");
        }
    }

    @GetMapping(value = "/bank_card")
    public ResponseEntity<List<BankCard>> read() {
        final List<BankCard> bcs = bankCardDAO.findAll();

        return bcs != null &&  !bcs.isEmpty()
                ? new ResponseEntity<>(bcs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/bank_card/{id}")
    public ResponseEntity delete(@PathVariable Integer id, BankCard bc) {
        try {
            bankCardDAO.remove(id, bc);
            return ResponseEntity.ok("User was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete error");
        }
    }

    @PutMapping(value = "/bank_card/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody BankCard bc) {
        final boolean updated = bankCardDAO.update(id, bc);

        return updated
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/bank_card/{id}/addMoney/{money}", method= RequestMethod.GET)
    public ResponseEntity<?> addMoney(@PathVariable(name = "id") int id, @PathVariable("money") double addM, @RequestBody BankCard bc) {
        bankCardDAO.addMoney(id, addM, bc);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

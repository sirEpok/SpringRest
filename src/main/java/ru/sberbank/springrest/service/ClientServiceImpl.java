package ru.sberbank.springrest.service;

import org.springframework.stereotype.Service;
import ru.sberbank.springrest.dao.ClientDAO;
import ru.sberbank.springrest.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService{
    // Хранилище клиентов
    //private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private ClientDAO cdao = new ClientDAO();
    // Переменная для генерации ID клиента
    //private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    public ClientServiceImpl() {}

    @Override
    public Client read(int id) {
        return cdao.findByID(id);
    }

    @Override
    public void create(Client client) {
//        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
//        client.setId(clientId);
        cdao.save(client);
    }

//    @Override
//    public List<Client> readAll() {
//        return new ArrayList<>(cdao.findAll());
//    }

//    @Override
//    public boolean update(Client client, int id) {
//        if (cdao.findByID(id) != null) {
//            client.setId(id);
//            cdao.update(client, id);
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        return cdao.remove(id) != null;
//    }
}

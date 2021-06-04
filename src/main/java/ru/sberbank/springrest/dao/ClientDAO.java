package ru.sberbank.springrest.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.sberbank.springrest.model.Client;
import ru.sberbank.springrest.utility.HibernateSessionFactoryUtil;

import javax.annotation.Resource;
import java.util.List;

@Component
@Resource(name = "clientDAO")
public class ClientDAO {
    public Client findByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public void save(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public List<Client> findAll() {
        List<Client> users = (List<Client>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Client").list();
        return users;
    }

    public void remove(Integer id, Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public boolean update(int id, Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Client c = findByID(id);
        c.setFullName(client.getFullName());
        c.setPhone(client.getPhone());
        c.setBankAccountNumber(client.getBankAccountNumber());
        session.update(c);
        tx1.commit();
        session.close();
        if (findByID(id).equals(client)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Client> showCards(int id) {
        List<Client> users = (List<Client>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createSQLQuery("select full_name, number_card from clients, bank_cards where CLIENTS.BANK_ACCOUNT_NUMBER = BANK_CARDS.BANK_ACCOUNT_NUMBER AND id =:data")
                .setParameter("data", id)
                .list();
        return users;
    }
}

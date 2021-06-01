package ru.sberbank.springrest.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sberbank.springrest.model.Client;
import ru.sberbank.springrest.utility.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientDAO {
//    public Client findByID(int ID) {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, ID);
//    }
//    public void save(Client client) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(client);
//        tx1.commit();
//        session.close();
//    }
//    public void update(Client client, int id) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.update(client);
//        tx1.commit();
//        session.close();
//    }
//    public void remove(int id) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.delete(id);
//        tx1.commit();
//        session.close();
//    }
//    public List<Client> findAll() {
//        List<Client> users = (List<Client>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Client").list();
//        return users;
//    }
}

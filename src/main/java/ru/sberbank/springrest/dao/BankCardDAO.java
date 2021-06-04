package ru.sberbank.springrest.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.sberbank.springrest.model.BankCard;
import ru.sberbank.springrest.utility.HibernateSessionFactoryUtil;

import javax.annotation.Resource;
import java.util.List;

@Component
@Resource(name = "bankCardDAO")
public class BankCardDAO {
    public BankCard findByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankCard.class, id);
    }

    public void save(BankCard bc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(bc);
        tx1.commit();
        session.close();
    }

    public List<BankCard> findAll() {
        List<BankCard> bcs = (List<BankCard>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From BankCard").list();
        return bcs;
    }

    public void remove(Integer id, BankCard bc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(bc);
        tx1.commit();
        session.close();
    }

    public boolean update(int id, BankCard bc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        BankCard c = findByID(id);
        c.setBalance(bc.getBalance());
        session.update(c);
        tx1.commit();
        session.close();
        if (findByID(id).equals(bc)) {
            return true;
        } else {
            return false;
        }
    }

    public void addMoney(int id, double addM, BankCard bc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        BankCard c = findByID(id);
        c.setBalance(bc.getBalance() + addM);
        session.update(c);
        tx1.commit();
        session.close();
    }
}
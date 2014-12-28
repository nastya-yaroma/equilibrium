package com.yaroma.valuation.service.implementation;

import com.yaroma.HibernateFactory;
import com.yaroma.valuation.model.Stat;
import com.yaroma.valuation.service.StatService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StatServiceImplementation implements StatService{

    @Override
    public void createStat(Stat stat) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Stat readTask(int statId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Stat stat = (Stat) session.get(Stat.class, statId);
        session.close();

        return stat;
    }

    @Override
    public void updateStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllStats() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List statsList = session.createCriteria(Stat.class).list();
        session.close();

        return statsList;
    }
    
}

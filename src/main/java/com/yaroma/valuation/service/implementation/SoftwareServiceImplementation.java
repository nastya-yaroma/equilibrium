package com.yaroma.valuation.service.implementation;

import com.yaroma.HibernateFactory;
import com.yaroma.valuation.model.Software;
import com.yaroma.valuation.service.SoftwareService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SoftwareServiceImplementation implements SoftwareService {

    @Override
    public void createSoftware(Software software) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(software);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Software readSoftware(int softwareId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Software software;
        software = (Software) session.get(Software.class, softwareId);
        session.close();

        return software;
    }

    @Override
    public void updateSoftware(Software software) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(software);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteSoftware(Software software) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(software);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllSoftwares() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List softwaresList = session.createCriteria(Software.class).list();
        session.close();

        return softwaresList;
    }
    
}

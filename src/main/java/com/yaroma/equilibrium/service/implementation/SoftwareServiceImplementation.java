package com.yaroma.equilibrium.service.implementation;

import com.yaroma.HibernateFactory;
import com.yaroma.equilibrium.model.Software;
import com.yaroma.equilibrium.service.SoftwareService;
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
    
    @Override
    public Software getSoftwareByName(String name) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List softwaressList = session.createCriteria(Software.class).list();
        session.close();

        Software resultSoftware = null;
        for (int i = 0; i < softwaressList.size(); i++) {
            Software software = (Software) softwaressList.get(i);
            if (software.getName().equals(name)) {
                resultSoftware = software;
            }
        }

        return resultSoftware;
    }
    
}

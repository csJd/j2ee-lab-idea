package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

/**
 * Created by dd on 2016/5/12.
 */
public class UserDao {
    SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public Session getSession() {
        return sf.openSession();
    }

    public void add(Object obj) {
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public void delete(Object obj) {
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public void update(Object obj) {

        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public Object findById(String cls, Serializable key) {
        Session session = null;
        try {
            session = getSession();
            Object instance = session.get(cls, key);
            return instance;
        } catch (Exception e) {
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
}

package com.dao.impl.orm;

import com.dao.AbstractDAO;
import com.dao.entity.IEntity;
import com.dao.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class OrmAbstractDAO<E extends IEntity> extends AbstractDAO<E> {
    protected SessionFactory sessionFactory;

    @Autowired
    public void SetSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public E create(E entity) throws DAOException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        }catch (Exception e){
//            LOGGER.error("Unable to get entities: {}", e.getMessage(), e);
            throw new DAOException("Unable to get entities: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<E> getEntity(long id) throws DAOException {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.find(getEntityClass(), id));
        }catch (Exception e){
//            LOGGER.error("Unable to get entities: {}", e.getMessage(), e);
            throw new DAOException("Unable to get entities: " + e.getMessage(), e);
        }
    }

    @Override
    public List<E> getAll() throws DAOException {
        try(Session session = sessionFactory.openSession()){
            return (List<E>) session.createQuery("From " + getEntityClass().getSimpleName()).list();
        }catch (Exception e){
//            LOGGER.error("Unable to get entities: {}", e.getMessage(), e);
            throw new DAOException("Unable to get entities: " + e.getMessage(), e);
        }
    }

    @Override
    public E updateEntity(E entity) throws DAOException {
        try(Session session = sessionFactory.openSession()){
            return (E) session.merge(entity);
        }catch (Exception e){
//            LOGGER.error("Unable to update entities: {}", e.getMessage(), e);
            throw new DAOException("Unable to update entities: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEntity(Long id) throws DAOException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Optional<E> entityOptional = getEntity(id);
            entityOptional.ifPresent(session::remove);
            transaction.commit();
        } catch (Exception e) {
//            LOGGER.error("Unable to delete entity: {}", e.getMessage(), e);
            throw new DAOException("Unable to delete entity: " + e.getMessage(), e);
        }
    }

    protected abstract Class<E> getEntityClass();
}

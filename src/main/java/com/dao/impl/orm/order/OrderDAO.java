package com.dao.impl.orm.order;

import com.dao.entity.client.ClientEntity;
import com.dao.entity.order.OrderEntity;
import com.dao.entity.product.ProductEntity;
import com.dao.exception.DAOException;
import com.dao.impl.orm.OrmAbstractDAO;
import com.dto.client.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO extends OrmAbstractDAO<OrderEntity> {

    public OrderEntity saveOrUpdateEntity(OrderEntity entity) throws DAOException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
//            if (entity.getClientEntity().getId() == 0){
//                Criteria criteria = session.createCriteria(ClientEntity.class).
//                        add(Restrictions.eq("phone", entity.getClientEntity().getPhone()));
//                ArrayList<ClientEntity> clientEntities =(ArrayList<ClientEntity>) criteria.list();
//                if (!clientEntities.isEmpty()){
//                    entity.setClientEntity(clientEntities.get(0));
//                }
//            }
//            if (entity.getProductEntity().getId() != 0){
//                Optional<ProductEntity> productEntity = Optional.ofNullable(session.find(ProductEntity.class,
//                        entity.getProductEntity().getId()));
//                productEntity.ifPresent(entity::setProductEntity);
//            }
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        }catch (Exception e){
//            LOGGER.error("Unable to get entities: {}", e.getMessage(), e);
            throw new DAOException("Unable to get entities: " + e.getMessage(), e);
        }
    }

    @Override
    protected Class<OrderEntity> getEntityClass() {
        return OrderEntity.class;
    }
}

package com.dao.entity.order;

import com.dao.entity.Entity;
import com.dao.entity.client.ClientEntity;
import com.dao.entity.product.ProductEntity;
import com.dto.client.Client;
import com.dto.product.Product;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "orders")
public class OrderEntity extends Entity {

    @Column(name = "date_order")
    private Date dateOrder;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private ProductEntity productEntity;

    public OrderEntity(Date dateOrder, ClientEntity clientEntity, ProductEntity productEntity) {
        this.dateOrder = dateOrder;
        this.clientEntity = clientEntity;
        this.productEntity = productEntity;
    }

    public OrderEntity(long id, Date dateOrder, ClientEntity clientEntity, ProductEntity productEntity) {
        super(id);
        this.dateOrder = dateOrder;
        this.clientEntity = clientEntity;
        this.productEntity = productEntity;
    }

    public OrderEntity() {
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}

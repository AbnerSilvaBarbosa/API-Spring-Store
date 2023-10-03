package com.example.springboot.models;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER_PRODUCT")
public class UserProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUserProduct;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "idProduct")
    private ProductModel productModel;

    private Integer quantity;

    public UserModel getUser() {
        return userModel;
    }

    public void setUser(UserModel user) {
        this.userModel = user;
    }

    public ProductModel getProduct() {
        return productModel;
    }

    public void setProduct(ProductModel product) {
        this.productModel = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

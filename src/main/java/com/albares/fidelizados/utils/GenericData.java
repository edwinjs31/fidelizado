package com.albares.fidelizados.utils;

import com.albares.fidelizados.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author Edwin Jaldin S.
 */
@JsonInclude(Include.NON_NULL)
public class GenericData {

    private Login login;
    private User user;
    private Business business;
    private Image image;
    private Product product;

    public GenericData() {
    }

    public GenericData(Login login) {
        this.login = login;
    }

    public GenericData(User user) {
        this.user = user;
    }

    public GenericData(Business business) {
        this.business = business;
    }

    public GenericData(Image image) {
        this.image = image;
    }

    public GenericData(Product product) {
        this.product = product;
    }

    
    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}

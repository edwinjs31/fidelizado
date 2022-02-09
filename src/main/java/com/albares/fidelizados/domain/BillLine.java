package com.albares.fidelizados.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BillLine {

    private Integer id;
    private Integer price_unity;
    private Bill bill;
    private Product product;
    private Integer quantity;

    public BillLine() {
    }

    public Integer getPrice_unity() {
        return price_unity;
    }

    public void setPrice_unity(Integer price_unity) {
        this.price_unity = price_unity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

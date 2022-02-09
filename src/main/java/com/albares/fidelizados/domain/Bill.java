package com.albares.fidelizados.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.Date;

//factura
@JsonInclude(Include.NON_NULL)
public class Bill {

    private Integer id;
    private User user;
    private Business business;
    private Integer price;
    private Integer points;
    private Date bill_date;

    private ArrayList<BillLine> billLines;
    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public ArrayList<BillLine> getBillLines() {
        return billLines;
    }

    public void setBillLines(ArrayList<BillLine> billLines) {
        this.billLines = billLines;
    }

    
}

package com.albares.fidelizados.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;

/**
 *
 * @author Edwin Jaldin S.
 */
//Intercambio de premios
@JsonInclude(Include.NON_NULL)
public class PrizeExchange {

    private Integer id;
    private Prize prize;
    private Date exchange_date;
    private User user;
    private Business business;

    public PrizeExchange() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Date getExchange_date() {
        return exchange_date;
    }

    public void setExchange_date(Date exchange_date) {
        this.exchange_date = exchange_date;
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

}

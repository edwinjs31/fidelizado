package com.albares.fidelizados.domain;

import com.albares.fidelizados.db.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Edwin Jaldin S.
 */
@JsonInclude(Include.NON_NULL)
public class Product {

    private Integer id;
    private String bar_code;
    private String name;
    private Integer price;
    private Business business;
    private String photo_path;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public void generateBarCode() {
        String c1 = RandomStringUtils.randomAlphanumeric(3).toUpperCase();
        String c2 = RandomStringUtils.randomAlphanumeric(3).toUpperCase();
        this.bar_code = c1 + "-" + c2;
    }

    public void insert_DB(Db myDb) throws SQLException, Exception {
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO products(bar_code,name,price,business_id) VALUES(?,?,?,?) RETURNING id;"
        );
        ps.setString(1, this.getBar_code());
        ps.setString(2, this.getName());
        ps.setInt(3, this.getPrice());
        ps.setInt(4, this.getBusiness().getId());
        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
        } else {
            throw new Exception();
        }
    }
}

package com.albares.fidelizados.domain;

import com.albares.fidelizados.db.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Edwin Jaldin S.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(Include.NON_NULL)
public class Business {

    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String vat;
    private Integer ratio_euro_point;

    private Login login;
    private Integer free_prizes;
    //fuera de BD
    private String token;

    public Business() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public Integer getRatio_euro_point() {
        return ratio_euro_point;
    }

    public void setRatio_euro_point(Integer ratio_euro_point) {
        this.ratio_euro_point = ratio_euro_point;
    }

    public Integer getFree_prizes() {
        return free_prizes;
    }

    public void setFree_prizes(Integer free_prizes) {
        this.free_prizes = free_prizes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void getIdAndNamebyLogin(Db myDb) throws SQLException {
        PreparedStatement ps = myDb.prepareStatement(
                "SELECT id,name FROM business WHERE  login_id = ?;"
        );
        ps.setInt(1, this.getLogin().getId());

        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
            this.setName(rs.getString("name"));
        }
    }

    public void insert_DB(Db myDb) throws SQLException, Exception {
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO business (name,phone,address,vat,ratio_euro_point,login_id) VALUES (?,?,?,?,?,?) RETURNING id;"
        );
        ps.setString(1, this.getName());
        ps.setString(2, this.getPhone());
        ps.setString(3, this.getAddress());
        ps.setString(4, this.getVat());
        ps.setInt(5, this.getRatio_euro_point());
        ps.setInt(6, this.getLogin().getId());

        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
        } else {
            throw new Exception();
        }
    }
}

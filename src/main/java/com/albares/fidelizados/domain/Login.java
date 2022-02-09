package com.albares.fidelizados.domain;

import com.albares.fidelizados.db.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Edwin Jaldin S.
 */
@JsonInclude(Include.NON_NULL)
public class Login {

    private Integer id;
    private String email;
    private String pass;
    private String email_code;
    private LocalDateTime expiration_time;
    //this.setExpiration_time(rs.getTimestamp(4).toLocalDateTime());

    public Login() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail_code() {
        return email_code;
    }

    public void setEmail_code(String email_code) {
        this.email_code = email_code;
    }

    public LocalDateTime getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(LocalDateTime expiration_time) {
        this.expiration_time = expiration_time;
    }

    public void generateEmailCodeAndExpirationTime() {
        this.email_code = RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        this.expiration_time = LocalDateTime.now().plusMinutes(10);

    }

    //compara el pass ingresado y el pass de la BD
    public Boolean checkPass(String pass) {
        return pass.equals(this.getPass());
    }

    //comprueba el email_code ingresado y el de BD
    public Boolean checkEmailCode(String code) {
        return code.equals(this.getEmail_code());
    }

    //comprueba el expiration_time del email_code ingresado
    public Boolean checkExpirationTime() {
        LocalDateTime timeNow = LocalDateTime.now();
        return timeNow.isBefore(this.getExpiration_time());
    }

    public Boolean checkEmailAndGetIdPass(Db myDb) throws SQLException {
        PreparedStatement ps = myDb.prepareStatement("SELECT id, pass FROM logins WHERE email=?;");
        ps.setString(1, this.getEmail());
        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
            this.setPass(rs.getString("pass"));
            return true;
        } else {
            return false;
        }
    }

    public void insert_DB(Db myDb) throws SQLException, Exception {
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO logins(email, pass) VALUES(?,?) RETURNING id;");
        ps.setString(1, this.getEmail());
        ps.setString(2, this.getPass());

        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
        } else {
            throw new Exception();
        }
    }


//*****************************************************************************************
    public void updateLogins_DB(Db myDb) throws SQLException {
        String update = "UPDATE logins SET ";
        List<String> fields = new ArrayList();
        if (this.getEmail() != null) {
            fields.add("email");
        }
        if (this.getPass() != null) {
            fields.add("pass");
        }
        if (this.getEmail_code() != null) {
            fields.add("email_code");
        }
        if (this.getExpiration_time() != null) {
            fields.add("expiration_time");
        }
        for (int i = 0; i < fields.size(); i++) {
            update += fields.get(i) + "= ?";
            if (i != fields.size() - 1) {
                update += ",";
            }
        }
        update += " where email = ?;";
        PreparedStatement ps = myDb.prepareStatement(update);
        for (int i = 0; i < fields.size(); i++) {
            switch (fields.get(i)) {
                case "email":
                    ps.setString(i + 1, this.getEmail());
                    break;
                case "pass":
                    ps.setString(i + 1, this.getPass());
                    break;
                case "email_code":
                    ps.setString(i + 1, this.getEmail_code());
                    break;
                case "expiration_time":
                    ps.setTimestamp(i + 1, Timestamp.valueOf(this.getExpiration_time()));
                    break;
            }
        }
        ps.setString(fields.size() + 1, this.getEmail());
        ps.executeUpdate();
    }

    public void getIdEmailCodeAndExpirationTime(Db myDb) throws SQLException {
        PreparedStatement ps = myDb.prepareStatement("SELECT id,email_code,expiration_time FROM logins WHERE email=?;");
        ps.setString(1, this.getEmail());
        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt(1));
            this.setEmail_code(rs.getString(2));
            this.setExpiration_time(rs.getTimestamp(3).toLocalDateTime());
        }
    }

}

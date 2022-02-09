package com.albares.fidelizados.domain;

import com.albares.fidelizados.db.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Edwin Jaldin S.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(Include.NON_NULL)
public class User {

    private Integer id;
    private String name;
    private Integer gender;
    private String code;
    private Login login;
    private String birthdate;

    //Fuera de la BD
    private String token;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //Ya que coincide el email y pass,obtenemos el id y nombre del usuario
    public void getIdAndNamebyLogin(Db myDb) throws SQLException {
        PreparedStatement ps = myDb.prepareStatement(
                "SELECT id,name FROM users WHERE  login_id = ?;"
        );
        ps.setInt(1, this.getLogin().getId());
        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
            this.setName(rs.getString("name"));
        }
    }

    public void insert_DB(Db myDb) throws SQLException, Exception {
        Date sqlDate = Date.valueOf(this.getBirthdate());
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO users(name,gender,birthdate,login_id) VALUES(?,?,?,?) RETURNING id;"
        );
        ps.setString(1, this.getName());
        ps.setInt(2, this.getGender());
        ps.setDate(3, sqlDate);
        //ps.setTimestamp(2, Timestamp.valueOf(this.getBirthday()));//birthday seria 'LocalDateTime'
        ps.setInt(4, this.getLogin().getId());

        ResultSet rs = myDb.executeQuery(ps);
        if (rs.next()) {
            this.setId(rs.getInt("id"));
        } else {
            throw new Exception();
        }
    }

    /*
    @JsonIgnore //Esta variable no aparece en el JSON de Jackson
    public String getEncodedPass() throws NoSuchAlgorithmException {
        return sha256(this.getPass() + Secrets.SALT_PASS);
        return null;
    }
     */
    //INSERT user
    /*
    public int insertAndGetId_DB(Db myDb) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO users (name, pass, score) VALUES (?, ?, ?) returning id;"
        );
        ps.setString(1, this.getName());
        ps.setString(2, this.getEncodedPass());
        ResultSet rs = myDb.executeQuery(ps);
        rs.next();
        this.setId(rs.getInt(1));
        return this.getId();
    }
     */
    //SELECT users
    /*
    public static List selectUsers(Db myDb) throws SQLException {
        PreparedStatement ps = myDb.prepareStatement(
                "SELECT name,score FROM users ORDER BY score DESC LIMIT 3;");

        ResultSet rs = myDb.executeQuery(ps);
        List<User> users = new ArrayList();

        while (rs.next()) {
            //User user = new User(rs.getString(1), rs.getInt(2));
            //sers.add(user);
        }
        return users;
    }
     */
}

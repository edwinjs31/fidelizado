package com.albares.fidelizados.api;

import com.albares.fidelizados.db.Db;
import com.albares.fidelizados.domain.Business;
import com.albares.fidelizados.domain.Login;
import com.albares.fidelizados.domain.User;
import com.albares.fidelizados.utils.GenericData;
import com.albares.fidelizados.utils.JWTUtils;
import com.albares.fidelizados.utils.Parameters;
import com.albares.fidelizados.utils.Response;
import com.albares.fidelizados.utils.ResponseCode;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

/*
REQUEST:
{
    "login":{
        "email":"aasdasd@ksfj.com",
        "email_code":"45R2",
        "pass":"2123132"
    },
    
}
RESPONSE:
{
    "responseCode":-1 //
                    0 //
                    1 //OK

    "login":{
        "token":"ksfdfjdsfd654...."
    },

}
 */
@Path("/resetPassword")
public class resetPassword {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(GenericData gd) throws SQLException, Exception {
        Db myDb = new Db();
        myDb.connect();
        Login login = gd.getLogin();
        String emailCode = login.getEmail_code();//guardamos el email_code
        String newPass = login.getPass();//guardamos el nuevo pass
        
        if (gd.getUser() != null) {
            User user = gd.getUser();
            login.getIdEmailCodeAndExpirationTime(myDb);
            //Si el codigo y el tiempo de expiracion son validos
            if (login.checkEmailCode(emailCode) && login.checkExpirationTime()) {
                login.setPass(newPass);
                user.setLogin(login);//una vez cambiado el pass ya inicia el usuario actual
                login.setEmail_code("VC");
                login.updateLogins_DB(myDb);
                user.getIdAndNamebyLogin(myDb);
                user.setLogin(null);//no se requiere mostrar el login en el response.
                user.setToken(JWTUtils.generateToken(user.getId(), login.getId(), Parameters.APP_USER));
                myDb.disconnect();
                return new Response(ResponseCode.OK, new GenericData(user));

            } else {
                myDb.disconnect();
                return new Response(ResponseCode.NO_USER);

            }
        } else if (gd.getBusiness() != null) {
            Business business = gd.getBusiness();
            login.getIdEmailCodeAndExpirationTime(myDb);
            if (login.checkEmailCode(emailCode) && login.checkExpirationTime()) {
                login.setPass(newPass);
                business.setLogin(login);
                login.setEmail_code("VC");
                login.updateLogins_DB(myDb);
                business.getIdAndNamebyLogin(myDb);
                business.setLogin(null);
                business.setToken(JWTUtils.generateToken(business.getId(), login.getId(), Parameters.APP_BUSINESS));
                myDb.disconnect();
                return new Response(ResponseCode.OK, new GenericData(business));

            } else {
                myDb.disconnect();
                return new Response(ResponseCode.NO_USER);
            }
            //si no hay User ni Business,lanzamos una exepcion. 
        } else {
            throw new Exception();
        }

    }
}

package com.albares.fidelizados.api;

import com.albares.fidelizados.db.Db;
import com.albares.fidelizados.domain.Login;
import com.albares.fidelizados.utils.GenericData;
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
        "email":"correo@gmail.com",
        
    },

    
}
RESPONSE:
{
    "responseCode":-1 //Pass mal
                    0 //No_email
                    1 //Ok
 
}
 */
@Path("/sendCode")
public class sendCode {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendCode(GenericData gd) throws SQLException, Exception {
        Db myDb = new Db();
        myDb.connect();
        Login login = gd.getLogin();

        //Teniendo en cuenta que para recuperar un pass ya existe el email.
        if (Parameters.attempts != 0) {
            Parameters.attempts--;
            if (gd.getUser() != null) {
                login.generateEmailCodeAndExpirationTime();
                login.updateLogins_DB(myDb);
                myDb.disconnect();
                return (new Response(ResponseCode.OK));
            } else if (gd.getBusiness() != null) {
                login.generateEmailCodeAndExpirationTime();
                login.updateLogins_DB(myDb);
                myDb.disconnect();
                return (new Response(ResponseCode.OK));
            } else {

                throw new Exception();
            }
        } else {
            return (new Response(ResponseCode.ERROR));

        }

    }
}

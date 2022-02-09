package com.albares.fidelizados.api;
/**
 * En un solo Web Sevice para hacer login en la app User y Business.
 */
import com.albares.fidelizados.db.Db;
import com.albares.fidelizados.domain.*;
import com.albares.fidelizados.utils.GenericData;
import com.albares.fidelizados.utils.JWTUtils;
import com.albares.fidelizados.utils.Parameters;
import com.albares.fidelizados.utils.Response;
import com.albares.fidelizados.utils.ResponseCode;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

/*
REQUEST:
{
    "login":{
        "email":"aasdasd@ksfj.com",
        "pass":"2123132"
    },
    "user":{} //"business":{}
}
RESPONSE:
{
    "responseCode":-1 //Mal la pass
                    0 //No existe login con ese email
                    1 //Existe email y coincide la pass
}
 */
@Path("/doLogin")
public class doLogin {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(GenericData gd) throws SQLException, Exception {
        
        //El Objeto 'GenericData' contiene otros objetos como; login,user,business,etc.
        Db myDb = new Db();
        myDb.connect();
        Login login = gd.getLogin();
        String pass = login.getPass();//guardamos el pass del login recibido
        
        // Si hay un User:Se está haciendo login en la app user
        if (gd.getUser() != null) {
            User user = gd.getUser();
            user.setLogin(login);//login del usuario actual
            if (login.checkEmailAndGetIdPass(myDb)) {//si existe el email
                if (login.checkPass(pass)) {//si el pass es el mismo
                    user.getIdAndNamebyLogin(myDb);
                    user.setLogin(null);//no se requiere mostrar el login en el response.
                    user.setToken(JWTUtils.generateToken(user.getId(), login.getId(), Parameters.APP_USER));
                    myDb.disconnect();
                    return new Response(ResponseCode.OK, new GenericData(user));
                } else {
                    myDb.disconnect();
                    //Retorna un error,en el frontend se genera una opcion para reestablecer el pass
                    return new Response(ResponseCode.ERROR);
                }
            } else {//Si no existe el email,en el frontend se dirige al formulario de registro
                myDb.disconnect();
                return new Response(ResponseCode.NO_USER);

            }
        //Si hay un Business:Se está haciendo login en la app business
        } else if (gd.getBusiness() != null) { 
            Business business = gd.getBusiness();
            business.setLogin(login);
            if (login.checkEmailAndGetIdPass(myDb)) {
                if (login.checkPass(pass)) {
                    business.getIdAndNamebyLogin(myDb);
                    business.setLogin(null);
                    business.setToken(JWTUtils.generateToken(business.getId(), login.getId(), Parameters.APP_BUSINESS));
                    myDb.disconnect();
                    return new Response(ResponseCode.OK, new GenericData(business));
                } else {
                    myDb.disconnect();
                    return new Response(ResponseCode.ERROR);
                }
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

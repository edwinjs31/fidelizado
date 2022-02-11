package com.albares.fidelizados.api;
/**
 * Dos formas de procesar imágenes: Streamed y base64, con sus pros y contras.
 */
import com.albares.fidelizados.utils.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Edwin Jaldin S.
 */
@Path("/addImage")
public class addImage {
    /*
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStreamedImage(InputStream uploadedInputStream) throws IOException {

        Image i = new Image();
        i.saveStreamedImage(uploadedInputStream);
        return (new Response(ResponseCode.OK, new GenericData(i)));

    }*/
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBase64Image(Image image) throws IOException, NoSuchAlgorithmException {
        Image i = new Image();
        i.saveBase64Image(image.getImageString());
        return (new Response(ResponseCode.OK, new GenericData(i)));

    }

}

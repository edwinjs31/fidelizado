package com.albares.fidelizados.utils;

import com.albares.fidelizados.utils.Parameters;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Edwin Jaldin S.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {

    public String imageUrl;
    public String imageString;

    public Image() {
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Opcion 1: Recomendable para 
    public String saveStreamedImage(InputStream inStream) throws IOException {
        String fileNamePath = this.generateNewImagePath();
        File file = new File(fileNamePath);

        Files.createDirectories(file.getParentFile().toPath());

        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(file);
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
        this.setImageUrl(Parameters.SERVER_URL + Parameters.RELATIVE_PATH + getImageFolder(file.getName()) + file.getName());
        return this.getImageUrl();
    }

    //Opcion 2: 
    public String saveBase64Image(String image) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        byte[] imageByte = Base64.decodeBase64(image);

        String fileNamePath = this.generateNewImagePath();
        File file = new File(fileNamePath);

        Files.createDirectories(file.getParentFile().toPath());

        FileOutputStream fos = new FileOutputStream(fileNamePath);
        fos.write(imageByte);
        fos.flush();
        fos.close();

        this.setImageUrl(Parameters.SERVER_URL + Parameters.RELATIVE_PATH + getImageFolder(file.getName()) + file.getName());
        return this.getImageUrl();
    }

    private String generateNewImagePath() {
        String name = UUID.randomUUID().toString().replace("-", "");
        return Parameters.BASE_PATH + Parameters.RELATIVE_PATH + getImageFolder(name) + name + ".jpg";
    }

    private String getImageFolder(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos > 0 && pos < (fileName.length() - 1)) {
            fileName = fileName.substring(0, pos);
        }
        return fileName.substring(fileName.length() - 3) + "/";
    }

}

package com.moaaz.health.service;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class ImageService {


    public String saveImageToFolder(String base64Image) {

        if (base64Image == null || base64Image.isBlank() || base64Image.isEmpty())
            return "";

//        log.info("Trying TO Update Image With Base "+base64Image);
        // Decode the Base64 image
//            base64Image =  base64Image.replaceFirst("data:[^;]*;base64,", "");
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // Generate a unique filename or key for the image
        String filename = UUID.randomUUID().toString() + ".png";

        // Define the folder path where you want to save the image
        String folderPath = "src/main/resources/images/";

        // Create the FileOutputStream to write the image
        try (FileOutputStream fos = new FileOutputStream(folderPath + filename)) {
            fos.write(imageBytes);
            fos.flush();
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
//            throw e; // Rethrow the exception for handling at a higher level
        }

        // Return the filename or path as needed
        return filename;


    }
}

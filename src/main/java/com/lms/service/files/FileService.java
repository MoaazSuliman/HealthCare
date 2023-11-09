package com.lms.service.files;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {


//    @Autowired
//    private S3Client s3Client;


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


    @SneakyThrows
    public String uploadVideoToS3AndGetImageUrl(MultipartFile file) {


        // Decode the Base64 image
        try {
            byte[] videoBytes = file.getBytes();
            byte[] base64 = Base64.getEncoder().encode(videoBytes);
            String toUpload= new String(base64);
            return "https://";
        } catch (IOException exception) {
            throw new IOException(exception);
        }
// Generate a unique filename or key for the image
//        String filename = UUID.randomUUID().toString() + ".png";
//
//        // Upload the image to S3 bucket
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket("")
//                .key(filename)
//                .build();
//        s3Client.putObject(request, RequestBody.fromBytes(imageBytes));

        // Return the URL of the uploaded image
//        return "https://";

    }


}

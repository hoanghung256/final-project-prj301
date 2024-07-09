/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author This PC
 */

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductImage;

public class ImageHandler {
    public static List<ProductImage> handleUploadImages(Collection<Part> parts, ServletContext context) {
        String imageFolderPath = context.getRealPath("/") + "upload-images";
        List<ProductImage> pathList = new ArrayList<>();

        try {
            // Create /upload-images folder if does not exist
            Files.createDirectories(Paths.get(imageFolderPath));

            for (Part part : parts) {
                // Ignore not uploaded file parts 
                if (part.getSubmittedFileName() == null)
                    continue;

                String imagePath = imageFolderPath + File.separator + part.getSubmittedFileName();
                part.write(imagePath);
                pathList.add(new ProductImage(part.getSubmittedFileName()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pathList;
    }

    public static void deleteImage(String imageUrl) {
        File file = new File(imageUrl);

        if (file.exists()) {
            file.delete();
        }
    }

//    public static ProductImage handleSingleUploadImage(Part part, ServletContext context) {
//    String imageFolderPath = context.getRealPath("/") + "upload-images";
//
//    try {
//        // Create /upload-images folder if does not exist
//        Files.createDirectories(Paths.get(imageFolderPath));
//
//        // Generate unique filename for the uploaded file
//        String fileName = ;
//
//        // Save the file to the server
//        String imagePath = imageFolderPath + File.separator + fileName;
//        part.write(imagePath);
//
//        // Return the ProductImage object with the filename
//        return new ProductImage(fileName);
//
//    } catch (IOException ex) {
//        Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
//    }
//
//    return null;
//}
}

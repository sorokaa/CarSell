package com.example.CarSell.POJO;

import com.example.CarSell.domain.Post;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
/*
* Simple POJO, which generate HTML page via post template
*
* */
public class GenerateHtml {

    private static Logger logger = LoggerFactory.getLogger(GenerateHtml.class);

    /*
    * Recieve a post object and try to create file
    * File name create by next formula: <mark>#<model>#<id>.html
    *
    * Return object of file
     */

    private static File createHtml (Post post) {
        try {
            File file = new File("src/main/resources/templates/post/" +
                    post.getMark() + "#" + post.getModel() + "#" +
                    post.getId() + ".html");

            while(!file.createNewFile()) {
                file = new File("src/main/resources/templates/post/" +
                        post.getMark() + "#" + post.getModel().replaceAll("\\s+", "") + "#" +
                        post.getId() + ".html");

            }

            return file;

        } catch (IOException exp) {
            logger.info("Can't create HTML page: " + exp.getMessage());
            return null;
        }
    }

    /*
    * Recieve posts object, find file on post folder and try to delete
    *
    * Return boolean value
     */

    public static boolean deletePage(@NotNull Post post) {
        String mark = post.getMark();
        String model = post.getModel();
        String id = String.valueOf(post.getId());
        File file = new File("src/main/resources/templates/post/"+mark+"#"+model+"#"+id+".html");
        return file.delete();
    }

    /*
    * Entry point to generate file
    *
    * Create file and fill text from postTemplate.html
     */

    public static void generatePage(@NotNull Post post) {

        Path pathToTemplate = Path.of("src/main/resources/templates/post/postTemplate/postTemplate.html");
        String fileText = "";

        try {
            fileText = Files.readString(pathToTemplate);
        } catch (IOException exp) {
            logger.info("Can't open template file: " + exp.getMessage());
            return;
        }

        fileText = fileText.replaceAll("#TITLE#", post.getMark() + " " +
                                                                   post.getModel());

        File newFile = createHtml(post);

        if(newFile == null) {
            logger.info("Can't create file...");
            return;
        }

        Path path = Path.of(newFile.getPath());

        try {
            Files.writeString(path, fileText);
        } catch (IOException exp) {
            logger.info("Can't write info to file...: " + exp.getMessage());
        }

    }
}

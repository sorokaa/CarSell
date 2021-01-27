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

public class GenerateHtml {

    private static Logger logger = LoggerFactory.getLogger(GenerateHtml.class);


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

    public static boolean deletePage(@NotNull Post post) {
        String mark = post.getMark();
        String model = post.getModel();
        String id = String.valueOf(post.getId());
        File file = new File("src/main/resources/templates/post/"+mark+"#"+model+"#"+id+".html");
        return file.delete();
    }

    public static void generatePage(@NotNull Post post) {
        Random randomNumber = new Random();

        Path pathToTemplate = Path.of("src/main/resources/templates/post/postTemplate/postTemplate.html");
        String fileText = "";

        try {
            fileText = Files.readString(pathToTemplate);
        } catch (IOException exp) {
            logger.info("Can't open template file: " + exp.getMessage());
            return;
        }

        /*fileText = fileText.replaceAll("#TITLE#", post.getMark() + " " +
                                                                   post.getModel());
        fileText = fileText.replace("#LINK#", post.getLink());
        fileText = fileText.replace("#MARK#", post.getMark());
        fileText = fileText.replace("#MODEL#", post.getModel());
        fileText = fileText.replace("#COLOR#", post.getColor());
        fileText = fileText.replace("#COST#", post.getCost().toString());
        fileText = fileText.replace("#ENGINE#", post.getEngineVolume().toString());
        fileText = fileText.replace("#INFO#", post.getShortInfo());

*/
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

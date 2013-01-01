package com.belano.tabplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

public class TabFileReader {

    private BufferedReader getFileReader(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            return new BufferedReader(new FileReader(resource.getFile()));
        } catch (Exception e) {
            String message = String.format("Input file %s does not exist or cannot be opened for reading.", path);
            throw new IllegalArgumentException(message);
        }
    }

    public String readAsciiTabFromFile(String path) {
        BufferedReader reader = getFileReader(path);
        StringBuilder contents = new StringBuilder();
        String inputLine = null;
        try {
            while ((inputLine = reader.readLine()) != null) {
                contents.append(inputLine);
                contents.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unexpected exception");
        }
        return contents.toString();
    }

}

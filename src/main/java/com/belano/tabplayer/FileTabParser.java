package com.belano.tabplayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

public class FileTabParser implements TabParser {

    private BufferedReader getFileReader(String path) throws Exception {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            return new BufferedReader(new FileReader(resource.getFile()));
        } catch (FileNotFoundException e) {
            String message = String.format("Input file %s does not exist or cannot be opened for reading.", path);
            throw new IllegalArgumentException(message);
        }
    }

    public List<Note> parseAsciiTab(String asciiTabFile) {
        // BufferedReader reader = getFileReader(asciiTabFile);

        return null;
    }

}

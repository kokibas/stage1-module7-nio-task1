package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null;
        int age = 0;
        String email = null;
        long phone = 0L;
        Path path = Paths.get(file.toURI());
        List<String > list = null;
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            list = Files.readAllLines(path);
            for (String s : list) {
                String parts[] = s.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    map.put(key,value);
                }
            }
            name = map.get("Name");
            age = Integer.parseInt(map.get("Age"));
            email = map.get("Email");
            phone = Long.parseLong(map.get("Phone"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return new Profile(name,age,email,phone);
    }
}

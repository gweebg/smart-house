package org.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {

    @Test
    void loadTest()
    {
        FileLoader manager = new FileLoader();
        manager.load("src/main/java/org/house/load.txt");
        System.out.println(manager);
    }
}
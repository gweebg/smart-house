package org.Utils;

import org.Exceptions.ExistingIdException;
import org.Exceptions.NegativeDeviceIdException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileLoaderTest {

    @Test
    void loadTest() throws NegativeDeviceIdException, IOException, ExistingIdException
    {
        FileLoader manager = new FileLoader();
        manager.loadFromFile("/home/guilherme/Documents/repos/smart-house/src/main/java/org/House/load.txt",12, 7);
        System.out.println(manager);
    }
}
package com.picahielos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSplitter {
    public static List<ReadableFile> split(ReadableFile inputFile, String destinationFilename) throws IOException {
        long availableMemory = Runtime.getRuntime().freeMemory();
        List<ReadableFile> outputFiles = new ArrayList<>();
        int i = 0;

        List<String> lines = inputFile.readChunk(availableMemory);

        while (!lines.isEmpty()) {
            Collections.sort(lines);

            String filename = destinationFilename + i++;
            Files.write(Paths.get(filename), lines);
            outputFiles.add(new ReadableFile(filename));

            lines = inputFile.readChunk(availableMemory);
        }

        return outputFiles;
    }
}

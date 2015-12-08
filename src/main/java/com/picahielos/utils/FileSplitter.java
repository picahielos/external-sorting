package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Splits a file into smaller ones.
 */
public class FileSplitter {
    /**
     * Splits the given file into chunks of the given size. The content is sorted.
     * @param inputFile The file to split.
     * @param destinationFilename The name of the file as a pattern.
     * @param size The size of each file.
     * @return The list of new files.
     * @throws IOException
     */
    public static List<ReadableFile> split(ReadableFile inputFile, String destinationFilename, long size) throws IOException {
        List<ReadableFile> outputFiles = new ArrayList<>();
        int i = 0;

        List<String> lines = inputFile.readChunk(size);

        while (!lines.isEmpty()) {
            String filename = destinationFilename + i++;
            Files.write(Paths.get(filename), lines);
            outputFiles.add(new ReadableFile(filename));

            lines = inputFile.readChunk(size);
        }

        return outputFiles;
    }
}

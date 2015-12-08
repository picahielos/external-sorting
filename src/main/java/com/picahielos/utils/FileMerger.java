package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FileMerger {
    public static void merge(List<ReadableFile> outputFiles, String destinationFilename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFilename, true))) {
            ReadableFile readableFile;
            String currentLine;

            do {
                readableFile = Collections.min(outputFiles);

                currentLine = readableFile.getCurrentLine();
                if (currentLine != null) {
                    bw.append(currentLine).append("\n");
                }

                readableFile.readNextLine();
            } while (currentLine != null);
        }
    }
}

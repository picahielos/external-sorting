package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileMerger {
    public static void merge(List<ReadableFile> outputFiles, String destinationFilename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFilename))) {
            String currentLine;

            do {
                ReadableFile file = getMin(outputFiles);

                currentLine = file.getCurrentLine();
                if (currentLine != null) {
                    bw.append(currentLine).append("\n");
                }

                file.readNextLine();
            } while (currentLine != null);
        }
    }

    private static ReadableFile getMin(List<ReadableFile> outputFiles) {
        ReadableFile file = outputFiles.get(0);
        for (ReadableFile tmpFile : outputFiles) {
            if (tmpFile.compareTo(file) < 0) {
                file = tmpFile;
            }
        }
        return file;
    }
}

package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class allows the user to merge a list of files into one in an ordered way.
 */
public class FileMerger {
    /**
     * Given a list of files, merge their lines in an ordered way and save it into the destination file.
     * @param outputFiles The list of file to merge.
     * @param destinationFilename The destination file.
     * @throws IOException
     */
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

    /**
     * Gets the line that goes first from the list of files.
     * @param outputFiles The list of files.
     * @return The file with the first line.
     */
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

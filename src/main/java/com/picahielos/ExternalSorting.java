package com.picahielos;

import java.io.IOException;
import java.util.List;

public class ExternalSorting {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("We need input and output file.");
            System.exit(1);
        }

        List<ReadableFile> outputFiles = null;
        try {
            outputFiles = FileSplitter.split(new ReadableFile(args[0]), args[1]);
        } catch (IOException e) {
            System.out.println("Error processing source file: " + e.getMessage());
            System.exit(1);
        }

        try {
            FileMerger.merge(outputFiles, args[1]);
        } catch (IOException e) {
            System.out.println("Error processing destination file: " + e.getMessage());
        }

        outputFiles.forEach(ReadableFile::remove);
    }
}

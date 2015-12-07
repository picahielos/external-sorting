package com.picahielos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadableFile implements Comparable<ReadableFile> {
    private String filename;
    private BufferedReader br;
    private FileReader fr;
    private String currentLine;

    public ReadableFile(String filename) throws FileNotFoundException {
        this.filename = filename;
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
    }

    public List<String> readChunk(long size) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        long remainingSpace = size;
        Integer length;

        try {
            while ((line = br.readLine()) != null && (length = line.getBytes("UTF-8").length) <= remainingSpace) {
                lines.add(line);
                remainingSpace -= length;
                br.mark((int) size);
            }

            if (line != null) {
                br.reset();
            }
        } catch (IOException e) {
            fr.close();
            throw e;
        }

        return lines;
    }

    public String getCurrentLine() {
        if (currentLine == null) {
            readNextLine();
        }
        return currentLine;
    }

    public void readNextLine() {
        try {
            currentLine = br.readLine();
        } catch (IOException e) {
        }
    }

    public void remove() {
        try {
            fr.close();
            Files.delete(Paths.get(filename));
        } catch (IOException e) {
        }
    }

    @Override
    public int compareTo(ReadableFile o) {
        if (getCurrentLine() == null) {
            return 1;
        } else if (o.getCurrentLine() == null) {
            return -1;
        } else {
            return getCurrentLine().compareTo(o.getCurrentLine());
        }
    }
}

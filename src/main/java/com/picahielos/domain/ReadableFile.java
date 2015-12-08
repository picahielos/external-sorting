package com.picahielos.domain;

import com.picahielos.utils.SortedLineList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a file with some helpers to read it.
 * When comparing two readable files, we are only concerned about the current line.
 */
public class ReadableFile implements Comparable<ReadableFile> {
    private String filename;
    private BufferedReader br;
    private FileReader fr;
    private String currentLine;

    /**
     * @param filename The name of the file.
     * @throws FileNotFoundException
     */
    public ReadableFile(String filename) throws FileNotFoundException {
        this.filename = filename;
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
    }

    /**
     * Reads a chunk of data.
     * @param size The size of the data to read.
     * @return The list of lines read.
     * @throws IOException
     */
    public List<String> readChunk(long size) throws IOException {
        List<String> lines = new SortedLineList();
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

    /**
     * Returns the current line. If it is null, tries to read next.
     * @return The line.
     */
    public String getCurrentLine() {
        if (currentLine == null) {
            readNextLine();
        }
        return currentLine;
    }

    /**
     * Reads next line.
     */
    public void readNextLine() {
        try {
            currentLine = br.readLine();
        } catch (IOException e) {
        }
    }

    /**
     * Removes the file.
     */
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

package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileSplitterTest {
    @Test
    public void testSplittingOneFile() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("one");
        lines.add("two");
        lines.add("three");
        lines.add("four");
        Files.write(Paths.get("source.test"), lines);

        List<ReadableFile> outputFiles = FileSplitter.split(new ReadableFile("source.test"), "dest.test", 500);

        assertEquals(outputFiles.size(), 1);

        List<String> content = outputFiles.get(0).readChunk(500);

        Files.delete(Paths.get("source.test"));
        Files.delete(Paths.get("dest.test0"));

        assertEquals(content.get(0), "four");
        assertEquals(content.get(1), "one");
        assertEquals(content.get(2), "three");
        assertEquals(content.get(3), "two");
    }

    @Test
    public void testSplittingMultipleFile() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("one");
        lines.add("two");
        lines.add("three");
        lines.add("four");
        Files.write(Paths.get("source.test"), lines);

        List<ReadableFile> outputFiles = FileSplitter.split(new ReadableFile("source.test"), "dest.test", 6);

        assertEquals(outputFiles.size(), 3);

        List<String> content1 = outputFiles.get(0).readChunk(500);
        List<String> content2 = outputFiles.get(1).readChunk(500);
        List<String> content3 = outputFiles.get(2).readChunk(500);

        Files.delete(Paths.get("source.test"));
        Files.delete(Paths.get("dest.test0"));
        Files.delete(Paths.get("dest.test1"));
        Files.delete(Paths.get("dest.test2"));

        assertEquals(content1.get(0), "one");
        assertEquals(content1.get(1), "two");
        assertEquals(content2.get(0), "three");
        assertEquals(content3.get(0), "four");
    }
}

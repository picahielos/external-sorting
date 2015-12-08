package com.picahielos.utils;

import com.picahielos.domain.ReadableFile;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileMergerTest {
    @Test
    public void testMerger() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("four");
        lines.add("one");
        lines.add("three");
        lines.add("two");
        Files.write(Paths.get("dest.test0"), lines);

        lines = new ArrayList<>();
        lines.add("goodbye");
        lines.add("hello");
        lines.add("no");
        lines.add("yes");
        Files.write(Paths.get("dest.test1"), lines);

        List<ReadableFile> outputFiles = new ArrayList<>();
        outputFiles.add(new ReadableFile("dest.test0"));
        outputFiles.add(new ReadableFile("dest.test1"));

        FileMerger.merge(outputFiles, "dest.test");

        List<String> content = Files.readAllLines(Paths.get("dest.test"));

        Files.delete(Paths.get("dest.test"));
        Files.delete(Paths.get("dest.test0"));
        Files.delete(Paths.get("dest.test1"));

        assertEquals(content.get(0), "four");
        assertEquals(content.get(1), "goodbye");
        assertEquals(content.get(2), "hello");
        assertEquals(content.get(3), "no");
        assertEquals(content.get(4), "one");
        assertEquals(content.get(5), "three");
        assertEquals(content.get(6), "two");
        assertEquals(content.get(7), "yes");
    }
}

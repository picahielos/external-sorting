package com.picahielos.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortedLineListTest {
    @Test
    public void testAddInOrder() {
        List<String> list = new SortedLineList();
        list.add("string");
        list.add("hola");
        list.add("adios");
        list.add("string");

        assertEquals(list.get(0), "adios");
        assertEquals(list.get(1), "hola");
        assertEquals(list.get(2), "string");
        assertEquals(list.get(3), "string");
    }
}

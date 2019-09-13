/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.adamjak.utils.collectionsutils;

import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Adamjak <tomas@adamjak.net>
 */
public class LineListTest {

    public LineListTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of size method, of class LineList.
     */
    @Test
    public void testSize() {
        LineList<Integer> l1 = new LineList<Integer>();
        LineList<String> l2 = new LineList<String>();

        l1.add(2);
        l1.add(4);
        l1.add(6);
        l1.add(7);

        Assert.assertEquals(4, l1.size());
        Assert.assertEquals(0, l2.size());

    }

    /**
     * Test of isEmpty method, of class LineList.
     */
    @Test
    public void testIsEmpty() {
        LineList<Integer> l1 = new LineList<>();
        boolean empty = l1.isEmpty();
        Assert.assertTrue(empty);

        l1.add(2);
        l1.add(4);
        l1.add(6);
        l1.add(7);

        Assert.assertFalse(l1.isEmpty());

        l1.clear();

        Assert.assertTrue(l1.isEmpty());
    }

    /**
     * Test of contains method, of class LineList.
     */
    @Test
    public void testContains() {
        LineList<String> l1 = new LineList<String>();
        Assert.assertFalse(l1.contains("a"));

        l1.add("b");
        l1.add("d");

        Assert.assertTrue(l1.contains("b"));

        l1.remove("b");
        Assert.assertFalse(l1.contains("b"));

    }

    /**
     * Test of iterator method, of class LineList.
     */
    @Test
    public void testIterator() {

    }

    /**
     * Test of toArray method, of class LineList.
     */
    @Test
    public void testToArray() {
        LineList<String> l1 = new LineList<>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        Object[] exp1 = {"a", "b", "c"};
        Object[] res1 = l1.toArray();

        Assert.assertEquals(exp1.length, res1.length);

        for (int i = 0; i < exp1.length; i++) {
            Assert.assertEquals(exp1[i], res1[i]);
        }

        String[] strArr = new String[0];
        String[] res2 = l1.toArray(strArr);
        String[] exp2 = {"a", "b", "c"};

        Assert.assertEquals(res2.length, res2.length);

        for (int i = 0; i < res2.length; i++) {
            Assert.assertEquals(exp2[i], res2[i]);
        }
    }

    /**
     * Test of containsAll method, of class LineList.
     */
    @Test
    public void testContainsAll() {

    }

    /**
     * Test of addAll method, of class LineList.
     */
    @Test
    public void testAddAll_Collection() {

    }

    /**
     * Test of addAll method, of class LineList.
     */
    @Test
    public void testAddAll_int_Collection() {
        try {
            new LineList<Object>().addAll(0, Collections.EMPTY_LIST);
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException e) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test of removeAll method, of class LineList.
     */
    @Test
    public void testRemoveAll() {

    }

    /**
     * Test of retainAll method, of class LineList.
     */
    @Test
    public void testRetainAll() {

    }

    /**
     * Test of get method, of class LineList.
     */
    @Test
    public void testGet() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        Assert.assertEquals("a", l1.get(0));
        Assert.assertEquals("b", l1.get(1));
        Assert.assertEquals("c", l1.get(2));
    }

    /**
     * Test of set method, of class LineList.
     */
    @Test
    public void testSet() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        l1.set(1, "x");

        Assert.assertEquals("a", l1.get(0));
        Assert.assertEquals("x", l1.get(1));
        Assert.assertEquals("c", l1.get(2));
    }

    /**
     * Test of add method, of class LineList.
     */
    @Test
    public void testAdd_int_GenericType() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        l1.add(0, "x");
        l1.add(3, "y");

        Assert.assertEquals("x", l1.get(0));
        Assert.assertEquals("a", l1.get(1));
        Assert.assertEquals("b", l1.get(2));
        Assert.assertEquals("y", l1.get(3));
        Assert.assertEquals("c", l1.get(4));
    }

    /**
     * Test of remove method, of class LineList.
     */
    @Test
    public void testRemove_int() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        l1.remove(1);

        Assert.assertEquals("a", l1.get(0));
        Assert.assertEquals("c", l1.get(1));
    }

    /**
     * Test of indexOf method, of class LineList.
     */
    @Test
    public void testIndexOf() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("b");
        l1.add("c");

        Assert.assertEquals(1, l1.indexOf("b"));
    }

    /**
     * Test of lastIndexOf method, of class LineList.
     */
    @Test
    public void testLastIndexOf() {
        LineList<String> l1 = new LineList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("b");
        l1.add("c");

        Assert.assertEquals(2, l1.lastIndexOf("b"));
    }

    /**
     * Test of listIterator method, of class LineList.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testListIterator_0args() {
        new LineList<>().listIterator();
    }

    /**
     * Test of listIterator method, of class LineList.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testListIterator_int() {
        new LineList<>().listIterator(0);
    }

    /**
     * Test of subList method, of class LineList.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testSubList() {
        new LineList<>().subList(0, 1);
    }

}

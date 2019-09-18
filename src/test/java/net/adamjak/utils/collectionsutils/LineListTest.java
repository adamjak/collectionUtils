/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.adamjak.utils.collectionsutils;

import java.util.Collections;
import java.util.List;
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
    public void testAddAll() {
        LineList<String> l1 = new LineList<>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        LineList<String> l2 = new LineList<>();
        l2.add("1");
        l2.add("2");
        l2.add("3");

        LineList<String> l3 = new LineList<>();

        l1.addAll(0, l2);

        String exp1 = "123abc";
        String r1 = String.join("", l1);

        l1.addAll(3, l2);

        String exp2 = "123123abc";
        String r2 = String.join("", l1);

        l2.addAll(0, l3);

        String exp3 = "123";
        String r3 = String.join("", l2);

        l3.addAll(0, l2);

        String exp4 = "123";
        String r4 = String.join("", l3);

        Assert.assertEquals(exp1, r1);
        Assert.assertEquals(exp2, r2);
        Assert.assertEquals(exp3, r3);
        Assert.assertEquals(exp4, r4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllException1() {
        new LineList().add(-1, Collections.EMPTY_LIST);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllException2() {
        new LineList().add(3, Collections.EMPTY_LIST);
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException1() {
        new LineList().get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException2() {
        new LineList().get(5);
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException1() {
        new LineList().set(-1, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException2() {
        new LineList().set(5, "a");
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_int_GenericTypeException1() {
        new LineList().add(-1, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_int_GenericTypeException2() {
        new LineList().add(5, "a");
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove_intException1() {
        new LineList().remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove_intException2() {
        new LineList().remove(2);
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
        Assert.assertEquals(-1, l1.indexOf("x"));
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
    @Test
    public void testSubList() {
        LineList<String> l = new LineList<>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        l.add("f");

        List<String> r = l.subList(1, 4);

        Assert.assertEquals(3, r.size());
        Assert.assertEquals("b", r.get(0));
        Assert.assertEquals("c", r.get(1));
        Assert.assertEquals("d", r.get(2));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException1() {
        new LineList().subList(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException2() {
        new LineList().subList(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListException3() {
        new LineList().subList(0, -1);
    }

}

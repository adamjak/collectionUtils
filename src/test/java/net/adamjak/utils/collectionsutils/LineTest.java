/*
 * Copyright (c) 2019, Tomas Adamjak <tomas@adamjak.net>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 *     - Neither the name of Tomas Adamjak <tomas@adamjak.net>,  nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.adamjak.utils.collectionsutils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Adamjak <tomas@adamjak.net>
 */
public class LineTest {

    public LineTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of addToEnd method, of class Line.
     */
    @Test
    public void testAddToEndGenericType() {
        Line<String> line = new Line<>("a");
        line.addToEnd("b");

        Assert.assertEquals("a", line.getStart().getObject());
        Assert.assertEquals("b", line.getEnd().getObject());
    }

    /**
     * Test of addToEnd method, of class Line.
     */
    @Test
    public void testAddToEndLine() {
        Line<String> line1 = new Line<>();
        line1.addToEnd("a");

        Line<String> line2 = new Line<>();
        line2.addToEnd("b");

        line1.addToEnd(line2);

        Assert.assertEquals("a", line1.getStart().getObject());
        Assert.assertEquals("b", line1.getEnd().getObject());
    }

    /**
     * Test of addAfter method, of class Line.
     */
    @Test
    public void testAddAfter() {
        Line<String> line1 = new Line<>();
        line1.addToEnd("a");
        line1.addToEnd("b");
        line1.addAfter("b", "c");
        line1.addAfter("b", "x");

        Line<String> line2 = new Line<>();
        line2.addToEnd("1");
        line2.addToEnd("2");

        Assert.assertTrue(line1.addAfter("b", line2));
        Assert.assertTrue(line1.addAfter("c", line2));

        Assert.assertFalse(line1.addAfter("x", new Line<>()));
        Assert.assertFalse(line1.addAfter("w", "v"));
        Assert.assertFalse(line1.addAfter("w", line2));

        String lineToString = StreamSupport.stream(line1.spliterator(), true).map(Object::toString).collect(Collectors.joining(""));

        Assert.assertEquals("ab12xc12", lineToString);
    }

    /**
     * Test of addBefore method, of class Line.
     */
    @Test
    public void testAddBefore() {
        Line<String> line1 = new Line<>();
        line1.addToEnd("a");
        line1.addToEnd("b");
        line1.addBefore("b", "x");

        Line<String> line2 = new Line<>();
        line2.addToEnd("1");
        line2.addToEnd("2");

        Assert.assertTrue(line1.addBefore("b", line2));

        Assert.assertTrue(line2.addBefore("1", "0"));
        Assert.assertTrue(line1.addBefore("a", line2));
        Assert.assertFalse(line1.addBefore("x", new Line<>()));
        Assert.assertFalse(line1.addBefore("w", "v"));
        Assert.assertFalse(line1.addBefore("w", line2));
        String lineToString = StreamSupport.stream(line1.spliterator(), true).map(Object::toString).collect(Collectors.joining(""));

        Assert.assertEquals("012ax12b", lineToString);
    }

    /**
     * Test of remove method, of class Line.
     */
    @Test
    public void testRemove() {
        Line<String> line = new Line<>();
        line.addToEnd("a");
        line.addToEnd("b");
        line.addToEnd("c");
        line.addToEnd("d");
        line.addToEnd("e");

        Assert.assertEquals("a", line.getStart().getObject());
        Assert.assertEquals("e", line.getEnd().getObject());

        line.remove("d");
        Assert.assertEquals("a", line.getStart().getObject());
        Assert.assertEquals("e", line.getEnd().getObject());

        line.remove("e");
        Assert.assertEquals("a", line.getStart().getObject());
        Assert.assertEquals("c", line.getEnd().getObject());

        line.remove("a");
        Assert.assertEquals("b", line.getStart().getObject());
        Assert.assertEquals("c", line.getEnd().getObject());

        Assert.assertFalse(line.remove("g"));
    }

    /**
     * Test of clear method, of class Line.
     */
    @Test
    public void testClear() {
        Line<String> line = new Line<>();
        line.addToEnd("a");
        line.addToEnd("b");
        line.addToEnd("c");
        line.addToEnd("d");

        Assert.assertTrue(line.iterator().hasNext());

        line.clear();
        Assert.assertFalse(line.iterator().hasNext());

    }

    /**
     * Test of {@link Line#cloneLine(net.adamjak.utils.collectionsutils.Line)} method, of class Line.
     */
    @Test
    public void testCloneLine() {
        Line<String> line1 = new Line<>();
        line1.addToEnd("a");
        line1.addToEnd("b");
        line1.addToEnd("c");

        String cloneLineToString = StreamSupport.stream(Line.cloneLine(line1).spliterator(), true).map(Object::toString).collect(Collectors.joining(""));

        Assert.assertEquals("abc", cloneLineToString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCloneLineIllegalArgumentException() {
        Line<String> nullLine = null;
        Line.cloneLine(nullLine);
    }

}

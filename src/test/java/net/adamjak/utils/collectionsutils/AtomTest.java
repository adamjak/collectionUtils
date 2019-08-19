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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Adamjak <tomas@adamjak.net>
 */
public class AtomTest {

    private Atom<String> stringAtom;
    private Atom<Integer> integerAtom1;
    private Atom<Integer> integerAtom2;

    public AtomTest() {
    }

    @Before
    public void setUp() {
        this.stringAtom = new Atom<>("Hello", null);
        this.integerAtom1 = new Atom<>(1, this.integerAtom2);
        this.integerAtom2 = new Atom<>();
    }

    /**
     * Test of getObject method and setObject method, of class Atom.
     */
    @Test
    public void testGetSetObject() {
        String stringAtomData = "Hello";
        Integer integerAtom1Data = 1;

        Assert.assertEquals(stringAtomData, this.stringAtom.getObject());
        Assert.assertEquals(integerAtom1Data, this.integerAtom1.getObject());

        Assert.assertNull(this.integerAtom2.getObject());

        Integer integerAtom2Data = Integer.SIZE;
        this.integerAtom2.setObject(Integer.SIZE);

        Assert.assertEquals(integerAtom2Data, this.integerAtom2.getObject());
    }

    /**
     * Test of getNext method and setNext method, of class Atom.
     */
    @Test
    public void testGetSetNext() {
        Assert.assertNull(this.stringAtom.getNext());

        this.integerAtom2.setNext(this.integerAtom1);
        Assert.assertEquals(this.integerAtom1, this.integerAtom2.getNext());
    }

}

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

import java.util.Iterator;

/**
 *
 * @author Tomas Adamjak <tomas@adamjak.net>
 */
public class Line<T> implements Iterable<T> {

    private Atom<T> start, end;

    public Line() {
    }

    public Line(T initialObject) {
        Atom<T> newAtom = new Atom<>(initialObject, null);
        this.start = this.end = newAtom;
    }

    public void addToEnd(T insertedObject) {
        Atom<T> newAtom = new Atom<>(insertedObject, null);

        if (this.start == null) {
            this.start = this.end = newAtom;
        } else {
            this.end.setNext(newAtom);
            this.end = newAtom;
        }

    }

    public void addToEnd(Line<T> insertedLine) {
        for (T t : insertedLine) {
            this.addToEnd(t);
        }
    }

    public boolean addAfter(T searched, T insertedObject) {
        Atom<T> current = this.start;

        while (true) {
            if (current.getObject().equals(searched) == false && current.getNext() == null) {
                return false;
            } else if (current.getObject().equals(searched)) {
                Atom<T> newAtom = new Atom<>(insertedObject, current.getNext());
                current.setNext(newAtom);
                return true;
            } else {
                current = current.getNext();
            }
        }
    }

    public boolean addAfter(T searched, Line<T> insertedLine) {
        if (insertedLine.iterator().hasNext() == false) {
            return false;
        }
        Atom<T> current = this.start;

        while (true) {
            if (current.getObject().equals(searched) == false && current.getNext() == null) {
                return false;
            } else if (current.getObject().equals(searched)) {

                Atom<T> beforeNextInserted = current;
                Atom<T> afterLine = current.getNext();

                for (T t : insertedLine) {
                    Atom<T> newAtom = new Atom<>(t, afterLine);
                    beforeNextInserted.setNext(newAtom);
                    beforeNextInserted = newAtom;
                }

                if (current.equals(this.end)) {
                    this.end = afterLine;
                }

                return true;
            } else {
                current = current.getNext();
            }
        }
    }

    public boolean addBefore(T searched, T insertedObject) {
        if (this.start.getObject().equals(searched)) {
            Atom<T> newAtom = new Atom<>(insertedObject, this.start);
            this.start = newAtom;
            return true;
        }

        Atom<T> current = this.start;
        while (current.getNext() != null) {
            if (current.getNext().getObject().equals(searched)) {
                Atom<T> newAtom = new Atom<>(insertedObject, current.getNext());
                current.setNext(newAtom);
                return true;
            } else {
                current = current.getNext();
            }
        }
        return false;
    }

    public boolean addBefore(T searched, Line<T> insertedLine) {
        if (insertedLine.iterator().hasNext() == false) {
            return false;
        }
        if (this.start.getObject().equals(searched)) {

            Line<T> newLine = Line.cloneLine(insertedLine);
            newLine.addToEnd(this);
            this.start = newLine.getStart();
            this.end = newLine.getEnd();
            return true;
        }

        Atom<T> current = this.start;
        while (current.getNext() != null) {
            if (current.getNext().getObject().equals(searched)) {
                final Line<T> cloneLine = Line.cloneLine(insertedLine);

                cloneLine.end.setNext(current.getNext());
                current.setNext(cloneLine.getStart());
                return true;
            }

            current = current.getNext();
        }

        return false;

    }

    public boolean remove(T removedObject) {
        if (this.start.getObject().equals(removedObject)) {
            this.start = this.start.getNext();
            return true;
        }

        Atom<T> current = this.start;
        while (current.getNext() != null) {
            if (current.getNext().getObject().equals(removedObject)) {
                if (this.end.getObject().equals(removedObject)) {
                    this.end = current;
                }
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();

        }

        return false;
    }

    Atom<T> getStart() {
        return this.start;
    }

    Atom<T> getEnd() {
        return this.end;
    }

    public void clear() {
        this.start = this.end = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LineIterator<>(this);

    }

    /**
     * Create clone of line. Method create clone of line and line elements but not of object into line.
     *
     * @param <T> type of objects in line
     * @param line line to clone
     * @return New line with same objects.
     * @throws IllegalArgumentException if param line is null
     */
    public static <T> Line<T> cloneLine(Line<T> line) throws IllegalArgumentException {
        if (line == null) {
            throw new IllegalArgumentException("Param line can not be null");
        }

        Line<T> newLine = new Line<T>();
        final Iterator<T> iterator = line.iterator();
        while (iterator.hasNext()) {
            newLine.addToEnd(iterator.next());
        }

        return newLine;
    }

}

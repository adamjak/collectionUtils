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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.StreamSupport;

/**
 *
 * @author Tomas Adamjak <tomas@adamjak.net>
 */
public class LineList<T> implements List<T> {

    private Line<T> line;

    public LineList() {
        this.line = new Line<>();
    }

    /**
     *
     * @return Return clone of {@link Line} whitch is internal representation of this list.
     */
    public Line<T> getLine() {
        return Line.cloneLine(line);
    }

    @Override
    public int size() {
        return Long.valueOf(StreamSupport.stream(this.line.spliterator(), false).count()).intValue();
    }

    @Override
    public boolean isEmpty() {
        return this.line.iterator().hasNext() == false;
    }

    @Override
    public boolean contains(Object o) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException("Param o can not be null!");
        }

        for (T t : this.line) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.line.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size()];
        int i = 0;
        for (T t : this.line) {
            arr[i] = t;
            i++;
        }
        return arr;
    }

    @Override
    public <U> U[] toArray(U[] array) {
        if (array == null) {
            throw new NullPointerException("Param array from method toArray(U[] array) can not be null!");
        }
        U[] arr = (U[]) Array.newInstance(array.getClass().getComponentType(), this.size());

        int i = 0;
        for (T t : this.line) {
            arr[i] = (U) t;
            i++;
        }

        return arr;
    }

    @Override
    public boolean add(T e) {
        this.line.addToEnd(e);
        return true;
    }

    @Override
    public boolean remove(Object o) throws ClassCastException {
        T remove = (T) o;
        return this.line.remove(remove);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Param c can not be null!");
        }
        return c.stream().noneMatch((object) -> (this.contains(object) == false));
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach((t) -> {
            this.line.addToEnd(t);
        });
        return c.isEmpty() == false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        LineList<T> ll = new LineList<>();
        ll.addAll(c);

        if ((index == 0 && c.isEmpty()) || (index == 0 && this.isEmpty()) || index == this.size()) {
            return this.addAll(c);
        }

        if (index == 0) {
            this.line.addBefore(this.get(0), ll.getLine());
        } else {
            this.line.addAfter(this.get(index - 1), ll.getLine());
        }
        return ll.isEmpty() == false;
    }

    @Override
    public boolean removeAll(Collection<?> c) throws ClassCastException {
        boolean change = false;
        for (Object o : c) {
            boolean remove = this.remove(o);
            if (remove == true) {
                change = true;
            }
        }
        return change;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Param c can not be null!");
        }
        boolean returnStatus = false;
        for (Object object : c) {
            if (this.contains(object)) {
                boolean removingStatus = this.remove(object);
                if (returnStatus == false && removingStatus == true) {
                    returnStatus = true;
                }
            }
        }
        return returnStatus;
    }

    @Override
    public void clear() {
        this.line.clear();
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be great that 0!");
        }
        int i = 0;
        for (T t : this.line) {
            if (i == index) {
                return t;
            }
            i++;
        }

        throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        final T replacedElement = this.get(index);
        this.line.addBefore(replacedElement, element);
        this.line.remove(replacedElement);

        return replacedElement;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            this.line.addToStart(element);
        } else {
            this.line.addAfter(this.get(index - 1), element);
        }
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index must be great that 0 and smaller that list size!");
        }
        int i = 0;
        for (T t : this.line) {
            if (i == index) {
                this.remove(t);
                return t;
            }
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object o) throws ClassCastException {
        int index = 0;
        T searched = (T) o;
        for (T t : this.line) {
            if (t.equals(searched)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) throws ClassCastException {
        int lastIndex = -1;
        int index = 0;
        T searched = (T) o;
        for (T t : this.line) {
            if (t.equals(searched)) {
                lastIndex = index;
            }
            index++;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex)");
        }

        LineList<T> newList = new LineList<>();
        int size = this.size();
        for (int i = 0; i < size; i++) {
            if (i >= fromIndex && i < toIndex) {
                newList.add(this.get(i));
            }
        }

        return newList;
    }

}

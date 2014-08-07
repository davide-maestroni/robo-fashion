/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bmd.android.collection.filter;

import com.bmd.android.collection.iterator.SparseIterable;

import java.util.Collection;

/**
 * This interface defines a builder of filters associated with a
 * {@link com.bmd.android.collection.iterator.SparseIterable} instance.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <T> The iterable type.
 * @param <E> The element type.
 */
public interface FilterBuilder<T extends SparseIterable<E>, E> {

    /**
     * Creates a filter matching the specified object and returns the filtered iterable.
     *
     * @param element The object to match with the iterable element.
     * @return The filtered iterable.
     */
    public T element(Object element);

    /**
     * Creates a filter matching the specified objects and returns the filtered iterable.
     *
     * @param elements The objects to match with the iterable element.
     * @return The filtered iterable.
     */
    public T elements(Object... elements);

    /**
     * Creates a filter matching the specified collection of objects and returns the filtered
     * iterable.
     *
     * @param elements The collection to match with the iterable element.
     * @return The filtered iterable.
     */
    public T elements(Collection<?> elements);

    /**
     * Creates a filter matching the objects returned by the specified iterable and returns the
     * filtered iterable.
     *
     * @param elements The iterable of objects to match with the iterable element.
     * @return The filtered iterable.
     */
    public T elements(Iterable<?> elements);

    /**
     * Creates a filter matching the first <code>count</code> elements and returns the filtered
     * iterable.
     *
     * @param count The elements count.
     * @return The filtered iterable.
     */
    public T first(int count);

    /**
     * Creates a filter matching all the elements greater or equal than the specified index and
     * returns the filtered iterable.
     *
     * @param index The index from which the elements match.
     * @return The filtered iterable.
     */
    public T from(int index);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * array and returns the filtered iterable.
     *
     * @param indexes The indexes of the matching elements.
     * @return The filtered iterable.
     */
    public T indexes(int... indexes);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * collection and returns the filtered iterable.
     *
     * @param indexes The collection of indexes of the matching elements.
     * @return The filtered iterable.
     */
    public T indexes(Collection<Integer> indexes);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * iterable and returns the filtered iterable.
     *
     * @param indexes The iterable returning the indexes of the matching elements.
     * @return The filtered iterable.
     */
    public T indexes(Iterable<Integer> indexes);

    /**
     * Creates a filter matching the last <code>count</code> elements and returns the filtered
     * iterable.
     *
     * @param count The elements count.
     * @return The filtered iterable.
     */
    public T last(int count);

    /**
     * Add the specified filter to the iterable and returns the latter.
     *
     * @param filter The filter.
     * @return The filtered iterable.
     */
    public T matching(Filter<E> filter);

    /**
     * Add the specified advanced filter to the iterable and returns the latter.
     *
     * @param filter The filter.
     * @return The filtered iterable.
     */
    public T matching(AdvancedFilter<E> filter);

    /**
     * Creates a filter matching all the elements less or equal than the specified index and
     * returns the filtered iterable.
     *
     * @param index The index until which the elements match.
     * @return The filtered iterable.
     */
    public T to(int index);
}
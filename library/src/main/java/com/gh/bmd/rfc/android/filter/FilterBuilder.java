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
package com.gh.bmd.rfc.android.filter;

import com.gh.bmd.rfc.android.iterator.SparseIterable;

import java.util.Collection;

/**
 * This interface defines a builder of filters associated with a
 * {@link com.gh.bmd.rfc.android.iterator.SparseIterable} instance.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <T> the iterable type.
 * @param <E> the element type.
 */
public interface FilterBuilder<T extends SparseIterable<E>, E> {

    /**
     * Creates a filter matching the specified object and returns the filtered iterable.
     *
     * @param element the object to match with the iterable element.
     * @return the filtered iterable.
     */
    public T element(Object element);

    /**
     * Creates a filter matching the specified objects and returns the filtered iterable.
     *
     * @param elements the objects to match with the iterable element.
     * @return the filtered iterable.
     */
    public T elements(Object... elements);

    /**
     * Creates a filter matching the specified collection of objects and returns the filtered
     * iterable.
     *
     * @param elements the collection to match with the iterable element.
     * @return the filtered iterable.
     */
    public T elements(Collection<?> elements);

    /**
     * Creates a filter matching the objects returned by the specified iterable and returns the
     * filtered iterable.
     *
     * @param elements the iterable of objects to match with the iterable element.
     * @return the filtered iterable.
     */
    public T elements(Iterable<?> elements);

    /**
     * Creates a filter matching the first <code>count</code> elements and returns the filtered
     * iterable.
     *
     * @param count the elements count.
     * @return the filtered iterable.
     */
    public T first(int count);

    /**
     * Creates a filter matching all the elements greater or equal than the specified index and
     * returns the filtered iterable.
     *
     * @param index the index from which the elements match.
     * @return the filtered iterable.
     */
    public T from(int index);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * array and returns the filtered iterable.
     *
     * @param indexes the indexes of the matching elements.
     * @return the filtered iterable.
     */
    public T indexes(int... indexes);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * collection and returns the filtered iterable.
     *
     * @param indexes the collection of indexes of the matching elements.
     * @return the filtered iterable.
     */
    public T indexes(Collection<Integer> indexes);

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * iterable and returns the filtered iterable.
     *
     * @param indexes the iterable returning the indexes of the matching elements.
     * @return the filtered iterable.
     */
    public T indexes(Iterable<Integer> indexes);

    /**
     * Creates a filter matching the last <code>count</code> elements and returns the filtered
     * iterable.
     *
     * @param count the elements count.
     * @return the filtered iterable.
     */
    public T last(int count);

    /**
     * Add the specified filter to the iterable and returns the latter.
     *
     * @param filter the filter.
     * @return the filtered iterable.
     */
    public T matching(Filter<E> filter);

    /**
     * Add the specified advanced filter to the iterable and returns the latter.
     *
     * @param filter the filter.
     * @return the filtered iterable.
     */
    public T matching(AdvancedFilter<E> filter);

    /**
     * Creates a filter matching all the elements less or equal than the specified index and
     * returns the filtered iterable.
     *
     * @param index the index until which the elements match.
     * @return the filtered iterable.
     */
    public T to(int index);
}
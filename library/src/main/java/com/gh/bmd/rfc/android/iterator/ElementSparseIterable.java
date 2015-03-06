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
package com.gh.bmd.rfc.android.iterator;

import com.gh.bmd.rfc.android.filter.Filter;
import com.gh.bmd.rfc.android.filter.FilterBuilder;
import com.gh.bmd.rfc.android.translator.Translator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface extends the {@link SparseIterable} one by adding specific methods handling
 * serialization of the elements to lists and arrays.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @param <E> the element type.
 */
public interface ElementSparseIterable<E> extends SparseIterable<E> {

    @Override
    public FilterBuilder<? extends ElementSparseIterable<E>, E> but();

    @Override
    public ElementSparseIterable<E> but(Filter<E> filter);

    @Override
    public ElementSparseIterable<E> doWhile(Condition<E> condition);

    @Override
    public ElementSparseIterable<E> forEach(Action<E> action);

    @Override
    public FilterBuilder<? extends ElementSparseIterable<E>, E> only();

    @Override
    public ElementSparseIterable<E> only(Filter<E> filter);

    @Override
    public ElementSparseIterable<E> remove();

    @Override
    public ElementSparseIterable<E> retain();

    @Override
    public ElementSparseIterable<E> reverse();

    /**
     * Fills the specified collection with the elements returned by this iterable, in the iteration
     * order.
     *
     * @param collection the collection to fill.
     * @return this iterable.
     */
    public ElementSparseIterable<E> fill(Collection<? super E> collection);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the index 0 of the array.
     * <p/>
     * Note that, if the elements returned by the iterable cannot be cast to the array elements
     * type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array the array to fill.
     * @param <T>   the array elements type.
     * @return this iterable.
     */
    public <T> ElementSparseIterable<E> fill(T[] array);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the specified offset inside the array.
     * <p/>
     * Note that, if the elements returned by the iterable cannot be cast to the array elements
     * type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array  the array to fill.
     * @param offset the offset from which to start filling the array.
     * @param <T>    the array elements type.
     * @return this iterable.
     */
    public <T> ElementSparseIterable<E> fill(T[] array, int offset);

    /**
     * Creates and returns a new array filled with the elements returned by this iterable, in the
     * iteration order.
     * <p/>
     * Note that, if the elements returned by the iterable cannot be cast to the array elements
     * type, a {@link java.lang.ClassCastException} will be thrown.
     *
     * @param type the array elements class.
     * @param <T>  the array elements type.
     * @return the new array.
     */
    public <T> T[] toArray(Class<T> type);

    /**
     * Creates and returns a new list filled with the elements returned by this iterable, in the
     * iteration order.
     *
     * @return the new list.
     */
    public ArrayList<E> toList();

    /**
     * Returns a new iterable whose elements are the same as this ones but translated through the
     * specified translator.
     * <p/>
     * Note that all the filters and the iteration order are retained in the translation.
     *
     * @param translator the translator used to convert the elements.
     * @param <T>        the new iterable elements type.
     * @return the new iterable.
     */
    public <T> ElementSparseIterable<T> translate(Translator<E, T> translator);
}
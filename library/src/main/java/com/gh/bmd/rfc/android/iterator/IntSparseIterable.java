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
import com.gh.bmd.rfc.android.filter.IntFilterBuilder;

import java.util.Collection;

/**
 * This interface extends the {@link ElementSparseIterable} one by adding specific methods handling
 * int elements.
 * <p/>
 * Created by davide on 3/10/14.
 */
public interface IntSparseIterable extends ElementSparseIterable<Integer> {

    @Override
    public IntFilterBuilder but();

    @Override
    public IntSparseIterable but(Filter<Integer> filter);

    @Override
    public IntSparseIterable doWhile(Condition<Integer> condition);

    @Override
    public IntSparseIterable forEach(Action<Integer> action);

    @Override
    public IntFilterBuilder only();

    @Override
    public IntSparseIterable only(Filter<Integer> filter);

    @Override
    public IntSparseIterable remove();

    @Override
    public IntSparseIterable retain();

    @Override
    public IntSparseIterable reverse();

    @Override
    public IntSparseIterable fill(Collection<? super Integer> collection);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the index 0 of the array.
     * <p/>
     * Note that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array the array to fill.
     * @return this iterable.
     */
    public IntSparseIterable fill(int[] array);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the specified offset inside the array.
     * <p/>
     * Note that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array  the array to fill.
     * @param offset the offset from which to start filling the array.
     * @return this iterable.
     */
    public IntSparseIterable fill(int[] array, int offset);

    /**
     * Creates and returns a new array filled with the elements returned by this iterable, in the
     * iteration order.
     *
     * @return the new array.
     */
    public int[] toArray();
}
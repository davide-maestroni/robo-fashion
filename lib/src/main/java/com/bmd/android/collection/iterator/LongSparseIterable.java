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
package com.bmd.android.collection.iterator;

import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.LongFilterBuilder;

import java.util.Collection;

/**
 * This interface extends the {@link com.bmd.android.collection.iterator.ElementSparseIterable} one
 * by adding specific methods handling long elements.
 * <p/>
 * Created by davide on 3/10/14.
 */
public interface LongSparseIterable extends ElementSparseIterable<Long> {

    @Override
    public LongFilterBuilder but();

    @Override
    public LongSparseIterable but(Filter<Long> filter);

    @Override
    public LongSparseIterable doWhile(Condition<Long> condition);

    @Override
    public LongSparseIterable forEach(Action<Long> action);

    @Override
    public LongFilterBuilder only();

    @Override
    public LongSparseIterable only(Filter<Long> filter);

    @Override
    public LongSparseIterable remove();

    @Override
    public LongSparseIterable retain();

    @Override
    public LongSparseIterable reverse();

    @Override
    public LongSparseIterable fill(Collection<? super Long> collection);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the index 0 of the array.
     * <p/>
     * Note that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array The array to fill.
     * @return This iterable.
     */
    public LongSparseIterable fill(long[] array);

    /**
     * Fills the specified array with the elements returned by this iterable, in the iteration
     * order, starting from the specified offset inside the array.
     * <p/>
     * Note that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array  The array to fill.
     * @param offset The offset from which to start filling the array.
     * @return This iterable.
     */
    public LongSparseIterable fill(long[] array, int offset);

    /**
     * Creates and returns a new array filled with the elements returned by this iterable, in the
     * iteration order.
     *
     * @return The new array.
     */
    public long[] toArray();
}
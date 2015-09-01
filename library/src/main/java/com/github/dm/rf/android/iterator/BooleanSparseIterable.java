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
package com.github.dm.rf.android.iterator;

import com.github.dm.rf.android.filter.BooleanFilterBuilder;
import com.github.dm.rf.android.filter.Filter;

import java.util.Collection;

/**
 * This interface extends the {@link ElementSparseIterable} one by adding specific methods handling
 * boolean elements.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 */
public interface BooleanSparseIterable extends ElementSparseIterable<Boolean> {

    @Override
    public BooleanFilterBuilder but();

    @Override
    public BooleanSparseIterable but(Filter<Boolean> filter);

    @Override
    public BooleanSparseIterable doWhile(Condition<Boolean> condition);

    @Override
    public BooleanSparseIterable forEach(Action<Boolean> action);

    @Override
    public BooleanFilterBuilder only();

    @Override
    public BooleanSparseIterable only(Filter<Boolean> filter);

    @Override
    public BooleanSparseIterable remove();

    @Override
    public BooleanSparseIterable retain();

    @Override
    public BooleanSparseIterable reverse();

    @Override
    public BooleanSparseIterable fill(Collection<? super Boolean> collection);

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
    public BooleanSparseIterable fill(boolean[] array);

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
    public BooleanSparseIterable fill(boolean[] array, int offset);

    /**
     * Creates and returns a new array filled with the elements returned by this iterable, in the
     * iteration order.
     *
     * @return the new array.
     */
    public boolean[] toArray();
}
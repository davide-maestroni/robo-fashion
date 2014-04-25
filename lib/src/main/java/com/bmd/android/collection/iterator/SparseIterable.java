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
import com.bmd.android.collection.filter.FilterBuilder;
import com.bmd.android.collection.translator.Translator;

import java.util.Collection;

/**
 * This interface defines the base functionalities of an {@link java.lang.Iterable} wrapping an
 * Android sparse collection, such as {@link android.util.SparseArray} and
 * {@link android.support.v4.util.SparseArrayCompat}.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @see com.bmd.android.collection.AndroidCollections
 */
public interface SparseIterable<E> extends Iterable<E> {

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * <code>true</code>.
     *
     * @param condition The condition to apply.
     * @return Whether the call to the condition returns <code>true</code> for at least one element.
     */
    public boolean any(Condition<E> condition);

    /**
     * Returns an exclusion filter builder.
     *
     * @return The filter builder instance.
     */
    public FilterBuilder<? extends SparseIterable<E>, E> but();

    /**
     * Concatenates an exclusion filter to the existing ones, if any.
     * <p/>
     * Note that the specified filter can be both a simple
     * {@link com.bmd.android.collection.filter.Filter} or an
     * {@link com.bmd.android.collection.filter.AdvancedFilter} instance.
     *
     * @param filter The filter to add.
     * @return This iterable.
     */
    public SparseIterable<E> but(Filter<E> filter);

    /**
     * Checks if the elements returned by the current filtered iterator contain the specified
     * object.
     *
     * @param element The element to search for.
     * @return Whether the element belongs to the iteration.
     */
    public boolean contains(Object element);

    /**
     * Checks if the elements returned by the current filtered iterator contain all the specified
     * objects in any order.
     *
     * @param elements The elements to search for.
     * @return Whether all the elements belong to the iteration.
     */
    public boolean containsAll(Object... elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain all the ones
     * returned by the specified iterable in any order.
     *
     * @param elements The iterable of elements to search for.
     * @return Whether all the elements belong to the iteration.
     */
    public boolean containsAll(Iterable<?> elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain at least one of
     * the specified objects.
     *
     * @param elements The elements to search for.
     * @return Whether any of the elements belong to the iteration.
     */
    public boolean containsAny(Object... elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain any of the ones
     * returned by the specified iterable.
     *
     * @param elements The iterable of elements to search for.
     * @return Whether any of the elements belong to the iteration.
     */
    public boolean containsAny(Iterable<?> elements);

    /**
     * Returns the total number of times an element equal to the specified one is encountered
     * during the iteration.
     *
     * @param element The element to search for.
     * @return The total occurrences.
     */
    public int countOf(Object element);

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * <code>false</code>.
     *
     * @param condition The condition to apply.
     * @return This iterable.
     */
    public SparseIterable<E> doWhile(Condition<E> condition);

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * <code>false</code>.
     *
     * @param condition The condition to apply.
     * @return Whether the call to the condition returns <code>true</code> for each element.
     */
    public boolean each(Condition<E> condition);

    /**
     * Finds the index of the first element, in the iteration order, equals to the specified one.
     *
     * @param element The element to search for.
     * @return The index in the iteration, or -1 if the object is not found.
     */
    public int firstIndexOf(Object element);

    /**
     * Finds the position of the first element, in the iteration order, equal to the specified one.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param element The element to search for.
     * @return The position in the iteration, or -1 if the object is not found.
     */
    public int firstPositionOf(Object element);

    /**
     * Applies the specified action to all the elements returned by the iterator in the iteration
     * order.
     *
     * @param action The action to apply.
     * @return This iterable.
     */
    public SparseIterable<E> forEach(Action<E> action);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified collection.
     *
     * @param collection The collection to compare.
     * @return Whether the specified collection equals this iterable.
     */
    public boolean isEqualTo(Collection<?> collection);

    /**
     * Checks if every element returned by the specified iterable is equal to the one returned by
     * this iterator in the same order.
     *
     * @param iterable The iterable to compare.
     * @return Whether the specified iterable equals this one.
     */
    public boolean isStrictlyEqualTo(Iterable<?> iterable);

    /**
     * Returns an inclusion filter builder.
     *
     * @return The filter builder instance.
     */
    public FilterBuilder<? extends SparseIterable<E>, E> only();

    /**
     * Concatenates an inclusion filter to the existing ones, if any.
     * <p/>
     * Note that the specified filter can be both a simple
     * {@link com.bmd.android.collection.filter.Filter} or an
     * {@link com.bmd.android.collection.filter.AdvancedFilter} instance.
     *
     * @param filter The filter to add.
     * @return This iterable.
     */
    public SparseIterable<E> only(Filter<E> filter);

    /**
     * Removes from the wrapped sparse collection all the elements returned by this iterable.
     *
     * @return This iterable
     */
    public SparseIterable<E> remove();

    /**
     * Retains in the wrapped sparse collection all the elements returned by this iterable. That
     * is, all the other elements will be removed.
     *
     * @return This iterable
     */
    public SparseIterable<E> retain();

    /**
     * Reverse the iteration order.
     *
     * @return This iterable
     */
    public SparseIterable<E> reverse();

    /**
     * Transforms this iterator in a
     * {@link com.bmd.android.collection.iterator.BooleanSparseIterable} by applying the specified
     * translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator The translator.
     * @return The new iterable.
     */
    public BooleanSparseIterable toBooleans(Translator<E, Boolean> translator);

    /**
     * Transforms this iterator in a {@link com.bmd.android.collection.iterator.IntSparseIterable}
     * by applying the specified translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator The translator.
     * @return The new iterable.
     */
    public IntSparseIterable toIntegers(Translator<E, Integer> translator);

    /**
     * Transforms this iterator in a {@link com.bmd.android.collection.iterator.LongSparseIterable}
     * by applying the specified translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator The translator.
     * @return The new iterable.
     */
    public LongSparseIterable toLongs(Translator<E, Long> translator);

    /**
     * This interface defines an action to be applied to the iterable elements.
     *
     * @param <E> The element type.
     */
    public interface Action<E> {

        /**
         * This function will be called for each element returned by the iterable.
         *
         * @param element The element instance.
         * @param count   The number of element iterated until now.
         * @param index   The index of the element in the sparse collection.
         */
        public void onNext(E element, int count, int index);
    }

    /**
     * This interface defines a condition to be applied to the iterable elements.
     *
     * @param <E> The element type.
     */
    public interface Condition<E> {

        /**
         * This function will be called for each element returned by the iterable.
         *
         * @param element The element instance.
         * @param count   The number of element iterated until now.
         * @param index   The index of the element in the sparse collection.
         * @return Whether to proceed with the iteration.
         */
        public boolean onNext(E element, int count, int index);
    }
}
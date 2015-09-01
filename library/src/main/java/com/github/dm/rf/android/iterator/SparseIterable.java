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

import com.github.dm.rf.android.filter.AdvancedFilter;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.FilterBuilder;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.v18.SparseCollections;

import java.util.Collection;

/**
 * This interface defines the base functionalities of an {@link java.lang.Iterable} wrapping an
 * Android sparse collection, such as {@link android.util.SparseArray} and
 * {@link android.support.v4.util.SparseArrayCompat}.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @see SparseCollections
 */
public interface SparseIterable<E> extends Iterable<E> {

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * true.
     *
     * @param condition the condition to apply.
     * @return whether the call to the condition returns true for at least one element.
     */
    public boolean any(Condition<E> condition);

    /**
     * Returns an exclusion filter builder.
     *
     * @return the filter builder instance.
     */
    public FilterBuilder<? extends SparseIterable<E>, E> but();

    /**
     * Concatenates an exclusion filter to the existing ones, if any.
     * <p/>
     * Note that the specified filter can be both a simple
     * {@link Filter} or an
     * {@link AdvancedFilter} instance.
     *
     * @param filter the filter to add.
     * @return this iterable.
     */
    public SparseIterable<E> but(Filter<E> filter);

    /**
     * Checks if the elements returned by the current filtered iterator contain the specified
     * object.
     *
     * @param element the element to search for.
     * @return whether the element belongs to the iteration.
     */
    public boolean contains(Object element);

    /**
     * Checks if the elements returned by the current filtered iterator contain all the specified
     * objects in any order.
     *
     * @param elements the elements to search for.
     * @return whether all the elements belong to the iteration.
     */
    public boolean containsAll(Object... elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain all the ones
     * returned by the specified iterable in any order.
     *
     * @param elements the iterable of elements to search for.
     * @return whether all the elements belong to the iteration.
     */
    public boolean containsAll(Iterable<?> elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain at least one of
     * the specified objects.
     *
     * @param elements the elements to search for.
     * @return whether any of the elements belong to the iteration.
     */
    public boolean containsAny(Object... elements);

    /**
     * Checks if the elements returned by the current filtered iterator contain any of the ones
     * returned by the specified iterable.
     *
     * @param elements the iterable of elements to search for.
     * @return whether any of the elements belong to the iteration.
     */
    public boolean containsAny(Iterable<?> elements);

    /**
     * Returns the total number of times an element equal to the specified one is encountered
     * during the iteration.
     *
     * @param element the element to search for.
     * @return the total occurrences.
     */
    public int countOf(Object element);

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * false.
     *
     * @param condition the condition to apply.
     * @return this iterable.
     */
    public SparseIterable<E> doWhile(Condition<E> condition);

    /**
     * Applies the specified condition to all the elements returned by the iterator in the
     * iteration order. The iteration is stopped as soon as the call to the condition returns with
     * false.
     *
     * @param condition the condition to apply.
     * @return whether the call to the condition returns true for each element.
     */
    public boolean each(Condition<E> condition);

    /**
     * Finds the index of the first element, in the iteration order, equals to the specified one.
     *
     * @param element the element to search for.
     * @return the index in the iteration, or -1 if the object is not found.
     */
    public int firstIndexOf(Object element);

    /**
     * Finds the position of the first element, in the iteration order, equal to the specified one.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param element the element to search for.
     * @return the position in the iteration, or -1 if the object is not found.
     */
    public int firstPositionOf(Object element);

    /**
     * Applies the specified action to all the elements returned by the iterator in the iteration
     * order.
     *
     * @param action the action to apply.
     * @return this iterable.
     */
    public SparseIterable<E> forEach(Action<E> action);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified collection.
     *
     * @param collection the collection to compare.
     * @return whether the specified collection equals this iterable.
     */
    public boolean isEqualTo(Collection<?> collection);

    /**
     * Checks if every element returned by the specified iterable is equal to the one returned by
     * this iterator in the same order.
     *
     * @param iterable the iterable to compare.
     * @return whether the specified iterable equals this one.
     */
    public boolean isStrictlyEqualTo(Iterable<?> iterable);

    /**
     * Returns an inclusion filter builder.
     *
     * @return the filter builder instance.
     */
    public FilterBuilder<? extends SparseIterable<E>, E> only();

    /**
     * Concatenates an inclusion filter to the existing ones, if any.
     * <p/>
     * Note that the specified filter can be both a simple
     * {@link Filter} or an
     * {@link AdvancedFilter} instance.
     *
     * @param filter the filter to add.
     * @return this iterable.
     */
    public SparseIterable<E> only(Filter<E> filter);

    /**
     * Removes from the wrapped sparse collection all the elements returned by this iterable.
     *
     * @return this iterable
     */
    public SparseIterable<E> remove();

    /**
     * Retains in the wrapped sparse collection all the elements returned by this iterable. That
     * is, all the other elements will be removed.
     *
     * @return this iterable
     */
    public SparseIterable<E> retain();

    /**
     * Reverse the iteration order.
     *
     * @return this iterable
     */
    public SparseIterable<E> reverse();

    /**
     * Transforms this iterator in a
     * {@link BooleanSparseIterable} by applying the specified
     * translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator the translator.
     * @return the new iterable.
     */
    public BooleanSparseIterable toBooleans(Translator<E, Boolean> translator);

    /**
     * Transforms this iterator in a {@link IntSparseIterable}
     * by applying the specified translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator the translator.
     * @return the new iterable.
     */
    public IntSparseIterable toIntegers(Translator<E, Integer> translator);

    /**
     * Transforms this iterator in a {@link LongSparseIterable}
     * by applying the specified translator to each element.
     * <p/>
     * Note that the returned iterable is backed by the same sparse collection.
     *
     * @param translator the translator.
     * @return the new iterable.
     */
    public LongSparseIterable toLongs(Translator<E, Long> translator);

    /**
     * This interface defines an action to be applied to the iterable elements.
     *
     * @param <E> the element type.
     */
    public interface Action<E> {

        /**
         * This function will be called for each element returned by the iterable.
         *
         * @param element the element instance.
         * @param count   the number of element iterated until now.
         * @param index   the index of the element in the sparse collection.
         */
        public void onNext(E element, int count, int index);
    }

    /**
     * This interface defines a condition to be applied to the iterable elements.
     *
     * @param <E> the element type.
     */
    public interface Condition<E> {

        /**
         * This function will be called for each element returned by the iterable.
         *
         * @param element the element instance.
         * @param count   the number of element iterated until now.
         * @param index   the index of the element in the sparse collection.
         * @return whether to proceed with the iteration.
         */
        public boolean onNext(E element, int count, int index);
    }
}
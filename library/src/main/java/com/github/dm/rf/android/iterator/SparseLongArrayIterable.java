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

import android.annotation.TargetApi;
import android.os.Parcelable;
import android.util.SparseLongArray;

import com.github.dm.rf.android.entry.IntSparseLongEntry;
import com.github.dm.rf.android.entry.ParcelableIntSparseLongEntry;
import com.github.dm.rf.android.entry.SparseLongArrayEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.SparseLongArrayFilterBuilder;
import com.github.dm.rf.android.translator.IntTranslator;
import com.github.dm.rf.android.translator.LongTranslator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/**
 * This interface extends the {@link SparseIterable} one by adding specific methods handling
 * {@link SparseLongArrayEntry} elements.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 */
@TargetApi(18)
public interface SparseLongArrayIterable extends SparseIterable<SparseLongArrayEntry> {

    /**
     * Appends all the elements returned by this iterable to the specified sparse collection.
     *
     * @param other the sparse collection to append the elements to.
     * @return this iterable.
     */
    public SparseLongArrayIterable appendTo(SparseLongArray other);

    @Override
    public SparseLongArrayFilterBuilder but();

    @Override
    public SparseLongArrayIterable but(Filter<SparseLongArrayEntry> filter);

    @Override
    public SparseLongArrayIterable doWhile(Condition<SparseLongArrayEntry> condition);

    @Override
    public SparseLongArrayIterable forEach(Action<SparseLongArrayEntry> action);

    @Override
    public SparseLongArrayFilterBuilder only();

    @Override
    public SparseLongArrayIterable only(Filter<SparseLongArrayEntry> filter);

    @Override
    public SparseLongArrayIterable remove();

    @Override
    public SparseLongArrayIterable retain();

    @Override
    public SparseLongArrayIterable reverse();

    /**
     * Checks if all the specified keys are present in the iterated elements.
     *
     * @param keys the keys to search for.
     * @return whether all the keys are present.
     */
    public boolean containsAllKeys(int... keys);

    /**
     * Checks if all the keys returned by the specified iterable are present in the iterated
     * elements.
     *
     * @param keys the keys to search for.
     * @return whether all the keys are present.
     */
    public boolean containsAllKeys(Iterable<Integer> keys);

    /**
     * Checks if all the specified value objects are present in the iterated elements.
     *
     * @param values the values to search for.
     * @return whether all the values are present.
     */
    public boolean containsAllValues(long... values);

    /**
     * Checks if all the values returned by the specified iterable are present in the iterated
     * elements.
     *
     * @param values the values to search for.
     * @return whether all the values are present.
     */
    public boolean containsAllValues(Iterable<Long> values);

    /**
     * Checks if at least one of the specified keys is present in the iterated elements.
     *
     * @param keys the keys to search for.
     * @return whether at least one key is present.
     */
    public boolean containsAnyKey(int... keys);

    /**
     * Checks if at least one of the keys returned by the specified iterable is present in the
     * iterated elements.
     *
     * @param keys the keys to search for.
     * @return whether at least one key is present.
     */
    public boolean containsAnyKey(Iterable<Integer> keys);

    /**
     * Checks if at least one of the specified values is present in the iterated elements.
     *
     * @param values the values to search for.
     * @return whether at least one value is present.
     */
    public boolean containsAnyValue(long... values);

    /**
     * Checks if at least one of the values returned by the specified iterable is present in the
     * iterated elements.
     *
     * @param values the values to search for.
     * @return whether at least one value is present.
     */
    public boolean containsAnyValue(Iterable<Long> values);

    /**
     * Checks if the specified key is present in the iterated elements.
     *
     * @param key the key to search for.
     * @return whether the key is present.
     */
    public boolean containsKey(int key);

    /**
     * Checks if the specified value is present in the iterated elements.
     *
     * @param value the value to search for.
     * @return whether the value is present.
     */
    public boolean containsValue(long value);

    /**
     * Fills the specified map with the elements returned by this iterable, in the iteration
     * order.
     *
     * @param map the map to fill.
     * @return this iterable.
     */
    public SparseLongArrayIterable fill(Map<? super Integer, ? super Long> map);

    /**
     * Fills the specified collection with an immutable copy of the elements returned by this
     * iterable, in the iteration order.
     *
     * @param collection the collection to fill.
     * @return this iterable.
     */
    public SparseLongArrayIterable fillImmutable(Collection<? super IntSparseLongEntry> collection);

    /**
     * Fills the specified array with an immutable copy of the elements returned by this
     * iterable, in the iteration order.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array the array to fill.
     * @param <T>   the array element type.
     * @return this iterable.
     */
    public <T> SparseLongArrayIterable fillImmutable(T[] array);

    /**
     * Fills the specified array with an immutable copy of the elements returned by this
     * iterable, in the iteration order, starting from the specified offset inside the array.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array  the array to fill.
     * @param offset the offset from which to start filling the array.
     * @param <T>    the array element type.
     * @return this iterable.
     */
    public <T> SparseLongArrayIterable fillImmutable(T[] array, int offset);

    /**
     * Fills the specified collection with a parcelable copy of the elements returned by this
     * iterable, in the iteration order.
     *
     * @param collection the collection to fill.
     * @return this iterable.
     */
    public SparseLongArrayIterable fillParcelable(
            Collection<? super ParcelableIntSparseLongEntry> collection);

    /**
     * Fills the specified array with a parcelable copy of the elements returned by this
     * iterable, in the iteration order.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array the array to fill.
     * @param <T>   the array element type.
     * @return this iterable.
     */
    public <T extends Parcelable> SparseLongArrayIterable fillParcelable(T[] array);

    /**
     * Fills the specified array with a parcelable copy of the elements returned by this
     * iterable, in the iteration order, starting from the specified offset inside the array.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     * <p/>
     * Note also that, in case the array is not big enough to contain all the elements, an
     * {@link java.lang.IndexOutOfBoundsException} will be thrown.
     *
     * @param array  the array to fill.
     * @param offset the offset from which to start filling the array.
     * @param <T>    the array element type.
     * @return this iterable.
     */
    public <T extends Parcelable> SparseLongArrayIterable fillParcelable(T[] array, int offset);

    /**
     * Finds the index of the first element value, in the iteration order, equals to the specified
     * one.
     *
     * @param value the value to search for.
     * @return the index in the iteration, or -1 if the object is not found.
     */
    public int firstIndexOfValue(long value);

    /**
     * Finds the position of the first element value, in the iteration order, equal to the specified
     * one.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param value the value to search for.
     * @return the position in the iteration, or -1 if the object is not found.
     */
    public int firstPositionOfValue(long value);

    /**
     * Finds the index of the specified key in the iteration order.
     *
     * @param key the key to search for.
     * @return the index in the iteration, or -1 if the object is not found.
     */
    public int indexOfKey(int key);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified sparse collection.
     *
     * @param array the sparse collection to compare.
     * @return whether the specified collection equals this iterable.
     */
    boolean isEqualTo(SparseLongArray array);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified map.
     *
     * @param map the map to compare.
     * @return whether the specified map equals this iterable.
     */
    boolean isEqualTo(Map<?, ?> map);

    /**
     * Returns this iterable elements keys as a
     * {@link IntSparseIterable} object.
     * <p/>
     * Note that, every filter added since the call to this function, will be also applied to the
     * returned iterable.
     *
     * @return the keys iterable.
     */
    public IntSparseIterable keys();

    /**
     * Finds the position of the specified key in the iteration order.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param key the key to search for.
     * @return the position in the iteration, or -1 if the object is not found.
     */
    public int positionOfKey(int key);

    /**
     * Puts all the elements returned by this iterable into the specified sparse collection.
     *
     * @param other the sparse collection to put the elements into.
     * @return this iterable.
     */
    public SparseLongArrayIterable putInto(SparseLongArray other);

    /**
     * Replaces the element values returned by this iterable with the ones obtained through the
     * specified translator.
     * <p/>
     * Note that the replacement will happen in place, and the same key corresponding to each
     * element will be retained.
     *
     * @param translator the value translator.
     * @return this iterable.
     */
    public SparseLongArrayIterable replaceValues(LongTranslator translator);

    /**
     * Creates and returns a new array filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     *
     * @param type the array element class.
     * @param <T>  the array element type.
     * @return the new array.
     */
    public <T> T[] toImmutableArray(Class<T> type);

    /**
     * Creates and returns a new list filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     *
     * @return the new list.
     */
    public ArrayList<IntSparseLongEntry> toImmutableList();

    /**
     * Creates and returns a new map filled with the elements key and values returned by this
     * iterable, in the iteration order.
     *
     * @return the new array.
     */
    public Map<Integer, Long> toMap();

    /**
     * Creates and returns a new array filled with a parcelable copy of the elements returned by
     * this iterable, in the iteration order.
     * <p/>
     * Note that, if the parcelable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     *
     * @param type the array element class.
     * @param <T>  the array element type.
     * @return the new array.
     */
    public <T extends Parcelable> T[] toParcelableArray(Class<T> type);

    /**
     * Creates and returns a new list filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     *
     * @return the new list.
     */
    public ArrayList<ParcelableIntSparseLongEntry> toParcelableList();

    /**
     * Creates and returns a new sorted map filled with the elements key and values returned by
     * this iterable, in the iteration order.
     *
     * @return the new array.
     * @see java.util.SortedMap
     */
    public SortedMap<Integer, Long> toSortedMap();

    /**
     * Returns a new {@link android.util.SparseLongArray} collection filled with the keys and
     * values returned by this iterable.
     *
     * @return the new collection instance.
     */
    public SparseLongArray toSparseArray();

    /**
     * Returns a new iterable whose elements are the same as this ones but translated through the
     * specified translators.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param keyTranslator   the translator used to convert the element key.
     * @param valueTranslator the translator used to convert the element value.
     * @return the new iterable.
     */
    public SparseLongArrayIterable translate(IntTranslator keyTranslator,
            LongTranslator valueTranslator);

    /**
     * Returns a new iterable whose elements are the same as this ones but the keys are translated
     * through the specified translator.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param keyTranslator the translator used to convert the element key.
     * @return the new iterable.
     */
    public SparseLongArrayIterable translateKeys(IntTranslator keyTranslator);

    /**
     * Returns a new iterable whose elements are the same as this ones but the values are translated
     * through the specified translator.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param valueTranslator the translator used to convert the element value.
     * @return the new iterable.
     */
    public SparseLongArrayIterable translateValues(LongTranslator valueTranslator);

    /**
     * Returns this iterable elements values as a
     * {@link LongSparseIterable} object.
     * <p/>
     * Note that, every filter added since the call to this function, will be also applied to the
     * returned iterable.
     *
     * @return the values iterable.
     */
    public LongSparseIterable values();
}
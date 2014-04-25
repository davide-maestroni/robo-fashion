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

import android.os.Parcelable;
import android.util.SparseBooleanArray;

import com.bmd.android.collection.entry.IntSparseBooleanEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseBooleanEntry;
import com.bmd.android.collection.entry.SparseBooleanArrayEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SparseBooleanArrayFilterBuilder;
import com.bmd.android.collection.translator.BooleanTranslator;
import com.bmd.android.collection.translator.IntTranslator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/**
 * This interface extends the {@link com.bmd.android.collection.iterator.SparseIterable} one
 * by adding specific methods handling
 * {@link com.bmd.android.collection.entry.SparseBooleanArrayEntry} elements.
 * <p/>
 * Created by davide on 3/10/14.
 */
public interface SparseBooleanArrayIterable extends SparseIterable<SparseBooleanArrayEntry> {

    /**
     * Appends all the elements returned by this iterable to the specified sparse collection.
     *
     * @param other The sparse collection to append the elements to.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable appendTo(SparseBooleanArray other);

    @Override
    public SparseBooleanArrayFilterBuilder but();

    @Override
    public SparseBooleanArrayIterable but(Filter<SparseBooleanArrayEntry> filter);

    @Override
    public SparseBooleanArrayIterable doWhile(Condition<SparseBooleanArrayEntry> condition);

    @Override
    public SparseBooleanArrayIterable forEach(Action<SparseBooleanArrayEntry> action);

    @Override
    public SparseBooleanArrayFilterBuilder only();

    @Override
    public SparseBooleanArrayIterable only(Filter<SparseBooleanArrayEntry> filter);

    @Override
    public SparseBooleanArrayIterable remove();

    @Override
    public SparseBooleanArrayIterable retain();

    @Override
    public SparseBooleanArrayIterable reverse();

    /**
     * Checks if all the specified keys are present in the iterated elements.
     *
     * @param keys The keys to search for.
     * @return Whether all the keys are present.
     */
    public boolean containsAllKeys(int... keys);

    /**
     * Checks if all the keys returned by the specified iterable are present in the iterated
     * elements.
     *
     * @param keys The keys to search for.
     * @return Whether all the keys are present.
     */
    public boolean containsAllKeys(Iterable<Integer> keys);

    /**
     * Checks if all the specified value objects are present in the iterated elements.
     *
     * @param values The values to search for.
     * @return Whether all the values are present.
     */
    public boolean containsAllValues(boolean... values);

    /**
     * Checks if all the values returned by the specified iterable are present in the iterated
     * elements.
     *
     * @param values The values to search for.
     * @return Whether all the values are present.
     */
    public boolean containsAllValues(Iterable<Boolean> values);

    /**
     * Checks if at least one of the specified keys is present in the iterated elements.
     *
     * @param keys The keys to search for.
     * @return Whether at least one key is present.
     */
    public boolean containsAnyKey(int... keys);

    /**
     * Checks if at least one of the keys returned by the specified iterable is present in the
     * iterated elements.
     *
     * @param keys The keys to search for.
     * @return Whether at least one key is present.
     */
    public boolean containsAnyKey(Iterable<Integer> keys);

    /**
     * Checks if at least one of the specified values is present in the iterated elements.
     *
     * @param values The values to search for.
     * @return Whether at least one value is present.
     */
    public boolean containsAnyValue(boolean... values);

    /**
     * Checks if at least one of the values returned by the specified iterable is present in the
     * iterated elements.
     *
     * @param values The values to search for.
     * @return Whether at least one value is present.
     */
    public boolean containsAnyValue(Iterable<Boolean> values);

    /**
     * Checks if the specified key is present in the iterated elements.
     *
     * @param key The key to search for.
     * @return Whether the key is present.
     */
    public boolean containsKey(int key);

    /**
     * Checks if the specified value is present in the iterated elements.
     *
     * @param value The value to search for.
     * @return Whether the value is present.
     */
    public boolean containsValue(boolean value);

    /**
     * Fills the specified map with the elements returned by this iterable, in the iteration
     * order.
     *
     * @param map The map to fill.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable fill(Map<? super Integer, ? super Boolean> map);

    /**
     * Fills the specified collection with an immutable copy of the elements returned by this
     * iterable, in the iteration order.
     *
     * @param collection The collection to fill.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable fillImmutable(
            Collection<? super IntSparseBooleanEntry> collection);

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
     * @param array The array to fill.
     * @param <T>   The array element type.
     * @return This iterable.
     */
    public <T> SparseBooleanArrayIterable fillImmutable(T[] array);

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
     * @param array  The array to fill.
     * @param offset The offset from which to start filling the array.
     * @param <T>    The array element type.
     * @return This iterable.
     */
    public <T> SparseBooleanArrayIterable fillImmutable(T[] array, int offset);

    /**
     * Fills the specified collection with a parcelable copy of the elements returned by this
     * iterable, in the iteration order.
     *
     * @param collection The collection to fill.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable fillParcelable(
            Collection<? super ParcelableIntSparseBooleanEntry> collection);

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
     * @param array The array to fill.
     * @param <T>   The array element type.
     * @return This iterable.
     */
    public <T extends Parcelable> SparseBooleanArrayIterable fillParcelable(T[] array);

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
     * @param array  The array to fill.
     * @param offset The offset from which to start filling the array.
     * @param <T>    The array element type.
     * @return This iterable.
     */
    public <T extends Parcelable> SparseBooleanArrayIterable fillParcelable(T[] array, int offset);

    /**
     * Finds the index of the first element value, in the iteration order, equals to the specified
     * one.
     *
     * @param value The value to search for.
     * @return The index in the iteration, or -1 if the object is not found.
     */
    public int firstIndexOfValue(boolean value);

    /**
     * Finds the position of the first element value, in the iteration order, equal to the specified
     * one.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param value The value to search for.
     * @return The position in the iteration, or -1 if the object is not found.
     */
    public int firstPositionOfValue(boolean value);

    /**
     * Finds the index of the specified key in the iteration order.
     *
     * @param key The key to search for.
     * @return The index in the iteration, or -1 if the object is not found.
     */
    public int indexOfKey(int key);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified sparse collection.
     *
     * @param array The sparse collection to compare.
     * @return Whether the specified collection equals this iterable.
     */
    boolean isEqualTo(SparseBooleanArray array);

    /**
     * Checks if all and only the elements returned by the this iterable are contained in the
     * specified map.
     *
     * @param map The map to compare.
     * @return Whether the specified map equals this iterable.
     */
    boolean isEqualTo(Map<?, ?> map);

    /**
     * Returns this iterable elements keys as a
     * {@link com.bmd.android.collection.iterator.IntSparseIterable} object.
     * <p/>
     * Note that, every filter added since the call to this function, will be also applied to the
     * returned iterable.
     *
     * @return The keys iterable.
     */
    public IntSparseIterable keys();

    /**
     * Finds the position of the specified key in the iteration order.
     * <p/>
     * Note that the returned value is relative to the iterator cycles so, in case filters has been
     * applied to this iterable, the returned number might not match with the index in the sparse
     * collection.
     *
     * @param key The key to search for.
     * @return The position in the iteration, or -1 if the object is not found.
     */
    public int positionOfKey(int key);

    /**
     * Puts all the elements returned by this iterable into the specified sparse collection.
     *
     * @param other The sparse collection to put the elements into.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable putInto(SparseBooleanArray other);

    /**
     * Replaces the element values returned by this iterable with the ones obtained through the
     * specified translator.
     * <p/>
     * Note that the replacement will happen in place, and the same key corresponding to each
     * element will be retained.
     *
     * @param translator The value translator.
     * @return This iterable.
     */
    public SparseBooleanArrayIterable replaceValues(BooleanTranslator translator);

    /**
     * Creates and returns a new array filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     * <p/>
     * Note that, if the immutable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     *
     * @param type The array element class.
     * @param <T>  The array element type.
     * @return The new array.
     */
    public <T> T[] toImmutableArray(Class<T> type);

    /**
     * Creates and returns a new list filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     *
     * @return The new list.
     */
    public ArrayList<IntSparseBooleanEntry> toImmutableList();

    /**
     * Creates and returns a new map filled with the elements key and values returned by this
     * iterable, in the iteration order.
     *
     * @return The new array.
     */
    public Map<Integer, Boolean> toMap();

    /**
     * Creates and returns a new array filled with a parcelable copy of the elements returned by
     * this iterable, in the iteration order.
     * <p/>
     * Note that, if the parcelable copy of the elements returned by the iterable cannot be cast to
     * the array elements type, a {@link java.lang.ClassCastException} will be thrown.
     *
     * @param type The array element class.
     * @param <T>  The array element type.
     * @return The new array.
     */
    public <T extends Parcelable> T[] toParcelableArray(Class<T> type);

    /**
     * Creates and returns a new list filled with an immutable copy of the elements returned by
     * this iterable, in the iteration order.
     *
     * @return The new list.
     */
    public ArrayList<ParcelableIntSparseBooleanEntry> toParcelableList();

    /**
     * Creates and returns a new sorted map filled with the elements key and values returned by
     * this iterable, in the iteration order.
     *
     * @return The new array.
     * @see java.util.SortedMap
     */
    public SortedMap<Integer, Boolean> toSortedMap();

    /**
     * Returns a new {@link android.util.SparseBooleanArray} collection filled with the keys and
     * values returned by this iterable.
     *
     * @return The new collection instance.
     */
    public SparseBooleanArray toSparseArray();

    /**
     * Returns a new iterable whose elements are the same as this ones but translated through the
     * specified translators.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param keyTranslator   The translator used to convert the element key.
     * @param valueTranslator The translator used to convert the element value.
     * @return The new iterable.
     */
    public SparseBooleanArrayIterable translate(IntTranslator keyTranslator,
            BooleanTranslator valueTranslator);

    /**
     * Returns a new iterable whose elements are the same as this ones but the keys are translated
     * through the specified translator.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param keyTranslator The translator used to convert the element key.
     * @return The new iterable.
     */
    public SparseBooleanArrayIterable translateKeys(IntTranslator keyTranslator);

    /**
     * Returns a new iterable whose elements are the same as this ones but the values are translated
     * through the specified translator.
     * <p/>
     * Note that, in case the passed translator is not bidirectional, every attempt to modify the
     * returned iterable elements will cause an exception to be thrown.
     * Note also that all the filters and the iteration order are retained in the translation.
     *
     * @param valueTranslator The translator used to convert the element value.
     * @return The new iterable.
     */
    public SparseBooleanArrayIterable translateValues(BooleanTranslator valueTranslator);

    /**
     * Returns this iterable elements values as a
     * {@link com.bmd.android.collection.iterator.BooleanSparseIterable} object.
     * <p/>
     * Note that, every filter added since the call to this function, will be also applied to the
     * returned iterable.
     *
     * @return The values iterable.
     */
    public BooleanSparseIterable values();
}
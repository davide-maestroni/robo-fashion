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
package com.bmd.android.collection.internal;

import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import com.bmd.android.collection.iterator.LongSparseArrayIterable;
import com.bmd.android.collection.iterator.SimpleArrayMapIterable;
import com.bmd.android.collection.iterator.SparseArrayCompatIterable;
import com.bmd.android.collection.iterator.SparseArrayIterable;
import com.bmd.android.collection.iterator.SparseBooleanArrayIterable;
import com.bmd.android.collection.iterator.SparseIntArrayIterable;
import com.bmd.android.collection.iterator.SparseLongArrayIterable;
import com.bmd.android.collection.iterator.SupportLongSparseArrayIterable;

/**
 * Utility class implementing a factory of
 * {@link com.bmd.android.collection.iterator.SparseIterable} instances wrapping specific sparse
 * collections.
 * <p/>
 * This class represents the only entry point to the internal package.
 * <p/>
 * Created by davide on 3/10/14.
 */
public class SparseIterableFactory {

    private SparseIterableFactory() {

    }

    /**
     * Wraps the specified {@link android.support.v4.util.SimpleArrayMap} into an iterable.
     *
     * @param arrayMap The array map to wrap.
     * @param <K>      The element key type.
     * @param <V>      The element value type.
     * @return The iterable instance.
     */
    public static <K, V> SimpleArrayMapIterable<K, V> create(final SimpleArrayMap<K, V> arrayMap) {

        return new SimpleArrayMapIterableImpl<K, V>(arrayMap);
    }

    /**
     * Wraps the specified {@link android.util.SparseLongArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseLongArrayIterable create(final SparseLongArray sparseArray) {

        return new SparseLongArrayIterableImpl(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> LongSparseArrayIterable<V> create(
            final android.util.LongSparseArray<V> sparseArray) {

        return new LongSparseArrayIterableImpl<V>(sparseArray);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SupportLongSparseArrayIterable<V> create(
            final LongSparseArray<V> sparseArray) {

        return new SupportLongSparseArrayIterableImpl<V>(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SparseArrayIterable<V> create(final SparseArray<V> sparseArray) {

        return new SparseArrayIterableImpl<V>(sparseArray);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.SparseArrayCompat} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SparseArrayCompatIterable<V> create(final SparseArrayCompat<V> sparseArray) {

        return new SparseArrayCompatIterableImpl<V>(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseBooleanArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseBooleanArrayIterable create(final SparseBooleanArray sparseArray) {

        return new SparseBooleanArrayIterableImpl(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseIntArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseIntArrayIterable create(final SparseIntArray sparseArray) {

        return new SparseIntArrayIterableImpl(sparseArray);
    }
}
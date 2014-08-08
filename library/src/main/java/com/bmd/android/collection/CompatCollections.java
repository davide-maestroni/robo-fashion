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
package com.bmd.android.collection;

import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import com.bmd.android.collection.internal.CompatIterableFactory;
import com.bmd.android.collection.iterator.SimpleArrayMapIterable;
import com.bmd.android.collection.iterator.SparseArrayCompatIterable;
import com.bmd.android.collection.iterator.SparseArrayIterable;
import com.bmd.android.collection.iterator.SparseBooleanArrayIterable;
import com.bmd.android.collection.iterator.SparseIntArrayIterable;
import com.bmd.android.collection.iterator.SupportLongSparseArrayIterable;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * Created by davide on 3/9/14.
 */
public class CompatCollections {

    /**
     * Avoid direct instantiation.
     */
    CompatCollections() {

    }

    /**
     * Wraps the specified {@link android.support.v4.util.SimpleArrayMap} into an iterable.
     *
     * @param arrayMap the array map to wrap.
     * @param <K>      the element key type.
     * @param <V>      the element value type.
     * @return the iterable instance.
     */
    public static <K, V> SimpleArrayMapIterable<K, V> iterate(final SimpleArrayMap<K, V> arrayMap) {

        return CompatIterableFactory.create(arrayMap);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @param <V>         the element value type.
     * @return the iterable instance.
     */
    public static <V> SupportLongSparseArrayIterable<V> iterate(
            final LongSparseArray<V> sparseArray) {

        return CompatIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @param <V>         the element value type.
     * @return the iterable instance.
     */
    public static <V> SparseArrayIterable<V> iterate(final SparseArray<V> sparseArray) {

        return CompatIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.SparseArrayCompat} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @param <V>         the element value type.
     * @return the iterable instance.
     */
    public static <V> SparseArrayCompatIterable<V> iterate(final SparseArrayCompat<V> sparseArray) {

        return CompatIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseIntArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @return the iterable instance.
     */
    public static SparseIntArrayIterable iterate(final SparseIntArray sparseArray) {

        return CompatIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseBooleanArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @return the iterable instance.
     */
    public static SparseBooleanArrayIterable iterate(final SparseBooleanArray sparseArray) {

        return CompatIterableFactory.create(sparseArray);
    }
}
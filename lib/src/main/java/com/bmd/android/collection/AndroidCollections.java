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
import android.util.SparseLongArray;

import com.bmd.android.collection.internal.SparseIterableFactory;
import com.bmd.android.collection.iterator.LongSparseArrayIterable;
import com.bmd.android.collection.iterator.SimpleArrayMapIterable;
import com.bmd.android.collection.iterator.SparseArrayCompatIterable;
import com.bmd.android.collection.iterator.SparseArrayIterable;
import com.bmd.android.collection.iterator.SparseBooleanArrayIterable;
import com.bmd.android.collection.iterator.SparseIntArrayIterable;
import com.bmd.android.collection.iterator.SparseLongArrayIterable;
import com.bmd.android.collection.iterator.SupportLongSparseArrayIterable;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * The wrapping iterable gives the ability to loop through the sparse collection elements as
 * value/key entries.<br/>
 * Additionally, the iteration can be filtered by concatenating inclusion
 * ({@link com.bmd.android.collection.iterator.SparseIterable#only()}) and
 * exclusion ({@link com.bmd.android.collection.iterator.SparseIterable#but()}) filters, and run in
 * reverse order.<br/>
 * Besides, it allows to check for equality or presence of specific elements within the iterated
 * ones (in fact, the implementation of sparse collection does not employ <code>equals()</code>
 * when comparing values).<br/>
 * Finally, the iterated elements can be retained
 * ({@link com.bmd.android.collection.iterator.SparseIterable#retain()}) or removed
 * ({@link com.bmd.android.collection.iterator.SparseIterable#remove()}) from the wrapped
 * collection.
 * <p/>
 * The sparse collection can be iterated both by using the classic Java syntax:
 * <pre>
 *
 *     for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)) {
 *
 *         // ... do your stuff ...
 *     }
 *
 * </pre>
 * Or through a more fluent syntax, like:
 * <pre>
 *
 *     AndroidCollections.iterate(array)
 *                       .only().from(4)
 *                       .forEach((element, index) -> {
 *                           // ... do your stuff ...
 *                       });
 *
 * </pre>
 * Or even by mixing the two:
 * <pre>
 *
 *     for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)
 *                                                                  .only().first(3)) {
 *
 *         // ... do your stuff ...
 *     }
 *
 * </pre>
 * <p/>
 * As an example to clearly understand the filter concatenation behavior, the output of the
 * following code:
 * <pre>
 *
 *     final SparseArrayCompat<String> array = new SparseArrayCompat<String>();
 *
 *     for (int i = 0; i < 5; i++) {
 *
 *         array.append(i, String.valueOf(i));
 *     }
 *
 *     AndroidCollections.iterate(array)
 *                       .only().first(3)
 *                       .only().last(2)
 *                       .reverse().forEach((element, index) -> {
 *                           System.out.println(element.getValue());
 *                       });
 *
 * </pre>
 * will be:
 * <pre>
 *
 *     2
 *     1
 *
 * </pre>
 * <p/>
 * Note that the iterable implementation is not thread safe (unless differently specified) and
 * not fail-fast, that is, if the wrapped collection is changed during the iteration no exception
 * will be thrown, and further call to the iterator or to the entries methods may lead to
 * unexpected results.
 * <p/>
 * The entries returned by the iterator can be safely accessed only inside the iteration loop,
 * since they have direct access to the wrapped sparse collection.
 * <br/>
 * In case the caller needed to retain an entry instance outside the loop, it must create an
 * immutable or parcelable copy and retain that instead:
 * <pre>
 *
 *     final SparseArrayCompat<String> array = new SparseArrayCompat<String>();
 *
 *     for (int i = 0; i < 5; i++) {
 *
 *         array.append(i, String.valueOf(i));
 *     }
 *
 *     IntSparseObjectEntry<String> myEntry;
 *
 *     for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)) {
 *
 *         if ("3".equals(entry.getValue())) {
 *
 *             myEntry = entry.toImmutable();
 *
 *             break;
 *         }
 *     }
 *
 * </pre>
 * <p/>
 * Created by davide on 3/9/14.
 */
public class AndroidCollections {

    /**
     * Avoid direct instantiation.
     */
    private AndroidCollections() {

    }

    /**
     * Wraps the specified {@link android.support.v4.util.SimpleArrayMap} into an iterable.
     *
     * @param arrayMap The array map to wrap.
     * @param <K>      The element key type.
     * @param <V>      The element value type.
     * @return The iterable instance.
     */
    public static <K, V> SimpleArrayMapIterable<K, V> iterate(final SimpleArrayMap<K, V> arrayMap) {

        return SparseIterableFactory.create(arrayMap);
    }

    /**
     * Wraps the specified {@link android.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> LongSparseArrayIterable<V> iterate(
            final android.util.LongSparseArray<V> sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SupportLongSparseArrayIterable<V> iterate(
            final LongSparseArray<V> sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SparseArrayIterable<V> iterate(final SparseArray<V> sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.support.v4.util.SparseArrayCompat} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @param <V>         The element value type.
     * @return The iterable instance.
     */
    public static <V> SparseArrayCompatIterable<V> iterate(final SparseArrayCompat<V> sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseIntArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseIntArrayIterable iterate(final SparseIntArray sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseBooleanArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseBooleanArrayIterable iterate(final SparseBooleanArray sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }

    /**
     * Wraps the specified {@link android.util.SparseLongArray} into an iterable.
     *
     * @param sparseArray The sparse array to wrap.
     * @return The iterable instance.
     */
    public static SparseLongArrayIterable iterate(final SparseLongArray sparseArray) {

        return SparseIterableFactory.create(sparseArray);
    }
}
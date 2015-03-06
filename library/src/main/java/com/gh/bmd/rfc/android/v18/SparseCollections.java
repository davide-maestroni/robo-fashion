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
package com.gh.bmd.rfc.android.v18;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.util.SparseLongArray;

import com.gh.bmd.rfc.android.internal.AndroidIterableFactory;
import com.gh.bmd.rfc.android.iterator.SparseLongArrayIterable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * The wrapping iterable gives the ability to loop through the sparse collection elements as
 * value/key entries.<br/>
 * Additionally, the iteration can be filtered by concatenating inclusion
 * ({@link com.gh.bmd.rfc.android.iterator.SparseIterable#only()}) and
 * exclusion ({@link com.gh.bmd.rfc.android.iterator.SparseIterable#but()}) filters, and run in
 * reverse order.<br/>
 * Besides, it allows to check for equality or presence of specific elements within the iterated
 * ones (in fact, the implementation of sparse collection does not employ <code>equals()</code>
 * when comparing values).<br/>
 * Finally, the iterated elements can be retained
 * ({@link com.gh.bmd.rfc.android.iterator.SparseIterable#retain()}) or removed
 * ({@link com.gh.bmd.rfc.android.iterator.SparseIterable#remove()}) from the wrapped
 * collection.
 * <p/>
 * The sparse collection can be iterated both by using the classic Java syntax:
 * <pre>
 *  <code>
 *     for (final SparseArrayEntry<String> entry: SparseCollections.iterate(array)) {
 *
 *         // ... do your stuff ...
 *     }
 *  </code>
 * </pre>
 * Or through a more fluent syntax, like:
 * <pre>
 *  <code>
 *     SparseCollections.iterate(array)
 *                      .only().from(4)
 *                      .forEach((element, index) -> {
 *                          // ... do your stuff ...
 *                      });
 *  </code>
 * </pre>
 * Or even by mixing the two:
 * <pre>
 *  <code>
 *     for (final SparseArrayEntry<String> entry: SparseCollections.iterate(array)
 *                                                                 .only().first(3)) {
 *
 *         // ... do your stuff ...
 *     }
 *  </code>
 * </pre>
 * <p/>
 * As an example to clearly understand the filter concatenation behavior, the output of the
 * following code:
 * <pre>
 *  <code>
 *     final SparseArrayCompat<String> array = new SparseArrayCompat<String>();
 *
 *     for (int i = 0; i < 5; i++) {
 *
 *         array.append(i, String.valueOf(i));
 *     }
 *
 *     SparseCollections.iterate(array)
 *                      .only().first(3)
 *                      .only().last(2)
 *                      .reverse().forEach((element, index) -> {
 *                          System.out.println(element.getValue());
 *                      });
 *  </code>
 * </pre>
 * will be:
 * <pre>
 *  <code>
 *     2
 *     1
 *  </code>
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
 *  <code>
 *     final SparseArrayCompat<String> array = new SparseArrayCompat<String>();
 *
 *     for (int i = 0; i < 5; i++) {
 *
 *         array.append(i, String.valueOf(i));
 *     }
 *
 *     IntSparseObjectEntry<String> myEntry;
 *
 *     for (final SparseArrayEntry<String> entry: SparseCollections.iterate(array)) {
 *
 *         if ("3".equals(entry.getValue())) {
 *
 *             myEntry = entry.toImmutable();
 *
 *             break;
 *         }
 *     }
 *  </code>
 * </pre>
 * <p/>
 * For JELLY BEAN retro-compatible implementation refer to
 * {@link com.gh.bmd.rfc.android.v16.SparseCollections}.
 * <p/>
 * Created by davide on 5/1/14.
 *
 * @see com.gh.bmd.rfc.android.v4.SparseCollections
 */
@SuppressFBWarnings(value = "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS",
                    justification = "utility class extending functionalities of another utility "
                            + "class")
@TargetApi(VERSION_CODES.JELLY_BEAN_MR2)
public class SparseCollections extends com.gh.bmd.rfc.android.v16.SparseCollections {

    /**
     * Avoid direct instantiation.
     */
    protected SparseCollections() {

    }

    /**
     * Wraps the specified {@link android.util.SparseLongArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @return the iterable instance.
     */
    public static SparseLongArrayIterable iterate(final SparseLongArray sparseArray) {

        return AndroidIterableFactory.create(sparseArray);
    }
}
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
package com.github.dm.rf.android.v16;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;

import com.github.dm.rf.android.internal.JellyBeanIterableFactory;
import com.github.dm.rf.android.iterator.LongSparseArrayIterable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * For FROYO retro-compatible implementation refer to
 * {@link com.github.dm.rf.android.v4.SparseCollections}.
 * <p/>
 * Created by davide-maestroni on 5/1/14.
 */
@SuppressFBWarnings(value = "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS",
                    justification = "utility class extending functionalities of another utility "
                            + "class")
@TargetApi(VERSION_CODES.JELLY_BEAN)
public class SparseCollections extends com.github.dm.rf.android.v4.SparseCollections {

    /**
     * Avoid direct instantiation.
     */
    protected SparseCollections() {

    }

    /**
     * Wraps the specified {@link android.util.LongSparseArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @param <V>         the element value type.
     * @return the iterable instance.
     */
    public static <V> LongSparseArrayIterable<V> iterate(
            final android.util.LongSparseArray<V> sparseArray) {

        return JellyBeanIterableFactory.create(sparseArray);
    }
}
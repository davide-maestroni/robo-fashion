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

import android.annotation.TargetApi;

import com.bmd.android.collection.internal.JellyBeanIterableFactory;
import com.bmd.android.collection.iterator.LongSparseArrayIterable;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * For Froyo retro-compatible implementation refer to
 * {@link com.bmd.android.collection.CompatCollections}.
 * <p/>
 * Created by davide on 5/1/14.
 */
@TargetApi(16)
public class JellyBeanCollections extends CompatCollections {

    /**
     * Avoid direct instantiation.
     */
    JellyBeanCollections() {

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

        return JellyBeanIterableFactory.create(sparseArray);
    }
}
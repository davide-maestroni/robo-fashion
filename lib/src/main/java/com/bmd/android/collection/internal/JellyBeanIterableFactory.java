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

import android.annotation.TargetApi;

import com.bmd.android.collection.iterator.LongSparseArrayIterable;

/**
 * Utility class implementing a factory of
 * {@link com.bmd.android.collection.iterator.SparseIterable} instances wrapping specific sparse
 * collections.
 * <p/>
 * This class represents the entry point to the internal package.
 * <p/>
 * For Froyo retro-compatible implementation refer to
 * {@link com.bmd.android.collection.internal.CompatIterableFactory}.
 * <p/>
 * Created by davide on 5/1/14.
 */
@TargetApi(16)
public class JellyBeanIterableFactory extends CompatIterableFactory {

    /**
     * Avoid direct instantiation.
     */
    JellyBeanIterableFactory() {

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
}
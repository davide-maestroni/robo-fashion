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
package com.github.dm.rf.android.internal;

import android.annotation.TargetApi;

import com.github.dm.rf.android.iterator.LongSparseArrayIterable;
import com.github.dm.rf.android.iterator.SparseIterable;

/**
 * Utility class implementing a factory of
 * {@link SparseIterable} instances wrapping specific sparse
 * collections.
 * <p/>
 * This class represents the entry point to the internal package.
 * <p/>
 * For Froyo retro-compatible implementation refer to {@link CompatIterableFactory}.
 * <p/>
 * Created by davide-maestroni on 5/1/14.
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
     * @param sparseArray the sparse array to wrap.
     * @param <V>         the element value type.
     * @return the iterable instance.
     */
    public static <V> LongSparseArrayIterable<V> create(
            final android.util.LongSparseArray<V> sparseArray) {

        return new LongSparseArrayIterableImpl<V>(sparseArray);
    }
}
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
package com.gh.bmd.rfc.android.internal;

import android.annotation.TargetApi;
import android.util.SparseLongArray;

import com.gh.bmd.rfc.android.iterator.SparseLongArrayIterable;

/**
 * This utility class creates objects wrapping an Android sparse collection, such as
 * {@link android.util.SparseArray} and {@link android.support.v4.util.SparseArrayCompat}, to give
 * it the base functionalities of an {@link java.lang.Iterable} object.
 * <p/>
 * This class represents the entry point to the internal package.
 * <p/>
 * For JellyBean retro-compatible implementation refer to
 * {@link com.gh.bmd.rfc.android.v16.SparseCollections}.
 * <p/>
 * Created by davide on 5/1/14.
 */
@TargetApi(18)
public class AndroidIterableFactory extends JellyBeanIterableFactory {

    /**
     * Avoid direct instantiation.
     */
    AndroidIterableFactory() {

    }

    /**
     * Wraps the specified {@link android.util.SparseLongArray} into an iterable.
     *
     * @param sparseArray the sparse array to wrap.
     * @return the iterable instance.
     */
    public static SparseLongArrayIterable create(final SparseLongArray sparseArray) {

        return new SparseLongArrayIterableImpl(sparseArray);
    }
}
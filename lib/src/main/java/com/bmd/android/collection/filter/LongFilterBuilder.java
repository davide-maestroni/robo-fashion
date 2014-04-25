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
package com.bmd.android.collection.filter;

import com.bmd.android.collection.iterator.LongSparseIterable;

/**
 * This interface extends the {@link com.bmd.android.collection.filter.FilterBuilder} one by
 * providing specific methods handling long elements.
 * <p/>
 * Created by davide on 3/16/14.
 */
public interface LongFilterBuilder extends FilterBuilder<LongSparseIterable, Long> {

    /**
     * Creates a filter matching the specified long value.
     *
     * @param element The long value to match.
     * @return The filtered iterable.
     */
    public LongSparseIterable element(long element);

    /**
     * Creates a filter matching the specified long values.
     *
     * @param elements The long values to match.
     * @return The filtered iterable.
     */
    public LongSparseIterable elements(long... elements);
}
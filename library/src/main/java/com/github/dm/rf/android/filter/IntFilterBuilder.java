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
package com.github.dm.rf.android.filter;

import com.github.dm.rf.android.iterator.IntSparseIterable;

/**
 * This interface extends the {@link FilterBuilder} one by providing specific methods handling int
 * elements.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 */
public interface IntFilterBuilder extends FilterBuilder<IntSparseIterable, Integer> {

    /**
     * Creates a filter matching the specified int value.
     *
     * @param element the int value to match.
     * @return the filtered iterable.
     */
    public IntSparseIterable element(int element);

    /**
     * Creates a filter matching the specified int values.
     *
     * @param elements the int values to match.
     * @return the filtered iterable.
     */
    public IntSparseIterable elements(int... elements);
}
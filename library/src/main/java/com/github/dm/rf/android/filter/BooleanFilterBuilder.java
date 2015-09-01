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

import com.github.dm.rf.android.iterator.BooleanSparseIterable;

/**
 * This interface defines a {@link FilterBuilder} associated with
 * {@link BooleanSparseIterable} objects.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 */
public interface BooleanFilterBuilder extends FilterBuilder<BooleanSparseIterable, Boolean> {

    /**
     * Creates a filter matching the specified boolean value and returns the filtered iterable.
     *
     * @param element the boolean value to match with the iterable element.
     * @return the filtered iterable.
     */
    public BooleanSparseIterable element(boolean element);
}
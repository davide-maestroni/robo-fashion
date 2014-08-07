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

import java.util.Iterator;

/**
 * This interface defines an advanced element filter.
 * <p/>
 * An advanced filter may retain an internal state which must be reset each time the {#initialize}
 * method is call.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> the element type.
 */
public interface AdvancedFilter<E> extends Filter<E> {

    /**
     * This method is called each time the filter needs to be initialized with the iterator
     * resulting from the application of previous filter to the sparse collection iterable.
     * <p/>
     * The specific implementation may use the passed iterator to analyze the elements before
     * the application of the filter itself.
     *
     * @param iterator the filtered iterator.
     */
    public void initialize(FilterIterator<E> iterator);

    /**
     * This interface defines an iterator resulting from the application of filters to the sparse
     * array iterable.
     *
     * @param <E> the element type.
     */
    public interface FilterIterator<E> extends Iterator<E> {

        /**
         * Returns the original index of the current element in the sparse collection.
         *
         * @return the index.
         */
        public int originalIndex();
    }
}
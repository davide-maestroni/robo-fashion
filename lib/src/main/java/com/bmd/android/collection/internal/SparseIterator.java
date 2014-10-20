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

import com.bmd.android.collection.filter.AdvancedFilter.FilterIterator;

/**
 * Internal interface adding package methods.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @param <E> the element type.
 */
interface SparseIterator<E> extends FilterIterator<E> {

    /**
     * Resets this iterator so that it goes back to the first element.
     */
    void reset();
}
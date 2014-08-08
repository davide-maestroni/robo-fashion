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

import com.bmd.android.collection.utils.BinarySearch;

import java.util.Arrays;

/**
 * Filter matching the elements whose index is contained in the specified array.
 * <p/>
 * Created by davide on 3/12/14.
 *
 * @param <E> the filtered element type.
 */
class IndexesFilter<E> implements Filter<E> {

    private final int[] mIndexes;

    public IndexesFilter(final int... indexes) {

        if (indexes == null) {

            throw new IllegalArgumentException();
        }

        mIndexes = indexes.clone();
        Arrays.sort(mIndexes);
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return BinarySearch.contains(mIndexes, mIndexes.length, index);
    }
}
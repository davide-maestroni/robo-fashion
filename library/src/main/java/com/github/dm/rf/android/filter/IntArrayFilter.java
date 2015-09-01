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

import com.github.dm.rf.android.translator.ToIntTranslator;
import com.github.dm.rf.android.utils.BinarySearch;

import java.util.Arrays;

/**
 * Filter matching all the int values contained in the specified array.
 * <p/>
 * Created by davide-maestroni on 3/14/14.
 *
 * @param <E> the filtered element type.
 */
class IntArrayFilter<E> implements Filter<E> {

    private final ToIntTranslator<E> mTranslator;

    private final int[] mValues;

    public IntArrayFilter(final ToIntTranslator<E> translator, final int[] values) {

        if (translator == null) {

            throw new IllegalArgumentException();
        }

        mTranslator = translator;
        mValues = values.clone();
        Arrays.sort(mValues);
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        final int value = mTranslator.translate(element);

        return BinarySearch.contains(mValues, mValues.length, value);
    }
}
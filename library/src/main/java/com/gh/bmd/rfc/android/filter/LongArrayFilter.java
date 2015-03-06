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
package com.gh.bmd.rfc.android.filter;

import com.gh.bmd.rfc.android.translator.ToLongTranslator;

/**
 * Filter matching the long values in the specified array.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> the filtered element type.
 */
class LongArrayFilter<E> implements Filter<E> {

    private final ToLongTranslator<E> mTranslator;

    private final long[] mValues;

    public LongArrayFilter(final ToLongTranslator<E> translator, final long[] values) {

        if (translator == null) {

            throw new IllegalArgumentException();
        }

        mTranslator = translator;
        mValues = values.clone();
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        final long value = mTranslator.translate(element);

        for (final long aValue : mValues) {

            if (aValue == value) {

                return true;
            }
        }

        return false;
    }
}
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

import com.bmd.android.collection.translator.ToLongTranslator;

/**
 * Filter matching the specified long value.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> The filtered element type.
 */
class LongValueFilter<E> implements Filter<E> {

    private final ToLongTranslator<E> mTranslator;

    private final long mValue;

    public LongValueFilter(final ToLongTranslator<E> translator, final long value) {

        if (translator == null) {

            throw new IllegalArgumentException();
        }

        mTranslator = translator;
        mValue = value;
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return (mTranslator.translate(element) == mValue);
    }
}
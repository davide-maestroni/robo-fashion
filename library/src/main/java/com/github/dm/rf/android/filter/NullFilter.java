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

import com.github.dm.rf.android.translator.Translator;

/**
 * Filter matching null values.
 * <p/>
 * Created by davide-maestroni on 3/14/14.
 *
 * @param <E> the filtered element type.
 */
class NullFilter<E> implements Filter<E> {

    private final Translator<E, ?> mTranslator;

    public NullFilter(final Translator<E, ?> translator) {

        if (translator == null) {

            throw new IllegalArgumentException();
        }

        mTranslator = translator;
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return (mTranslator.translate(element) == null);
    }
}
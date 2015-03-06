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

import com.gh.bmd.rfc.android.translator.Translator;

import java.util.ArrayList;

/**
 * Filter matching the values returned by the specified iterable.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> the filtered element type.
 */
class IterableFilter<E> implements Filter<E> {

    private final ArrayList<Object> mCollection;

    private final Translator<E, ?> mTranslator;

    public IterableFilter(final Translator<E, ?> translator, final Iterable<?> iterable) {

        if (translator == null) {

            throw new IllegalArgumentException();
        }

        final ArrayList<Object> objectList = new ArrayList<Object>();

        for (final Object object : iterable) {

            objectList.add(object);
        }

        mTranslator = translator;
        mCollection = objectList;
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return mCollection.contains(mTranslator.translate(element));
    }
}
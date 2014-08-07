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

import com.bmd.android.collection.translator.Translator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Filter checking if the value extracted from an element through a
 * {@link com.bmd.android.collection.translator.ToBooleanTranslator} instance is contained in the
 * specified collection.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> The element type.
 */
class CollectionFilter<E> implements Filter<E> {

    private final ArrayList<Object> mCollection;

    private final Translator<E, ?> mTranslator;

    public CollectionFilter(final Translator<E, ?> translator, final Collection<?> collection) {

        if ((translator == null) || (collection == null)) {

            throw new IllegalArgumentException();
        }

        mTranslator = translator;
        mCollection = new ArrayList<Object>(collection);
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return mCollection.contains(mTranslator.translate(element));
    }
}
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

import com.bmd.android.collection.filter.Filters;
import com.bmd.android.collection.filter.IntFilterBuilder;
import com.bmd.android.collection.iterator.IntSparseIterable;
import com.bmd.android.collection.translator.ToIntTranslator;

/**
 * Implementation of a {@link IntFilterBuilder}.
 * <p/>
 * Created by davide on 3/19/14.
 */
class IntFilterBuilderImpl extends FilterBuilderImpl<IntSparseIterable, Integer>
        implements IntFilterBuilder {

    private static volatile ToIntTranslator<Integer> sTranslator;

    public IntFilterBuilderImpl(final IntSparseIterable iterable, final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    private static void ensureTranslator() {

        if (sTranslator == null) {

            sTranslator = new ToIntTranslator<Integer>() {

                @Override
                public int translate(final Integer element) {

                    return element;
                }
            };
        }
    }

    @Override
    public IntSparseIterable element(final int element) {

        ensureTranslator();

        return super.matching(Filters.matching(sTranslator, element));
    }

    @Override
    public IntSparseIterable elements(final int... elements) {

        ensureTranslator();

        return super.matching(Filters.containedIn(sTranslator, elements));
    }
}
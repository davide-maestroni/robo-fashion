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
package com.github.dm.rf.android.internal;

import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.LongFilterBuilder;
import com.github.dm.rf.android.iterator.LongSparseIterable;
import com.github.dm.rf.android.translator.ToLongTranslator;

/**
 * Implementation of a {@link LongFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/19/14.
 */
class LongFilterBuilderImpl extends FilterBuilderImpl<LongSparseIterable, Long>
        implements LongFilterBuilder {

    private static volatile ToLongTranslator<Long> sTranslator;

    public LongFilterBuilderImpl(final LongSparseIterable iterable, final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    private static void ensureTranslator() {

        if (sTranslator == null) {

            sTranslator = new ToLongTranslator<Long>() {

                @Override
                public long translate(final Long element) {

                    return element;
                }
            };
        }
    }

    @Override
    public LongSparseIterable element(final long element) {

        ensureTranslator();

        return super.matching(Filters.matching(sTranslator, element));
    }

    @Override
    public LongSparseIterable elements(final long... elements) {

        ensureTranslator();

        return super.matching(Filters.containedIn(sTranslator, elements));
    }
}
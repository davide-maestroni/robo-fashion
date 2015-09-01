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

import com.github.dm.rf.android.filter.BooleanFilterBuilder;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.iterator.BooleanSparseIterable;
import com.github.dm.rf.android.translator.ToBooleanTranslator;

/**
 * Implementation of a {@link BooleanFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/19/14.
 */
class BooleanFilterBuilderImpl extends FilterBuilderImpl<BooleanSparseIterable, Boolean>
        implements BooleanFilterBuilder {

    private static volatile ToBooleanTranslator<Boolean> sTranslator;

    public BooleanFilterBuilderImpl(final BooleanSparseIterable iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    private static void ensureTranslator() {

        if (sTranslator == null) {

            sTranslator = new ToBooleanTranslator<Boolean>() {

                @Override
                public boolean translate(final Boolean element) {

                    return element;
                }
            };
        }
    }

    @Override
    public BooleanSparseIterable element(final boolean element) {

        ensureTranslator();

        return super.matching(Filters.matching(sTranslator, element));
    }
}
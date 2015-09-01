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

import com.github.dm.rf.android.entry.SparseBooleanArrayEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.SparseBooleanArrayFilterBuilder;
import com.github.dm.rf.android.iterator.SparseBooleanArrayIterable;
import com.github.dm.rf.android.translator.ToBooleanTranslator;
import com.github.dm.rf.android.translator.ToIntTranslator;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.translator.Translators;

import java.util.Collection;

/**
 * Implementation of a {@link SparseBooleanArrayFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 */
class SparseBooleanArrayFilterBuilderImpl
        extends FilterBuilderImpl<SparseBooleanArrayIterable, SparseBooleanArrayEntry>
        implements SparseBooleanArrayFilterBuilder {

    public SparseBooleanArrayFilterBuilderImpl(final SparseBooleanArrayIterable iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SparseBooleanArrayIterable key(final int key) {

        final ToIntTranslator<SparseBooleanArrayEntry> translator = Translators.intKey();

        final Filter<SparseBooleanArrayEntry> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SparseBooleanArrayIterable keys(final int... keys) {

        final ToIntTranslator<SparseBooleanArrayEntry> translator = Translators.intKey();

        final Filter<SparseBooleanArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseBooleanArrayIterable keys(final Collection<Integer> keys) {

        final Translator<SparseBooleanArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseBooleanArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseBooleanArrayIterable keys(final Iterable<Integer> keys) {

        final Translator<SparseBooleanArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseBooleanArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseBooleanArrayIterable value(final boolean value) {

        final ToBooleanTranslator<SparseBooleanArrayEntry> translator = Translators.booleanValue();

        final Filter<SparseBooleanArrayEntry> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }
}
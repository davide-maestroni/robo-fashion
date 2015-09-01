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

import com.github.dm.rf.android.entry.SparseLongArrayEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.SparseLongArrayFilterBuilder;
import com.github.dm.rf.android.iterator.SparseLongArrayIterable;
import com.github.dm.rf.android.translator.ToIntTranslator;
import com.github.dm.rf.android.translator.ToLongTranslator;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.translator.Translators;

import java.util.Collection;

/**
 * Implementation of a {@link SparseLongArrayFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 */
class SparseLongArrayFilterBuilderImpl
        extends FilterBuilderImpl<SparseLongArrayIterable, SparseLongArrayEntry>
        implements SparseLongArrayFilterBuilder {

    public SparseLongArrayFilterBuilderImpl(final SparseLongArrayIterable iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SparseLongArrayIterable key(final int key) {

        final ToIntTranslator<SparseLongArrayEntry> translator = Translators.intKey();

        final Filter<SparseLongArrayEntry> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable keys(final int... keys) {

        final ToIntTranslator<SparseLongArrayEntry> translator = Translators.intKey();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable keys(final Collection<Integer> keys) {

        final Translator<SparseLongArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable keys(final Iterable<Integer> keys) {

        final Translator<SparseLongArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable value(final long value) {

        final ToLongTranslator<SparseLongArrayEntry> translator = Translators.longValue();

        final Filter<SparseLongArrayEntry> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable values(final long... values) {

        final ToLongTranslator<SparseLongArrayEntry> translator = Translators.longValue();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable values(final Collection<Long> values) {

        final Translator<SparseLongArrayEntry, ?> translator = Translators.longValueObject();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SparseLongArrayIterable values(final Iterable<Long> values) {

        final Translator<SparseLongArrayEntry, ?> translator = Translators.longValueObject();

        final Filter<SparseLongArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
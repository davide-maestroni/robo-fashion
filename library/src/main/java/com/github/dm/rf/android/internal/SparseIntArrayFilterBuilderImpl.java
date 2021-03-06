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

import com.github.dm.rf.android.entry.SparseIntArrayEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.SparseIntArrayFilterBuilder;
import com.github.dm.rf.android.iterator.SparseIntArrayIterable;
import com.github.dm.rf.android.translator.ToIntTranslator;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.translator.Translators;

import java.util.Collection;

/**
 * Implementation of a {@link SparseIntArrayFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 */
class SparseIntArrayFilterBuilderImpl
        extends FilterBuilderImpl<SparseIntArrayIterable, SparseIntArrayEntry>
        implements SparseIntArrayFilterBuilder {

    public SparseIntArrayFilterBuilderImpl(final SparseIntArrayIterable iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SparseIntArrayIterable key(final int key) {

        final ToIntTranslator<SparseIntArrayEntry> translator = Translators.intKey();

        final Filter<SparseIntArrayEntry> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable keys(final int... keys) {

        final ToIntTranslator<SparseIntArrayEntry> translator = Translators.intKey();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable keys(final Collection<Integer> keys) {

        final Translator<SparseIntArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable keys(final Iterable<Integer> keys) {

        final Translator<SparseIntArrayEntry, ?> translator = Translators.intKeyObject();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable value(final int value) {

        final ToIntTranslator<SparseIntArrayEntry> translator = Translators.intValue();

        final Filter<SparseIntArrayEntry> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable values(final int... values) {

        final ToIntTranslator<SparseIntArrayEntry> translator = Translators.intValue();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable values(final Collection<Integer> values) {

        final Translator<SparseIntArrayEntry, ?> translator = Translators.intValueObject();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SparseIntArrayIterable values(final Iterable<Integer> values) {

        final Translator<SparseIntArrayEntry, ?> translator = Translators.intValueObject();

        final Filter<SparseIntArrayEntry> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
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
package com.gh.bmd.rfc.android.internal;

import com.gh.bmd.rfc.android.entry.LongSparseArrayEntry;
import com.gh.bmd.rfc.android.filter.Filter;
import com.gh.bmd.rfc.android.filter.Filters;
import com.gh.bmd.rfc.android.filter.LongSparseArrayFilterBuilder;
import com.gh.bmd.rfc.android.iterator.LongSparseArrayIterable;
import com.gh.bmd.rfc.android.translator.ToLongTranslator;
import com.gh.bmd.rfc.android.translator.Translator;
import com.gh.bmd.rfc.android.translator.Translators;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of a {@link com.gh.bmd.rfc.android.filter.LongSparseArrayFilterBuilder}.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <V> the entry value type.
 */
class LongSparseArrayFilterBuilderImpl<V>
        extends FilterBuilderImpl<LongSparseArrayIterable<V>, LongSparseArrayEntry<V>>
        implements LongSparseArrayFilterBuilder<V> {

    public LongSparseArrayFilterBuilderImpl(final LongSparseArrayIterable<V> iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public LongSparseArrayIterable<V> key(final long key) {

        final ToLongTranslator<LongSparseArrayEntry<V>> translator = Translators.longKey();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> keys(final long... keys) {

        final ToLongTranslator<LongSparseArrayEntry<V>> translator = Translators.longKey();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> keys(final Collection<Long> keys) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.longKeyObject();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> keys(final Iterable<Long> keys) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.longKeyObject();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> value(final Object value) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> values(final Object... values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter =
                Filters.containedIn(translator, Arrays.asList(values));

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> values(final Collection<?> values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public LongSparseArrayIterable<V> values(final Iterable<?> values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
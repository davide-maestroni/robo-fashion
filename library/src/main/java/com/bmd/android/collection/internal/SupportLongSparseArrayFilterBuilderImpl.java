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

import com.bmd.android.collection.entry.LongSparseArrayEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.Filters;
import com.bmd.android.collection.filter.SupportLongSparseArrayFilterBuilder;
import com.bmd.android.collection.iterator.SupportLongSparseArrayIterable;
import com.bmd.android.collection.translator.ToLongTranslator;
import com.bmd.android.collection.translator.Translator;
import com.bmd.android.collection.translator.Translators;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of a {@link com.bmd.android.collection.filter.SupportLongSparseArrayFilterBuilder}.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <V> The element value type.
 */
class SupportLongSparseArrayFilterBuilderImpl<V>
        extends FilterBuilderImpl<SupportLongSparseArrayIterable<V>, LongSparseArrayEntry<V>>
        implements SupportLongSparseArrayFilterBuilder<V> {

    public SupportLongSparseArrayFilterBuilderImpl(final SupportLongSparseArrayIterable<V> iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SupportLongSparseArrayIterable<V> key(final long key) {

        final ToLongTranslator<LongSparseArrayEntry<V>> translator = Translators.longKey();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> keys(final long... keys) {

        final ToLongTranslator<LongSparseArrayEntry<V>> translator = Translators.longKey();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> keys(final Collection<Long> keys) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.longKeyObject();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> keys(final Iterable<Long> keys) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.longKeyObject();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> value(final Object value) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> values(final Object... values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter =
                Filters.containedIn(translator, Arrays.asList(values));

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> values(final Collection<Object> values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SupportLongSparseArrayIterable<V> values(final Iterable<Object> values) {

        final Translator<LongSparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<LongSparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
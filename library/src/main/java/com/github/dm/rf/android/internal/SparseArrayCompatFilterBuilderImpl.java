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

import com.github.dm.rf.android.entry.SparseArrayEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.SparseArrayCompatFilterBuilder;
import com.github.dm.rf.android.iterator.SparseArrayCompatIterable;
import com.github.dm.rf.android.translator.ToIntTranslator;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.translator.Translators;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of a {@link SparseArrayCompatFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 *
 * @param <V> the entry value type.
 */
class SparseArrayCompatFilterBuilderImpl<V>
        extends FilterBuilderImpl<SparseArrayCompatIterable<V>, SparseArrayEntry<V>>
        implements SparseArrayCompatFilterBuilder<V> {

    public SparseArrayCompatFilterBuilderImpl(final SparseArrayCompatIterable<V> iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SparseArrayCompatIterable<V> key(final int key) {

        final ToIntTranslator<SparseArrayEntry<V>> translator = Translators.intKey();

        final Filter<SparseArrayEntry<V>> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> keys(final int... keys) {

        final ToIntTranslator<SparseArrayEntry<V>> translator = Translators.intKey();

        final Filter<SparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> keys(final Collection<Integer> keys) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.intKeyObject();

        final Filter<SparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> keys(final Iterable<Integer> keys) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.intKeyObject();

        final Filter<SparseArrayEntry<V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> value(final Object value) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<SparseArrayEntry<V>> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> values(final Object... values) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<SparseArrayEntry<V>> filter =
                Filters.containedIn(translator, Arrays.asList(values));

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> values(final Collection<Object> values) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<SparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SparseArrayCompatIterable<V> values(final Iterable<Object> values) {

        final Translator<SparseArrayEntry<V>, ?> translator = Translators.objectValue();

        final Filter<SparseArrayEntry<V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
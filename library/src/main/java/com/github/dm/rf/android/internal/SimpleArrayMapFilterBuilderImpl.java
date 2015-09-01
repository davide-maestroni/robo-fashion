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

import com.github.dm.rf.android.entry.SimpleArrayMapEntry;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.filter.SimpleArrayMapFilterBuilder;
import com.github.dm.rf.android.iterator.SimpleArrayMapIterable;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.translator.Translators;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of a {@link SimpleArrayMapFilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 *
 * @param <K> the entry key type.
 * @param <V> the entry value type.
 */
class SimpleArrayMapFilterBuilderImpl<K, V>
        extends FilterBuilderImpl<SimpleArrayMapIterable<K, V>, SimpleArrayMapEntry<K, V>>
        implements SimpleArrayMapFilterBuilder<K, V> {

    public SimpleArrayMapFilterBuilderImpl(final SimpleArrayMapIterable<K, V> iterable,
            final boolean isInclusive) {

        super(iterable, isInclusive);
    }

    @Override
    public SimpleArrayMapIterable<K, V> key(final Object key) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectKey();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.matching(translator, key);

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> keys(final Object... keys) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectKey();

        final Filter<SimpleArrayMapEntry<K, V>> filter =
                Filters.containedIn(translator, Arrays.asList(keys));

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> keys(final Collection<?> keys) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectKey();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> keys(final Iterable<?> keys) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectKey();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.containedIn(translator, keys);

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> value(final Object value) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectValue();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.matching(translator, value);

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> values(final Object... values) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectValue();

        final Filter<SimpleArrayMapEntry<K, V>> filter =
                Filters.containedIn(translator, Arrays.asList(values));

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> values(final Collection<?> values) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectValue();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }

    @Override
    public SimpleArrayMapIterable<K, V> values(final Iterable<?> values) {

        final Translator<SimpleArrayMapEntry<K, V>, ?> translator = Translators.objectValue();

        final Filter<SimpleArrayMapEntry<K, V>> filter = Filters.containedIn(translator, values);

        return super.matching(filter);
    }
}
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
import com.bmd.android.collection.entry.LongSparseObjectEntry;
import com.bmd.android.collection.entry.ParcelableLongSparseObjectEntry;
import com.bmd.android.collection.entry.SparseEntries;
import com.bmd.android.collection.translator.FullLongTranslator;
import com.bmd.android.collection.translator.FullTranslator;

/**
 * Implementation of {@link com.bmd.android.collection.internal.SparseIterator} of
 * {@link com.bmd.android.collection.entry.LongSparseArrayEntry} elements whose keys and values are
 * transformed through the specified translators.
 * <p/>
 * Created by davide on 3/31/14.
 *
 * @param <Tv> The translated element value type.
 * @param <V>  The original element value type.
 */
class TranslatedLongSparseArrayIterator<Tv, V>
        implements SparseIterator<LongSparseArrayEntry<Tv>>, LongSparseArrayEntry<Tv> {

    private final SparseIterator<LongSparseArrayEntry<V>> mIterator;

    private final FullLongTranslator mKeyTranslator;

    private final FullTranslator<V, Tv> mValueTranslator;

    private LongSparseArrayEntry<V> mEntry;

    public TranslatedLongSparseArrayIterator(final SparseIterator<LongSparseArrayEntry<V>> wrapped,
            final FullLongTranslator keyTranslator, final FullTranslator<V, Tv> valueTranslator) {

        mIterator = wrapped;
        mKeyTranslator = keyTranslator;
        mValueTranslator = valueTranslator;
    }

    @Override
    public int getIndex() {

        return mEntry.getIndex();
    }

    @Override
    public long getKey() {

        return mKeyTranslator.translate(mEntry.getKey());
    }

    @Override
    public Tv getValue() {

        return mValueTranslator.translate(mEntry.getValue());
    }

    @Override
    public void setValue(final Tv value) {

        mEntry.setValue(mValueTranslator.revert(value));
    }

    @Override
    public LongSparseObjectEntry<Tv> toImmutable() {

        return SparseEntries.entry(getKey(), getValue());
    }

    @Override
    public ParcelableLongSparseObjectEntry<Tv> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), getValue());
    }

    @Override
    public boolean hasNext() {

        return mIterator.hasNext();
    }

    @Override
    public LongSparseArrayEntry<Tv> next() {

        mEntry = mIterator.next();

        return this;
    }

    @Override
    public void remove() {

        mIterator.remove();
    }

    @Override
    public int originalIndex() {

        return mIterator.originalIndex();
    }

    @Override
    public void reset() {

        mIterator.reset();
    }
}
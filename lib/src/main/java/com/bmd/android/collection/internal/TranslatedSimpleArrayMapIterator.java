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

import com.bmd.android.collection.entry.ObjectSparseObjectEntry;
import com.bmd.android.collection.entry.ParcelableObjectSparseObjectEntry;
import com.bmd.android.collection.entry.SimpleArrayMapEntry;
import com.bmd.android.collection.entry.SparseEntries;
import com.bmd.android.collection.translator.FullTranslator;

/**
 * Implementation of {@link com.bmd.android.collection.internal.SparseIterator} of
 * {@link com.bmd.android.collection.entry.SimpleArrayMapEntry} elements whose keys and values are
 * transformed through the specified translators.
 * <p/>
 * Created by davide on 3/31/14.
 *
 * @param <Tk> The translated element key type.
 * @param <K>  The original element key type.
 * @param <Tv> The translated element value type.
 * @param <V>  The original element value type.
 */
class TranslatedSimpleArrayMapIterator<Tk, K, Tv, V>
        implements SparseIterator<SimpleArrayMapEntry<Tk, Tv>>, SimpleArrayMapEntry<Tk, Tv> {

    private final SparseIterator<SimpleArrayMapEntry<K, V>> mIterator;

    private final FullTranslator<K, Tk> mKeyTranslator;

    private final FullTranslator<V, Tv> mValueTranslator;

    private SimpleArrayMapEntry<K, V> mEntry;

    public TranslatedSimpleArrayMapIterator(final SparseIterator<SimpleArrayMapEntry<K, V>> wrapped,
            final FullTranslator<K, Tk> keyTranslator,
            final FullTranslator<V, Tv> valueTranslator) {

        mIterator = wrapped;
        mKeyTranslator = keyTranslator;
        mValueTranslator = valueTranslator;
    }

    @Override
    public int getIndex() {

        return mEntry.getIndex();
    }

    @Override
    public Tk getKey() {

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
    public ObjectSparseObjectEntry<Tk, Tv> toImmutable() {

        return SparseEntries.entry(getKey(), getValue());
    }

    @Override
    public ParcelableObjectSparseObjectEntry<Tk, Tv> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), getValue());
    }

    @Override
    public boolean hasNext() {

        return mIterator.hasNext();
    }

    @Override
    public SimpleArrayMapEntry<Tk, Tv> next() {

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
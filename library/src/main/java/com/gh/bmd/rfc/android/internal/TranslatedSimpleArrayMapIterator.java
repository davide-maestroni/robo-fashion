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

import com.gh.bmd.rfc.android.entry.ObjectSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.ParcelableObjectSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.SimpleArrayMapEntry;
import com.gh.bmd.rfc.android.entry.SparseEntries;
import com.gh.bmd.rfc.android.translator.FullTranslator;

/**
 * Implementation of {@link SparseIterator} of
 * {@link com.gh.bmd.rfc.android.entry.SimpleArrayMapEntry} elements whose keys and values are
 * transformed through the specified translators.
 * <p/>
 * Created by davide on 3/31/14.
 *
 * @param <TK> the translated element key type.
 * @param <K>  the original element key type.
 * @param <TV> the translated element value type.
 * @param <V>  the original element value type.
 */
class TranslatedSimpleArrayMapIterator<TK, K, TV, V>
        implements SparseIterator<SimpleArrayMapEntry<TK, TV>>, SimpleArrayMapEntry<TK, TV> {

    private final SparseIterator<SimpleArrayMapEntry<K, V>> mIterator;

    private final FullTranslator<K, TK> mKeyTranslator;

    private final FullTranslator<V, TV> mValueTranslator;

    private SimpleArrayMapEntry<K, V> mEntry;

    public TranslatedSimpleArrayMapIterator(final SparseIterator<SimpleArrayMapEntry<K, V>> wrapped,
            final FullTranslator<K, TK> keyTranslator,
            final FullTranslator<V, TV> valueTranslator) {

        mIterator = wrapped;
        mKeyTranslator = keyTranslator;
        mValueTranslator = valueTranslator;
    }

    @Override
    public int getIndex() {

        return mEntry.getIndex();
    }

    @Override
    public TK getKey() {

        return mKeyTranslator.translate(mEntry.getKey());
    }

    @Override
    public TV getValue() {

        return mValueTranslator.translate(mEntry.getValue());
    }

    @Override
    public void setValue(final TV value) {

        mEntry.setValue(mValueTranslator.revert(value));
    }

    @Override
    public ObjectSparseObjectEntry<TK, TV> toImmutable() {

        return SparseEntries.entry(getKey(), getValue());
    }

    @Override
    public ParcelableObjectSparseObjectEntry<TK, TV> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), getValue());
    }

    @Override
    public boolean hasNext() {

        return mIterator.hasNext();
    }

    @Override
    public SimpleArrayMapEntry<TK, TV> next() {

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
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

import com.bmd.android.collection.entry.IntSparseIntEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseIntEntry;
import com.bmd.android.collection.entry.SparseEntries;
import com.bmd.android.collection.entry.SparseIntArrayEntry;
import com.bmd.android.collection.translator.FullIntTranslator;

/**
 * Implementation of {@link SparseIterator} of
 * {@link com.bmd.android.collection.entry.SparseIntArrayEntry} elements whose keys and values are
 * transformed through the specified translators.
 * <p/>
 * Created by davide on 3/31/14.
 */
class TranslatedSparseIntArrayIterator
        implements SparseIterator<SparseIntArrayEntry>, SparseIntArrayEntry {

    private final SparseIterator<SparseIntArrayEntry> mIterator;

    private final FullIntTranslator mKeyTranslator;

    private final FullIntTranslator mValueTranslator;

    private SparseIntArrayEntry mEntry;

    public TranslatedSparseIntArrayIterator(final SparseIterator<SparseIntArrayEntry> wrapped,
            final FullIntTranslator keyTranslator, final FullIntTranslator valueTranslator) {

        mIterator = wrapped;
        mKeyTranslator = keyTranslator;
        mValueTranslator = valueTranslator;
    }

    @Override
    public int getIndex() {

        return mEntry.getIndex();
    }

    @Override
    public int getKey() {

        return mKeyTranslator.translate(mEntry.getKey());
    }

    @Override
    public int getValue() {

        return mValueTranslator.translate(mEntry.getValue());
    }

    @Override
    public void setValue(final int value) {

        mEntry.setValue(mValueTranslator.revert(value));
    }

    @Override
    public IntSparseIntEntry toImmutable() {

        return SparseEntries.entry(getKey(), getValue());
    }

    @Override
    public ParcelableIntSparseIntEntry toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), getValue());
    }

    @Override
    public boolean hasNext() {

        return mIterator.hasNext();
    }

    @Override
    public SparseIntArrayEntry next() {

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
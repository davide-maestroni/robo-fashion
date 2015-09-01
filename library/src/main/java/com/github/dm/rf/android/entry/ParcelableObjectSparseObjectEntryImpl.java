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
package com.github.dm.rf.android.entry;

import android.os.Parcel;

/**
 * Implementation of {@link ParcelableObjectSparseObjectEntry}.
 * <p/>
 * Created by davide-maestroni on 3/18/14.
 *
 * @param <K> the key type.
 * @param <V> the value type.
 */
class ParcelableObjectSparseObjectEntryImpl<K, V>
        implements ParcelableObjectSparseObjectEntry<K, V> {

    public static final Creator<ParcelableObjectSparseObjectEntryImpl> CREATOR =
            new Creator<ParcelableObjectSparseObjectEntryImpl>() {

                public ParcelableObjectSparseObjectEntryImpl createFromParcel(final Parcel parcel) {

                    return new ParcelableObjectSparseObjectEntryImpl(parcel);
                }

                public ParcelableObjectSparseObjectEntryImpl[] newArray(final int size) {

                    return new ParcelableObjectSparseObjectEntryImpl[size];
                }
            };

    private final K mKey;

    private final V mValue;

    public ParcelableObjectSparseObjectEntryImpl(final K key, final V value) {

        mKey = key;
        mValue = value;
    }

    @SuppressWarnings("unchecked")
    public ParcelableObjectSparseObjectEntryImpl(final Parcel parcel) {

        final ClassLoader classLoader = getClass().getClassLoader();
        mKey = (K) parcel.readValue(classLoader);
        mValue = (V) parcel.readValue(classLoader);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeValue(mKey);
        parcel.writeValue(mValue);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(final Object o) {

        return SparseEntries.equal(this, o);
    }

    @Override
    public int hashCode() {

        return SparseEntries.hashCode(this);
    }

    @Override
    public K getKey() {

        return mKey;
    }

    @Override
    public V getValue() {

        return mValue;
    }
}
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
package com.gh.bmd.rfc.android.entry;

import android.os.Parcel;

/**
 * Implementation of {@link ParcelableIntSparseObjectEntry}.
 * <p/>
 * Created by davide on 3/18/14.
 *
 * @param <V> the value type.
 */
class ParcelableIntSparseObjectEntryImpl<V> implements ParcelableIntSparseObjectEntry<V> {

    public static final Creator<ParcelableIntSparseObjectEntryImpl> CREATOR =
            new Creator<ParcelableIntSparseObjectEntryImpl>() {

                public ParcelableIntSparseObjectEntryImpl createFromParcel(final Parcel parcel) {

                    return new ParcelableIntSparseObjectEntryImpl(parcel);
                }

                public ParcelableIntSparseObjectEntryImpl[] newArray(final int size) {

                    return new ParcelableIntSparseObjectEntryImpl[size];
                }
            };

    private final int mKey;

    private final V mValue;

    public ParcelableIntSparseObjectEntryImpl(final int key, final V value) {

        mKey = key;
        mValue = value;
    }

    public ParcelableIntSparseObjectEntryImpl(final Parcel parcel) {

        mKey = parcel.readInt();
        //noinspection unchecked
        mValue = (V) parcel.readValue(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeInt(mKey);
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
    public int getKey() {

        return mKey;
    }

    @Override
    public V getValue() {

        return mValue;
    }
}
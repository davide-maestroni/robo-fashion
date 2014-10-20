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
package com.bmd.android.collection.entry;

import android.os.Parcel;

/**
 * Implementation of {@link ParcelableIntSparseBooleanEntry}.
 * <p/>
 * Created by davide on 3/18/14.
 */
class ParcelableIntSparseBooleanEntryImpl implements ParcelableIntSparseBooleanEntry {

    public static final Creator<ParcelableIntSparseBooleanEntryImpl> CREATOR =
            new Creator<ParcelableIntSparseBooleanEntryImpl>() {

                public ParcelableIntSparseBooleanEntryImpl createFromParcel(final Parcel parcel) {

                    return new ParcelableIntSparseBooleanEntryImpl(parcel);
                }

                public ParcelableIntSparseBooleanEntryImpl[] newArray(final int size) {

                    return new ParcelableIntSparseBooleanEntryImpl[size];
                }
            };

    private final int mKey;

    private final boolean mValue;

    public ParcelableIntSparseBooleanEntryImpl(final int key, final boolean value) {

        mKey = key;
        mValue = value;
    }

    public ParcelableIntSparseBooleanEntryImpl(final Parcel parcel) {

        mKey = parcel.readInt();
        mValue = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeInt(mKey);
        parcel.writeByte((byte) (mValue ? 1 : 0));
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
    public boolean getValue() {

        return mValue;
    }
}
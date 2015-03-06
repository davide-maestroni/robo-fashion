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
package com.gh.bmd.rfc.android.example;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;

import com.gh.bmd.rfc.android.entry.ParcelableObjectSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.SimpleArrayMapEntry;
import com.gh.bmd.rfc.android.iterator.SimpleArrayMapIterable;
import com.gh.bmd.rfc.android.v4.SparseCollections;

import java.util.Iterator;

/**
 * Enhanced implementation of {@link android.support.v4.util.SimpleArrayMap} supporting
 * parcelable serialization, iteration and proper equals implementation
 * (see <a href="https://code.google.com/p/android/issues/detail?id=68406">issue 68406</a>).
 *
 * @param <K> the key type.
 * @param <V> the value type.
 */
public class EnhancedArrayMap<K, V> extends SimpleArrayMap<K, V>
        implements Iterable<SimpleArrayMapEntry<K, V>>, Parcelable {

    public static final Creator<EnhancedArrayMap> CREATOR = new Creator<EnhancedArrayMap>() {

        public EnhancedArrayMap createFromParcel(final Parcel parcel) {

            return new EnhancedArrayMap(parcel);
        }

        public EnhancedArrayMap[] newArray(final int size) {

            return new EnhancedArrayMap[size];
        }
    };

    private final SimpleArrayMapIterable<K, V> mIterable;

    /**
     * Overrides {@link android.support.v4.util.SimpleArrayMap#SimpleArrayMap()}.
     */
    public EnhancedArrayMap() {

        mIterable = SparseCollections.iterate(this);
    }

    /**
     * Overrides {@link android.support.v4.util.SimpleArrayMap#SimpleArrayMap(int)}.
     *
     * @param capacity the initial capacity.
     */
    public EnhancedArrayMap(final int capacity) {

        super(capacity);

        mIterable = SparseCollections.iterate(this);
    }

    /**
     * Overrides
     * {@link android.support.v4.util.SimpleArrayMap#SimpleArrayMap(android.support.v4.util
     * .SimpleArrayMap)}.
     *
     * @param map the map from which to copy the initial elements.
     */
    public EnhancedArrayMap(final SimpleArrayMap map) {

        super(map);

        mIterable = SparseCollections.iterate(this);
    }

    private EnhancedArrayMap(final Parcel parcel) {

        final Parcelable[] array =
                parcel.readParcelableArray(EnhancedArrayMap.class.getClassLoader());

        for (final Parcelable parcelable : array) {

            @SuppressWarnings("unchecked")
            final ParcelableObjectSparseObjectEntry<K, V> entry =
                    (ParcelableObjectSparseObjectEntry<K, V>) parcelable;

            put(entry.getKey(), entry.getValue());
        }

        mIterable = SparseCollections.iterate(this);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeParcelableArray(
                mIterable.toParcelableArray(ParcelableObjectSparseObjectEntry.class), 0);
    }

    @Override
    public boolean equals(final Object object) {

        if (object == this) {

            return true;
        }

        if (object instanceof SimpleArrayMap) {

            return mIterable.isEqualTo((SimpleArrayMap<?, ?>) object);
        }

        return super.equals(object);
    }

    @Override
    public Iterator<SimpleArrayMapEntry<K, V>> iterator() {

        return mIterable.iterator();
    }
}
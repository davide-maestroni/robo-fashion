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
package com.bmd.android.collection.example;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.util.SimpleArrayMap;

import com.bmd.android.collection.CompatCollections;
import com.bmd.android.collection.entry.SimpleArrayMapEntry;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link com.bmd.android.collection.example.EnhancedArrayMap} class.
 * <p/>
 * Created by davide on 4/24/14.
 */
public class EnhancedArrayMapTest extends TestCase {

    private EnhancedArrayMap<Integer, String> mArray;

    public void testEquals() {

        assertThat(mArray).isEqualTo(mArray);
        assertThat(mArray.equals(mArray)).isTrue();

        final EnhancedArrayMap<Integer, String> array = new EnhancedArrayMap<Integer, String>();

        for (int i = 0; i < 5; i++) {

            array.put(i, String.valueOf(i));
        }

        assertThat(mArray).isEqualTo(array);
        assertThat(array).isEqualTo(mArray);
        assertThat(mArray.equals(array)).isTrue();
        assertThat(array.equals(mArray)).isTrue();

        final SimpleArrayMap<Integer, String> simpleArray = new SimpleArrayMap<Integer, String>();

        for (int i = 0; i < 5; i++) {

            simpleArray.put(i, String.valueOf(i));
        }

        assertThat(mArray.equals(simpleArray)).isTrue();
        assertThat(simpleArray.equals(mArray)).isFalse();
    }

    public void testIterator() {

        int i = 0;

        for (final SimpleArrayMapEntry<Integer, String> entry : mArray) {

            assertThat(entry.getKey()).isEqualTo(i);
            assertThat(entry.getValue()).isEqualTo(String.valueOf(i));

            ++i;
        }
    }

    public void testParcelable() {

        final Bundle bundle = new Bundle();
        bundle.putParcelable("array", mArray);

        final Parcel parcel = Parcel.obtain();
        bundle.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        final Bundle out = parcel.readBundle();
        out.setClassLoader(CompatCollections.class.getClassLoader());

        assertThat(out.getParcelable("array")).isEqualTo(mArray);
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        final EnhancedArrayMap<Integer, String> array = new EnhancedArrayMap<Integer, String>();

        for (int i = 0; i < 5; i++) {

            array.put(i, String.valueOf(i));
        }

        mArray = array;
    }
}
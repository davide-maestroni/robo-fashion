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
package com.bmd.android.collection;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseLongArray;

import com.bmd.android.collection.entry.IntSparseLongEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseLongEntry;
import com.bmd.android.collection.entry.SparseEntries;
import com.bmd.android.collection.entry.SparseLongArrayEntry;
import com.bmd.android.collection.iterator.SparseIterable.Action;
import com.bmd.android.collection.iterator.SparseIterable.Condition;
import com.bmd.android.collection.translator.IntTranslator;
import com.bmd.android.collection.translator.LongTranslator;
import com.bmd.android.collection.translator.Translator;
import com.bmd.android.collection.v18.SparseCollections;

import junit.framework.TestCase;

import org.assertj.core.data.MapEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link android.util.SparseLongArray} class.
 * <p/>
 * Created by davide on 3/15/14.
 */
@TargetApi(VERSION_CODES.JELLY_BEAN_MR2)
public class SparseLongArrayTest extends TestCase {

    private SparseLongArray mArray;

    public void testContains() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .contains(SparseEntries.entry(3, 3L))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .contains(SparseEntries.entry(3, 3L))).isFalse();

        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .firstPositionOf(SparseEntries.entry(3, 3L))).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .firstIndexOf(SparseEntries.entry(3, 3L))).isEqualTo(3);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOf(SparseEntries.entry(3, 3L))).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOf(SparseEntries.entry(3, 3L))).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOf(SparseEntries.entry(0, 0L))).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOf(SparseEntries.entry(0, 0L))).isEqualTo(0);

        assertThat(SparseCollections.iterate(mArray)
                                    .containsAll(SparseEntries.entry(3, 3L),
                                                 SparseEntries.entry(1, 1L))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAll(Arrays.asList(SparseEntries.entry(3, 3L),
                                                               SparseEntries.entry(5,
                                                                                   5L)))).isFalse();
        assertThat(SparseCollections.iterate(mArray).containsAll(SparseCollections.iterate(mArray)))
                .isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAny(Arrays.asList(SparseEntries.entry(5, 5L),
                                                               SparseEntries.entry(3, 3L),
                                                               SparseEntries.entry(5,
                                                                                   5L)))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAny(SparseEntries.entry(2, 7L))).isFalse();
        assertThat(SparseCollections.iterate(mArray).containsAny(SparseCollections.iterate(mArray)))
                .isTrue();

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsKey(3)).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).only().to(2).reverse().containsKey(3)).isFalse();

        assertThat(SparseCollections.iterate(mArray).but().to(2).positionOfKey(3)).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray).but().to(2).indexOfKey(3)).isEqualTo(3);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .positionOfKey(3)).isEqualTo(-1);
        assertThat(
                SparseCollections.iterate(mArray).only().to(2).reverse().indexOfKey(3)).isEqualTo(
                -1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .positionOfKey(0)).isEqualTo(2);
        assertThat(
                SparseCollections.iterate(mArray).only().to(2).reverse().indexOfKey(0)).isEqualTo(
                0);

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsKey(3)).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsAllKeys(2, 3)).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsAnyKey(2, 3)).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAllKeys(Arrays.asList(2, 3))).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAnyKey(Arrays.asList(2, 3))).isTrue();

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue(3)).isTrue();
        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue(9)).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsValue(3)).isFalse();

        assertThat(SparseCollections.iterate(mArray).but().to(2).firstPositionOfValue(3)).isEqualTo(
                0);
        assertThat(SparseCollections.iterate(mArray).but().to(2).firstIndexOfValue(3)).isEqualTo(3);
        assertThat(SparseCollections.iterate(mArray).only().to(2).reverse().firstPositionOfValue(3))
                .isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue(3)).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray).only().to(2).reverse().firstPositionOfValue(0))
                .isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue(0)).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOfValue(-7)).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue(-7)).isEqualTo(-1);

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue(3)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().to(2).reverse().containsAllValues(2, 3))
                .isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsAnyValue(2, 3)).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAllValues(Arrays.asList(2L, 3L))).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAnyValue(Arrays.asList(2L, 3L))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAnyValue(Arrays.asList(-2L, -3L))).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAllKeys(
                                            SparseCollections.iterate(mArray).keys())).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAnyKey(
                                            SparseCollections.iterate(mArray).keys())).isTrue();
    }

    public void testConversions() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .toLongs(new Translator<SparseLongArrayEntry, Long>() {

                                        @Override
                                        public Long translate(final SparseLongArrayEntry element) {

                                            return element.getValue();
                                        }
                                    })
                                    .reverse()).containsExactly(4L, 3L, 2L, 1L, 0L);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .key(2)
                                    .toBooleans(new Translator<SparseLongArrayEntry, Boolean>() {

                                        @Override
                                        public Boolean translate(
                                                final SparseLongArrayEntry element) {

                                            return (2 == element.getValue());
                                        }
                                    })).containsExactly(true);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .toIntegers(new Translator<SparseLongArrayEntry, Integer>() {

                                        @Override
                                        public Integer translate(
                                                final SparseLongArrayEntry element) {

                                            return element.getKey();
                                        }
                                    })
                                    .only()
                                    .first(3)
                                    .retain()
                                    .reverse()).containsExactly(2, 3, 4);
        assertThat(SparseCollections.iterate(mArray).keys()).containsExactly(2, 3, 4);
    }

    public void testCount() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray).countOf(SparseEntries.entry(1, 1L))).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .countOf(SparseEntries.entry(1, 1L))).isEqualTo(1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(1)
                                    .countOf(SparseEntries.entry(1, 1L))).isZero();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(1)
                                    .countOf(SparseEntries.entry(1, "1"))).isZero();

        SparseCollections.iterate(mArray).replaceValues(new LongTranslator() {

            @Override
            public long translate(final long element) {

                if (element == 2) {

                    return 1;
                }

                return element;
            }
        });
        assertThat(SparseCollections.iterate(mArray).countOf(SparseEntries.entry(1, 1L))).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray).values().countOf(1L)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).values().reverse().countOf(1L)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).reverse().values().countOf(1L)).isEqualTo(2);
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).values().countOf(1L)).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray).reverse().only().first(2).values().countOf(1L))
                .isZero();
        assertThat(SparseCollections.iterate(mArray).only().first(2).values().reverse().countOf(1L))
                .isEqualTo(1);
    }

    public void testEquals() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final SparseLongArray array = new SparseLongArray();

        for (int i = 0; i < 5; i++) {

            array.append(i, i);
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .isStrictlyEqualTo(SparseCollections.iterate(array))).isTrue();
        assertThat(SparseCollections.iterate(array)
                                    .isStrictlyEqualTo(SparseCollections.iterate(mArray))).isTrue();
        assertThat(SparseCollections.iterate(array)
                                    .only()
                                    .key(2)
                                    .remove()
                                    .isStrictlyEqualTo(
                                            SparseCollections.iterate(mArray))).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .isStrictlyEqualTo(SparseCollections.iterate(array))).isFalse();

        final SparseLongArray sparseArray = SparseCollections.iterate(mArray).toSparseArray();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(mArray)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(mArray)).isFalse();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sparseArray)).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).isEqualTo(sparseArray)).isFalse();

        final ArrayList<IntSparseLongEntry> list =
                SparseCollections.iterate(mArray).toImmutableList();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(list)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(list)).isFalse();

        final ArrayList<ParcelableIntSparseLongEntry> parcelableList =
                SparseCollections.iterate(mArray).toParcelableList();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(parcelableList)).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(2)
                                    .isEqualTo(parcelableList)).isFalse();

        final Map<Integer, Long> map = SparseCollections.iterate(mArray).toMap();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(map)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(map)).isFalse();

        final SortedMap<Integer, Long> sortedMap = SparseCollections.iterate(mArray).toSortedMap();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sortedMap)).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).isEqualTo(sortedMap)).isFalse();

        mArray.append(7, 7L);
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sparseArray)).isFalse();
        assertThat(SparseCollections.iterate(mArray).but().last(1).isEqualTo(sparseArray)).isTrue();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(list)).isFalse();
        assertThat(SparseCollections.iterate(mArray).but().last(1).isEqualTo(list)).isTrue();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(parcelableList)).isFalse();
        assertThat(
                SparseCollections.iterate(mArray).but().last(1).isEqualTo(parcelableList)).isTrue();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(map)).isFalse();
        assertThat(SparseCollections.iterate(mArray).but().last(1).isEqualTo(map)).isTrue();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sortedMap)).isFalse();
        assertThat(SparseCollections.iterate(mArray).but().last(1).isEqualTo(sortedMap)).isTrue();
    }

    public void testFilters() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray).only().last(2).toMap()).contains(
                MapEntry.entry(3, 3L), MapEntry.entry(4, 4L));

        assertThat(
                SparseCollections.iterate(mArray).only().first(3).only().last(2).toMap()).contains(
                MapEntry.entry(1, 1L), MapEntry.entry(2, 2L));

        assertThat(SparseCollections.iterate(mArray).only().last(3).but().last(1).toMap()).contains(
                MapEntry.entry(2, 2L), MapEntry.entry(3, 3L));

        assertThat(
                SparseCollections.iterate(mArray).only().indexes(2, 1).positionOfKey(2)).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray).only().indexes(2, 1).indexOfKey(2)).isEqualTo(
                2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes(2, 1)
                                    .reverse()
                                    .positionOfKey(2)).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes(2, 1)
                                    .reverse()
                                    .indexOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes(Arrays.asList(2, 1))
                                    .positionOfKey(2)).isEqualTo(1);
        assertThat(
                SparseCollections.iterate(mArray).only().indexes(Arrays.asList(2, 1)).indexOfKey(2))
                .isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes(Arrays.asList(2, 1))
                                    .reverse()
                                    .positionOfKey(2)).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes(Arrays.asList(2, 1))
                                    .reverse()
                                    .indexOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes((Iterable<Integer>) Arrays.asList(2, 1))
                                    .positionOfKey(2)).isEqualTo(1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes((Iterable<Integer>) Arrays.asList(2, 1))
                                    .indexOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes((Iterable<Integer>) Arrays.asList(2, 1))
                                    .reverse()
                                    .positionOfKey(2)).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .indexes((Iterable<Integer>) Arrays.asList(2, 1))
                                    .reverse()
                                    .indexOfKey(2)).isEqualTo(2);

        assertThat(SparseCollections.iterate(mArray).positionOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).indexOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).but().first(2).positionOfKey(2)).isZero();
        assertThat(SparseCollections.iterate(mArray).but().first(2).indexOfKey(2)).isEqualTo(2);

        assertThat(SparseCollections.iterate(mArray).reverse().positionOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).reverse().indexOfKey(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).reverse().positionOfKey(1)).isEqualTo(3);
        assertThat(SparseCollections.iterate(mArray).reverse().indexOfKey(1)).isEqualTo(1);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .but()
                                    .first(2)
                                    .positionOfKey(2)).isZero();
        assertThat(
                SparseCollections.iterate(mArray).reverse().but().first(2).indexOfKey(2)).isEqualTo(
                2);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .but()
                                    .first(2)
                                    .positionOfKey(1)).isEqualTo(1);
        assertThat(
                SparseCollections.iterate(mArray).reverse().but().first(2).indexOfKey(1)).isEqualTo(
                1);

        assertThat(SparseCollections.iterate(mArray).firstPositionOfValue(2)).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).firstIndexOfValue(2)).isEqualTo(2);
        assertThat(
                SparseCollections.iterate(mArray).but().first(2).firstPositionOfValue(2)).isZero();
        assertThat(SparseCollections.iterate(mArray).but().first(2).firstIndexOfValue(2)).isEqualTo(
                2);

        assertThat(SparseCollections.iterate(mArray).keys()).containsExactly(0, 1, 2, 3, 4);
        assertThat(SparseCollections.iterate(mArray).values()).containsExactly(0L, 1L, 2L, 3L, 4L);
        assertThat(SparseCollections.iterate(mArray).keys().reverse()).containsExactly(4, 3, 2, 1,
                                                                                       0);
        assertThat(SparseCollections.iterate(mArray).reverse().keys()).containsExactly(4, 3, 2, 1,
                                                                                       0);
        assertThat(SparseCollections.iterate(mArray).reverse().keys().reverse()).containsExactly(0,
                                                                                                 1,
                                                                                                 2,
                                                                                                 3,
                                                                                                 4);
        assertThat(SparseCollections.iterate(mArray).reverse().reverse().values()).containsExactly(
                0L, 1L, 2L, 3L, 4L);

        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .keys()
                                    .translate(new Translator<Integer, Integer>() {

                                        @Override
                                        public Integer translate(final Integer element) {

                                            return element + 1;
                                        }

                                    })
                                    .reverse()).containsExactly(1, 2, 3, 4, 5);
        assertThat(
                SparseCollections.iterate(mArray).reverse().only().first(3).keys()).containsExactly(
                4, 3, 2);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .only()
                                    .first(3)
                                    .keys()
                                    .translate(new Translator<Integer, Integer>() {

                                        @Override
                                        public Integer translate(final Integer element) {

                                            return element * 2;
                                        }

                                    })).containsExactly(8, 6, 4);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .only()
                                    .first(3)
                                    .keys()
                                    .translate(new Translator<Integer, Integer>() {

                                        @Override
                                        public Integer translate(final Integer element) {

                                            return element * 2;
                                        }

                                    })
                                    .reverse()).containsExactly(4, 6, 8);

        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .from(2)
                                    .but()
                                    .last(2)
                                    .keys()).containsExactly(2);
    }

    public void testImmutable() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final ArrayList<IntSparseLongEntry> arrayList =
                SparseCollections.iterate(mArray).only().first(2).toImmutableList();

        assertThat(arrayList).hasSize(2);
        assertThat(arrayList.get(0).getKey()).isEqualTo(0);
        assertThat(arrayList.get(0).getValue()).isEqualTo(0);
        assertThat(arrayList.get(1).getKey()).isEqualTo(1);
        assertThat(arrayList.get(1).getValue()).isEqualTo(1);

        final IntSparseLongEntry[] array = SparseCollections.iterate(mArray)
                                                            .only()
                                                            .key(2)
                                                            .toImmutableArray(
                                                                    IntSparseLongEntry.class);

        assertThat(array).hasSize(1);
        assertThat(array[0].getKey()).isEqualTo(2);
        assertThat(array[0].getValue()).isEqualTo(2);
        assertThat(array).containsExactly(SparseEntries.entry(2, 2L));

        final ArrayList<IntSparseLongEntry> filledList = new ArrayList<IntSparseLongEntry>(2);

        SparseCollections.iterate(mArray).only().first(2).fillImmutable(filledList);

        assertThat(filledList).isEqualTo(arrayList);

        final IntSparseLongEntry[] filledArray = new IntSparseLongEntry[2];

        SparseCollections.iterate(mArray).only().value(2).fillImmutable(filledArray);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.entry(2, 2L));

        SparseCollections.iterate(mArray).only().value(2).fillImmutable(filledArray, 1);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.entry(2, 2L));
        assertThat(filledArray[0]).isEqualTo(filledArray[1]);

        try {

            SparseCollections.iterate(mArray).fillImmutable(filledArray);

            fail();

        } catch (final IndexOutOfBoundsException e) {

        }

        final Object[] objectArray = new Object[2];

        SparseCollections.iterate(mArray).only().to(1).fillImmutable(objectArray);

        assertThat(objectArray).containsExactly((Object[]) filledArray);
    }

    public void testIterator() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final ArrayList<IntSparseLongEntry> forList = new ArrayList<IntSparseLongEntry>();

        for (final SparseLongArrayEntry entry : SparseCollections.iterate(mArray)) {

            forList.add(entry.toImmutable());
        }

        final ArrayList<IntSparseLongEntry> forEachList = new ArrayList<IntSparseLongEntry>();

        SparseCollections.iterate(mArray).forEach(new Action<SparseLongArrayEntry>() {

            @Override
            public void onNext(final SparseLongArrayEntry element, final int count,
                    final int index) {

                forEachList.add(element.toImmutable());
            }
        });

        assertThat(forList).isEqualTo(forEachList);

        final int[] totals = new int[2];

        SparseCollections.iterate(mArray).doWhile(new Condition<SparseLongArrayEntry>() {

            @Override
            public boolean onNext(final SparseLongArrayEntry element, final int count,
                    final int index) {

                ++totals[0];
                totals[1] = count + 1;

                return element.getKey() != 2;
            }
        });

        assertThat(totals).containsOnly(3);

        totals[0] = 0;

        assertThat(SparseCollections.iterate(mArray).each(new Condition<SparseLongArrayEntry>() {

            @Override
            public boolean onNext(final SparseLongArrayEntry element, final int count,
                    final int index) {

                ++totals[0];
                totals[1] = count + 1;

                return element.getKey() != 2;
            }

        })).isFalse();

        assertThat(totals).containsOnly(3);

        totals[0] = 0;

        assertThat(SparseCollections.iterate(mArray).any(new Condition<SparseLongArrayEntry>() {

            @Override
            public boolean onNext(final SparseLongArrayEntry element, final int count,
                    final int index) {

                ++totals[0];
                totals[1] = count + 1;

                return element.getKey() != 2;
            }
        })).isTrue();

        assertThat(totals).containsOnly(1);

        try {

            SparseCollections.iterate(mArray).iterator().remove();

            fail();

        } catch (final NoSuchElementException e) {

        }

        final Iterator<SparseLongArrayEntry> remIterator =
                SparseCollections.iterate(mArray).iterator();

        while (remIterator.hasNext()) {

            final SparseLongArrayEntry entry = remIterator.next();

            if ((entry.getKey() & 1) == 1) {

                remIterator.remove();
            }
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .toImmutableArray(IntSparseLongEntry.class)).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(2, 2L), SparseEntries.entry(4, 4L));

        for (final SparseLongArrayEntry entry : SparseCollections.iterate(mArray)) {

            if (entry.getKey() == 2) {

                entry.remove();
            }
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .toImmutableArray(IntSparseLongEntry.class)).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(4, 4L));

        try {

            for (final SparseLongArrayEntry entry : SparseCollections.iterate(mArray)) {

                entry.remove();
                entry.remove();

                fail();
            }

        } catch (final NoSuchElementException e) {

        }
    }

    public void testParcelable() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final ParcelableIntSparseLongEntry[] parcelableArray = SparseCollections.iterate(mArray)
                                                                                .only()
                                                                                .values(4, 1)
                                                                                .toParcelableArray(
                                                                                        ParcelableIntSparseLongEntry.class);

        assertThat(parcelableArray).hasSize(2);
        assertThat(parcelableArray[0].getKey()).isEqualTo(1);
        assertThat(parcelableArray[0].getValue()).isEqualTo(1);
        assertThat(parcelableArray[1].getKey()).isEqualTo(4);
        assertThat(parcelableArray[1].getValue()).isEqualTo(4);

        final ArrayList<ParcelableIntSparseLongEntry> parcelableList =
                SparseCollections.iterate(mArray)
                                 .but()
                                 .keys(Arrays.asList(1, 2, 3))
                                 .reverse()
                                 .toParcelableList();

        assertThat(parcelableList).hasSize(2);
        assertThat(parcelableList.get(0).getKey()).isEqualTo(4);
        assertThat(parcelableList.get(0).getValue()).isEqualTo(4);
        assertThat(parcelableList.get(1).getKey()).isEqualTo(0);
        assertThat(parcelableList.get(1).getValue()).isEqualTo(0);
        assertThat(parcelableList).containsExactly(SparseEntries.parcelableEntry(4, 4L),
                                                   SparseEntries.parcelableEntry(0, 0L));

        final Bundle bundle = new Bundle();
        bundle.putParcelableArray("array", parcelableArray);
        bundle.putParcelableArrayList("list", parcelableList);

        final Parcel parcel = Parcel.obtain();
        bundle.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        final Bundle out = parcel.readBundle();
        out.setClassLoader(SparseCollections.class.getClassLoader());

        assertThat(out.getParcelableArray("array")).isEqualTo(parcelableArray);
        assertThat(out.getParcelableArrayList("list")).isEqualTo(
                new ArrayList<Parcelable>(parcelableList));

        final ArrayList<ParcelableIntSparseLongEntry> filledList =
                new ArrayList<ParcelableIntSparseLongEntry>(2);

        SparseCollections.iterate(mArray).but().keys(1, 2, 3).reverse().fillParcelable(filledList);

        assertThat(filledList).isEqualTo(parcelableList);

        final ParcelableIntSparseLongEntry[] filledArray = new ParcelableIntSparseLongEntry[2];

        SparseCollections.iterate(mArray).only().value(2).fillParcelable(filledArray);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.parcelableEntry(2, 2L));

        SparseCollections.iterate(mArray).only().value(2).fillParcelable(filledArray, 1);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.parcelableEntry(2, 2L));
        assertThat(filledArray[0]).isEqualTo(filledArray[1]);

        try {

            SparseCollections.iterate(mArray).fillParcelable(filledArray);

            fail();

        } catch (final IndexOutOfBoundsException e) {

        }

        final Parcelable[] parcelables = new Parcelable[2];

        SparseCollections.iterate(mArray).only().to(1).fillParcelable(parcelables);

        assertThat(parcelables).containsExactly(filledArray);
    }

    public void testRemove() throws Exception {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .from(2)
                                    .but()
                                    .last(2)
                                    .keys()
                                    .remove()).containsExactly(0, 1, 3, 4);

        setUp();

        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .remove()
                                    .values()).containsExactly(4L, 3L);
    }

    public void testRetain() throws Exception {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .from(2)
                                    .but()
                                    .last(2)
                                    .keys()
                                    .retain()).containsExactly(2);

        setUp();

        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .retain()
                                    .values()
                                    .reverse()).containsExactly(0L, 1L, 2L);
    }

    public void testTranslations() {

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final SparseLongArray array1 =
                SparseCollections.iterate(mArray).translateValues(new LongTranslator() {

                    @Override
                    public long translate(final long value) {

                        return value;
                    }
                }).toSparseArray();

        assertThat(SparseCollections.iterate(array1).values()).containsExactly(0L, 1L, 2L, 3L, 4L);
        assertThat(SparseCollections.iterate(array1).replaceValues(new LongTranslator() {

            @Override
            public long translate(final long value) {

                return value + 1;
            }

        }).values()).containsExactly(1L, 2L, 3L, 4L, 5L);
        assertThat(SparseCollections.iterate(array1).values()).containsExactly(1L, 2L, 3L, 4L, 5L);
        assertThat(SparseCollections.iterate(array1).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 1L), SparseEntries.entry(1, 2L), SparseEntries.entry(2, 3L),
                SparseEntries.entry(3, 4L), SparseEntries.entry(4, 5L));

        final SparseLongArray array2 =
                SparseCollections.iterate(mArray).translate(new IntTranslator() {

                                                                @Override
                                                                public int translate(
                                                                        final int value) {

                                                                    return value + 1;
                                                                }
                                                            },

                                                            new LongTranslator() {

                                                                @Override
                                                                public long translate(
                                                                        final long value) {

                                                                    return value;
                                                                }
                                                            }).toSparseArray();
        assertThat(SparseCollections.iterate(array2).toImmutableList()).containsExactly(
                SparseEntries.entry(1, 0L), SparseEntries.entry(2, 1L), SparseEntries.entry(3, 2L),
                SparseEntries.entry(4, 3L), SparseEntries.entry(5, 4L));
        SparseCollections.iterate(array1).only().first(3).putInto(array2);
        assertThat(SparseCollections.iterate(array2).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 1L), SparseEntries.entry(1, 2L), SparseEntries.entry(2, 3L),
                SparseEntries.entry(3, 2L), SparseEntries.entry(4, 3L), SparseEntries.entry(5, 4L));
        SparseCollections.iterate(array1).only().first(3).appendTo(array2);
        assertThat(SparseCollections.iterate(array2).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 1L), SparseEntries.entry(1, 2L), SparseEntries.entry(2, 3L),
                SparseEntries.entry(3, 2L), SparseEntries.entry(4, 3L), SparseEntries.entry(5, 4L));

        SparseCollections.iterate(array2).only().keys(2, 3).translateValues(new LongTranslator() {

            @Override
            public long translate(final long value) {

                return value + 7;
            }
        }).putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 10L),
                SparseEntries.entry(3, 9L), SparseEntries.entry(4, 4L));
        SparseCollections.iterate(array2).only().keys(2, 3).translateValues(new LongTranslator() {

            @Override
            public long translate(final long value) {

                return value + 7;
            }
        }).appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 10L),
                SparseEntries.entry(3, 9L), SparseEntries.entry(4, 4L));
        SparseCollections.iterate(array2).only().last(1).translate(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value - 2;
            }

        }, new LongTranslator() {

            @Override
            public long translate(final long value) {

                return value - 2;
            }

        }).putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 10L),
                SparseEntries.entry(3, 2L), SparseEntries.entry(4, 4L));
        SparseCollections.iterate(array2).only().last(1).translate(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value - 2;
            }

        }, new LongTranslator() {

            @Override
            public long translate(final long value) {

                return value - 2;
            }

        }).appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 10L),
                SparseEntries.entry(3, 2L), SparseEntries.entry(4, 4L));

        final SparseLongArray array3 = SparseCollections.iterate(mArray).toSparseArray();
        assertThat(SparseCollections.iterate(array3).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 10L),
                SparseEntries.entry(3, 2L), SparseEntries.entry(4, 4L));

        SparseCollections.iterate(array3).but().last(1).translateKeys(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value + 2;
            }

        }).appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 0L),
                SparseEntries.entry(3, 1L), SparseEntries.entry(4, 10L),
                SparseEntries.entry(5, 2L));
        SparseCollections.iterate(array3).but().last(1).translateKeys(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value + 2;
            }

        }).putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, 0L), SparseEntries.entry(1, 1L), SparseEntries.entry(2, 0L),
                SparseEntries.entry(3, 1L), SparseEntries.entry(4, 10L),
                SparseEntries.entry(5, 2L));
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {

            return;
        }

        final SparseLongArray array = new SparseLongArray();

        for (int i = 0; i < 5; i++) {

            array.append(i, i);
        }

        mArray = array;
    }
}
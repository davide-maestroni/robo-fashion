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
package com.gh.bmd.rfc.android;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;

import com.gh.bmd.rfc.android.entry.IntSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.ParcelableIntSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.SparseArrayEntry;
import com.gh.bmd.rfc.android.entry.SparseEntries;
import com.gh.bmd.rfc.android.iterator.SparseIterable.Action;
import com.gh.bmd.rfc.android.iterator.SparseIterable.Condition;
import com.gh.bmd.rfc.android.translator.IntTranslator;
import com.gh.bmd.rfc.android.translator.Translator;
import com.gh.bmd.rfc.android.v18.SparseCollections;

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
 * Unit test for {@link android.support.v4.util.SparseArrayCompat} class.
 * <p/>
 * Created by davide on 3/15/14.
 */
public class SparseArrayCompatTest extends TestCase {

    private SparseArrayCompat<String> mArray;

    public void testContains() {

        assertThat(
                SparseCollections.iterate(mArray).but().to(2).contains(SparseEntries.entry(3, "3")))
                .isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .contains(SparseEntries.entry(3, "3"))).isFalse();

        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .firstPositionOf(SparseEntries.entry(3, "3"))).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .firstIndexOf(SparseEntries.entry(3, "3"))).isEqualTo(3);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOf(SparseEntries.entry(3, "3"))).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOf(SparseEntries.entry(3, "3"))).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOf(SparseEntries.entry(0, "0"))).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOf(SparseEntries.entry(0, "0"))).isEqualTo(0);

        assertThat(SparseCollections.iterate(mArray)
                                    .containsAll(SparseEntries.entry(3, "3"),
                                                 SparseEntries.entry(1, "1"))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAll(Arrays.asList(SparseEntries.entry(3, "3"),
                                                               SparseEntries.entry(5,
                                                                                   "5"))))
                .isFalse();
        assertThat(SparseCollections.iterate(mArray).containsAll(SparseCollections.iterate(mArray)))
                .isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAny(Arrays.asList(SparseEntries.entry(5, "5"),
                                                               SparseEntries.entry(3, "3"),
                                                               SparseEntries.entry(5,
                                                                                   "5")))).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAny(SparseEntries.entry(2, "7"))).isFalse();
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

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue("3")).isTrue();
        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue(3)).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsValue("3")).isFalse();

        assertThat(
                SparseCollections.iterate(mArray).but().to(2).firstPositionOfValue("3")).isEqualTo(
                0);
        assertThat(SparseCollections.iterate(mArray).but().to(2).firstIndexOfValue("3")).isEqualTo(
                3);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOfValue("3")).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue("3")).isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstPositionOfValue("0")).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue("0")).isEqualTo(0);
        assertThat(SparseCollections.iterate(mArray).only().to(2).reverse().firstPositionOfValue(0))
                .isEqualTo(-1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .firstIndexOfValue(0)).isEqualTo(-1);

        assertThat(SparseCollections.iterate(mArray).but().to(2).containsValue("3")).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .to(2)
                                    .reverse()
                                    .containsAllValues("2", "3")).isFalse();
        assertThat(
                SparseCollections.iterate(mArray).only().to(2).reverse().containsAnyValue("2", "3"))
                .isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAllValues(Arrays.asList("2", "3"))).isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .to(2)
                                    .containsAnyValue(Arrays.asList("2", "3"))).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).but().to(2).containsAnyValue(Arrays.asList(2, 3)))
                .isFalse();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAllKeys(
                                            SparseCollections.iterate(mArray).keys())).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .containsAnyKey(
                                            SparseCollections.iterate(mArray).keys())).isTrue();
    }

    public void testConversions() {

        assertThat(SparseCollections.iterate(mArray)
                                    .toLongs(new Translator<SparseArrayEntry<String>, Long>() {

                                        @Override
                                        public Long translate(
                                                final SparseArrayEntry<String> element) {

                                            return Long.decode(element.getValue());
                                        }
                                    })
                                    .reverse()).containsExactly(4L, 3L, 2L, 1L, 0L);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .key(2)
                                    .toBooleans(
                                            new Translator<SparseArrayEntry<String>, Boolean>() {

                                                @Override
                                                public Boolean translate(
                                                        final SparseArrayEntry<String> element) {

                                                    return "2".equals(element.getValue());
                                                }
                                            })).containsExactly(true);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .toIntegers(
                                            new Translator<SparseArrayEntry<String>, Integer>() {

                                                @Override
                                                public Integer translate(
                                                        final SparseArrayEntry<String> element) {

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

        assertThat(
                SparseCollections.iterate(mArray).countOf(SparseEntries.entry(1, "1"))).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray).reverse().countOf(SparseEntries.entry(1, "1")))
                .isEqualTo(1);
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(1)
                                    .countOf(SparseEntries.entry(1, "1"))).isZero();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(1)
                                    .countOf(SparseEntries.entry(1, 1))).isZero();

        SparseCollections.iterate(mArray).replaceValues(new Translator<String, String>() {

            @Override
            public String translate(final String element) {

                if ("2".equals(element)) {

                    return "1";
                }

                return element;
            }
        });
        assertThat(
                SparseCollections.iterate(mArray).countOf(SparseEntries.entry(1, "1"))).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray).values().countOf("1")).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).values().reverse().countOf("1")).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).reverse().values().countOf("1")).isEqualTo(2);
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).values().countOf("1")).isEqualTo(
                1);
        assertThat(SparseCollections.iterate(mArray)
                                    .reverse()
                                    .only()
                                    .first(2)
                                    .values()
                                    .countOf("1")).isZero();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(2)
                                    .values()
                                    .reverse()
                                    .countOf("1")).isEqualTo(1);
    }

    public void testEquals() {

        final SparseArray<String> array = new SparseArray<String>();

        for (int i = 0; i < 5; i++) {

            array.append(i, String.valueOf(i));
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

        final SparseArrayCompat<String> sparseArray =
                SparseCollections.iterate(mArray).toSparseArray();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(mArray)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(mArray)).isFalse();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sparseArray)).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).isEqualTo(sparseArray)).isFalse();

        final ArrayList<IntSparseObjectEntry<String>> list =
                SparseCollections.iterate(mArray).toImmutableList();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(list)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(list)).isFalse();

        final ArrayList<ParcelableIntSparseObjectEntry<String>> parcelableList =
                SparseCollections.iterate(mArray).toParcelableList();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(parcelableList)).isTrue();
        assertThat(SparseCollections.iterate(mArray)
                                    .only()
                                    .first(2)
                                    .isEqualTo(parcelableList)).isFalse();

        final Map<Integer, String> map = SparseCollections.iterate(mArray).toMap();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(map)).isTrue();
        assertThat(SparseCollections.iterate(mArray).only().first(2).isEqualTo(map)).isFalse();

        final SortedMap<Integer, String> sortedMap =
                SparseCollections.iterate(mArray).toSortedMap();
        assertThat(SparseCollections.iterate(mArray).isEqualTo(sortedMap)).isTrue();
        assertThat(
                SparseCollections.iterate(mArray).only().first(2).isEqualTo(sortedMap)).isFalse();

        mArray.append(7, "7");
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

        assertThat(SparseCollections.iterate(mArray).only().last(2).toMap()).contains(
                MapEntry.entry(3, "3"), MapEntry.entry(4, "4"));

        assertThat(
                SparseCollections.iterate(mArray).only().first(3).only().last(2).toMap()).contains(
                MapEntry.entry(1, "1"), MapEntry.entry(2, "2"));

        assertThat(SparseCollections.iterate(mArray).only().last(3).but().last(1).toMap()).contains(
                MapEntry.entry(2, "2"), MapEntry.entry(3, "3"));

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

        assertThat(SparseCollections.iterate(mArray).firstPositionOfValue("2")).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray).firstIndexOfValue("2")).isEqualTo(2);
        assertThat(SparseCollections.iterate(mArray)
                                    .but()
                                    .first(2)
                                    .firstPositionOfValue("2")).isZero();
        assertThat(
                SparseCollections.iterate(mArray).but().first(2).firstIndexOfValue("2")).isEqualTo(
                2);

        assertThat(SparseCollections.iterate(mArray).keys()).containsExactly(0, 1, 2, 3, 4);
        assertThat(SparseCollections.iterate(mArray).values()).containsExactly("0", "1", "2", "3",
                                                                               "4");
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
                "0", "1", "2", "3", "4");

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

        final ArrayList<IntSparseObjectEntry<String>> arrayList =
                SparseCollections.iterate(mArray).only().first(2).toImmutableList();

        assertThat(arrayList).hasSize(2);
        assertThat(arrayList.get(0).getKey()).isEqualTo(0);
        assertThat(arrayList.get(0).getValue()).isEqualTo("0");
        assertThat(arrayList.get(1).getKey()).isEqualTo(1);
        assertThat(arrayList.get(1).getValue()).isEqualTo("1");

        final IntSparseObjectEntry[] array = SparseCollections.iterate(mArray)
                                                              .only()
                                                              .key(2)
                                                              .toImmutableArray(
                                                                      IntSparseObjectEntry.class);

        assertThat(array).hasSize(1);
        assertThat(array[0].getKey()).isEqualTo(2);
        assertThat(array[0].getValue()).isEqualTo("2");
        assertThat(array).containsExactly(SparseEntries.entry(2, "2"));

        final ArrayList<IntSparseObjectEntry<String>> filledList =
                new ArrayList<IntSparseObjectEntry<String>>(2);

        SparseCollections.iterate(mArray).only().first(2).fillImmutable(filledList);

        assertThat(filledList).isEqualTo(arrayList);

        final IntSparseObjectEntry[] filledArray = new IntSparseObjectEntry[2];

        SparseCollections.iterate(mArray).only().value("2").fillImmutable(filledArray);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.entry(2, "2"));

        SparseCollections.iterate(mArray).only().value("2").fillImmutable(filledArray, 1);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.entry(2, "2"));
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

        final ArrayList<IntSparseObjectEntry<String>> forList =
                new ArrayList<IntSparseObjectEntry<String>>();

        for (final SparseArrayEntry<String> entry : SparseCollections.iterate(mArray)) {

            forList.add(entry.toImmutable());
        }

        final ArrayList<IntSparseObjectEntry<String>> forEachList =
                new ArrayList<IntSparseObjectEntry<String>>();

        SparseCollections.iterate(mArray).forEach(new Action<SparseArrayEntry<String>>() {

            @Override
            public void onNext(final SparseArrayEntry<String> element, final int count,
                    final int index) {

                forEachList.add(element.toImmutable());
            }
        });

        assertThat(forList).isEqualTo(forEachList);

        final int[] totals = new int[2];

        SparseCollections.iterate(mArray).doWhile(new Condition<SparseArrayEntry<String>>() {

            @Override
            public boolean onNext(final SparseArrayEntry<String> element, final int count,
                    final int index) {

                ++totals[0];
                totals[1] = count + 1;

                return element.getKey() != 2;
            }
        });

        assertThat(totals).containsOnly(3);

        totals[0] = 0;

        assertThat(
                SparseCollections.iterate(mArray).each(new Condition<SparseArrayEntry<String>>() {

                    @Override
                    public boolean onNext(final SparseArrayEntry<String> element, final int count,
                            final int index) {

                        ++totals[0];
                        totals[1] = count + 1;

                        return element.getKey() != 2;
                    }

                })).isFalse();

        assertThat(totals).containsOnly(3);

        totals[0] = 0;

        assertThat(SparseCollections.iterate(mArray).any(new Condition<SparseArrayEntry<String>>() {

            @Override
            public boolean onNext(final SparseArrayEntry<String> element, final int count,
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

        final Iterator<SparseArrayEntry<String>> remIterator =
                SparseCollections.iterate(mArray).iterator();

        while (remIterator.hasNext()) {

            final SparseArrayEntry<String> entry = remIterator.next();

            if ((entry.getKey() & 1) == 1) {

                remIterator.remove();
            }
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .toImmutableArray(IntSparseObjectEntry.class)).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(2, "2"),
                SparseEntries.entry(4, "4"));

        for (final SparseArrayEntry<String> entry : SparseCollections.iterate(mArray)) {

            if (entry.getKey() == 2) {

                entry.remove();
            }
        }

        assertThat(SparseCollections.iterate(mArray)
                                    .toImmutableArray(IntSparseObjectEntry.class)).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(4, "4"));

        try {

            for (final SparseArrayEntry<String> entry : SparseCollections.iterate(mArray)) {

                entry.remove();
                entry.remove();

                fail();
            }

        } catch (final NoSuchElementException e) {

        }
    }

    public void testParcelable() {

        final ParcelableIntSparseObjectEntry[] parcelableArray = SparseCollections.iterate(mArray)
                                                                                  .only()
                                                                                  .values("4", "1")
                                                                                  .toParcelableArray(
                                                                                          ParcelableIntSparseObjectEntry.class);

        assertThat(parcelableArray).hasSize(2);
        assertThat(parcelableArray[0].getKey()).isEqualTo(1);
        assertThat(parcelableArray[0].getValue()).isEqualTo("1");
        assertThat(parcelableArray[1].getKey()).isEqualTo(4);
        assertThat(parcelableArray[1].getValue()).isEqualTo("4");

        final ArrayList<ParcelableIntSparseObjectEntry<String>> parcelableList =
                SparseCollections.iterate(mArray)
                                 .but()
                                 .keys(Arrays.asList(1, 2, 3))
                                 .reverse()
                                 .toParcelableList();

        assertThat(parcelableList).hasSize(2);
        assertThat(parcelableList.get(0).getKey()).isEqualTo(4);
        assertThat(parcelableList.get(0).getValue()).isEqualTo("4");
        assertThat(parcelableList.get(1).getKey()).isEqualTo(0);
        assertThat(parcelableList.get(1).getValue()).isEqualTo("0");
        assertThat(parcelableList).containsExactly(SparseEntries.parcelableEntry(4, "4"),
                                                   SparseEntries.parcelableEntry(0, "0"));

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

        final ArrayList<ParcelableIntSparseObjectEntry<String>> filledList =
                new ArrayList<ParcelableIntSparseObjectEntry<String>>(2);

        SparseCollections.iterate(mArray).but().keys(1, 2, 3).reverse().fillParcelable(filledList);

        assertThat(filledList).isEqualTo(parcelableList);

        final ParcelableIntSparseObjectEntry[] filledArray = new ParcelableIntSparseObjectEntry[2];

        SparseCollections.iterate(mArray).only().value("2").fillParcelable(filledArray);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.parcelableEntry(2, "2"));

        SparseCollections.iterate(mArray).only().value("2").fillParcelable(filledArray, 1);

        assertThat(filledArray[0]).isEqualTo(SparseEntries.parcelableEntry(2, "2"));
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
                                    .values()).containsExactly("4", "3");
    }

    public void testRetain() throws Exception {

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
                                    .reverse()).containsExactly("0", "1", "2");
    }

    public void testTranslations() {

        final SparseArrayCompat<Integer> arrayCompat1 = SparseCollections.iterate(mArray)
                                                                         .translateValues(
                                                                                 new Translator<String, Integer>() {

                                                                                     @Override
                                                                                     public
                                                                                     Integer
                                                                                     translate(
                                                                                             final String element) {

                                                                                         return Integer
                                                                                                 .decode(element);
                                                                                     }
                                                                                 })
                                                                         .toSparseArray();

        assertThat(SparseCollections.iterate(arrayCompat1).values()).containsExactly(0, 1, 2, 3, 4);
        assertThat(SparseCollections.iterate(arrayCompat1)
                                    .replaceValues(new Translator<Integer, Integer>() {

                                        @Override
                                        public Integer translate(final Integer element) {

                                            return element + 1;
                                        }

                                    })
                                    .values()).containsExactly(1, 2, 3, 4, 5);
        assertThat(SparseCollections.iterate(arrayCompat1).values()).containsExactly(1, 2, 3, 4, 5);
        assertThat(SparseCollections.iterate(arrayCompat1).toImmutableList()).containsExactly(
                SparseEntries.entry(0, Integer.valueOf(1)),
                SparseEntries.entry(1, Integer.valueOf(2)),
                SparseEntries.entry(2, Integer.valueOf(3)),
                SparseEntries.entry(3, Integer.valueOf(4)),
                SparseEntries.entry(4, Integer.valueOf(5)));

        final SparseArrayCompat<Integer> arrayCompat2 =
                SparseCollections.iterate(mArray).translate(new IntTranslator() {

                                                                @Override
                                                                public int translate(
                                                                        final int value) {

                                                                    return value + 1;
                                                                }
                                                            },

                                                            new Translator<String, Integer>() {

                                                                @Override
                                                                public Integer translate(
                                                                        final String element) {

                                                                    return Integer.decode(element);
                                                                }
                                                            }).toSparseArray();
        assertThat(SparseCollections.iterate(arrayCompat2).toImmutableList()).containsExactly(
                SparseEntries.entry(1, Integer.valueOf(0)),
                SparseEntries.entry(2, Integer.valueOf(1)),
                SparseEntries.entry(3, Integer.valueOf(2)),
                SparseEntries.entry(4, Integer.valueOf(3)),
                SparseEntries.entry(5, Integer.valueOf(4)));
        SparseCollections.iterate(arrayCompat1).only().first(3).putInto(arrayCompat2);
        assertThat(SparseCollections.iterate(arrayCompat2).toImmutableList()).containsExactly(
                SparseEntries.entry(0, Integer.valueOf(1)),
                SparseEntries.entry(1, Integer.valueOf(2)),
                SparseEntries.entry(2, Integer.valueOf(3)),
                SparseEntries.entry(3, Integer.valueOf(2)),
                SparseEntries.entry(4, Integer.valueOf(3)),
                SparseEntries.entry(5, Integer.valueOf(4)));
        SparseCollections.iterate(arrayCompat1).only().first(3).appendTo(arrayCompat2);
        assertThat(SparseCollections.iterate(arrayCompat2).toImmutableList()).containsExactly(
                SparseEntries.entry(0, Integer.valueOf(1)),
                SparseEntries.entry(1, Integer.valueOf(2)),
                SparseEntries.entry(2, Integer.valueOf(3)),
                SparseEntries.entry(3, Integer.valueOf(2)),
                SparseEntries.entry(4, Integer.valueOf(3)),
                SparseEntries.entry(5, Integer.valueOf(4)));

        SparseCollections.iterate(arrayCompat2)
                         .only()
                         .keys(2, 3)
                         .translateValues(new Translator<Integer, String>() {

                             @Override
                             public String translate(final Integer element) {

                                 return element.toString();
                             }
                         })
                         .putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "3"), SparseEntries.entry(3, "2"),
                SparseEntries.entry(4, "4"));
        SparseCollections.iterate(arrayCompat2)
                         .only()
                         .keys(2, 3)
                         .translateValues(new Translator<Integer, String>() {

                             @Override
                             public String translate(final Integer element) {

                                 return element.toString();
                             }
                         })
                         .appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "3"), SparseEntries.entry(3, "2"),
                SparseEntries.entry(4, "4"));
        SparseCollections.iterate(arrayCompat2).only().last(1).translate(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value - 2;
            }

        }, new Translator<Integer, String>() {

            @Override
            public String translate(final Integer element) {

                return element.toString();
            }

        }).putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "3"), SparseEntries.entry(3, "4"),
                SparseEntries.entry(4, "4"));
        SparseCollections.iterate(arrayCompat2).only().last(1).translate(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value - 2;
            }

        }, new Translator<Integer, String>() {

            @Override
            public String translate(final Integer element) {

                return element.toString();
            }

        }).appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "3"), SparseEntries.entry(3, "4"),
                SparseEntries.entry(4, "4"));

        final SparseArrayCompat<String> arrayCompat3 =
                SparseCollections.iterate(mArray).toSparseArray();
        assertThat(SparseCollections.iterate(arrayCompat3).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "3"), SparseEntries.entry(3, "4"),
                SparseEntries.entry(4, "4"));

        SparseCollections.iterate(arrayCompat3).but().last(1).translateKeys(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value + 2;
            }

        }).appendTo(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "0"), SparseEntries.entry(3, "1"),
                SparseEntries.entry(4, "3"), SparseEntries.entry(5, "4"));
        SparseCollections.iterate(arrayCompat3).but().last(1).translateKeys(new IntTranslator() {

            @Override
            public int translate(final int value) {

                return value + 2;
            }

        }).putInto(mArray);
        assertThat(SparseCollections.iterate(mArray).toImmutableList()).containsExactly(
                SparseEntries.entry(0, "0"), SparseEntries.entry(1, "1"),
                SparseEntries.entry(2, "0"), SparseEntries.entry(3, "1"),
                SparseEntries.entry(4, "3"), SparseEntries.entry(5, "4"));
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        final SparseArrayCompat<String> arrayCompat = new SparseArrayCompat<String>();

        for (int i = 0; i < 5; i++) {

            arrayCompat.append(i, String.valueOf(i));
        }

        mArray = arrayCompat;
    }
}
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
package com.bmd.android.collection.filter;

import com.bmd.android.collection.translator.ToBooleanTranslator;
import com.bmd.android.collection.translator.ToIntTranslator;
import com.bmd.android.collection.translator.ToLongTranslator;
import com.bmd.android.collection.translator.Translator;
import com.bmd.android.collection.translator.Translators;

import java.util.Collection;

/**
 * Utility class providing helper methods for creating and managing {@link Filter} objects.
 * <p/>
 * Created by davide on 3/11/14.
 */
public class Filters {

    /**
     * Avoid direct instantiation.
     */
    private Filters() {

    }

    /**
     * Creates an {@link AdvancedFilter} wrapping the specified simple {@link Filter}.
     *
     * @param filter the filter to wrap.
     * @param <E>    the filtered element type.
     * @return the advanced filter.
     */
    public static <E> AdvancedFilter<E> advanced(final Filter<E> filter) {

        if (filter instanceof AdvancedFilter) {

            return (AdvancedFilter<E>) filter;
        }

        return new AdvancedFilter<E>() {

            @Override
            public void initialize(final FilterIterator<E> iterator) {

            }

            @Override
            public boolean matches(final E element, final int count, final int index) {

                return filter.matches(element, count, index);
            }
        };
    }

    /**
     * Creates a filter matching all the int values contained in the specified array.
     *
     * @param translator the translator transforming into an int value the sparse iterable
     *                   element.
     * @param values     the values to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final ToIntTranslator<E> translator,
            final int[] values) {

        return new IntArrayFilter<E>(translator, values);
    }

    /**
     * Creates a filter matching all the long values contained in the specified array.
     *
     * @param translator the translator transforming into a long value the sparse iterable element.
     * @param values     the values to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final ToLongTranslator<E> translator,
            final long[] values) {

        return new LongArrayFilter<E>(translator, values);
    }

    /**
     * Creates a filter matching all the values contained in the specified collection.
     *
     * @param translator the translator transforming into an object value the sparse iterable
     *                   element.
     * @param collection the values to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final Translator<E, ?> translator,
            final Collection<?> collection) {

        return new CollectionFilter<E>(translator, collection);
    }

    /**
     * Creates a filter matching all the values returned by the specified iterable.
     *
     * @param translator the translator transforming into an object value the sparse iterable
     *                   element.
     * @param iterable   the values to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final Translator<E, ?> translator,
            final Iterable<?> iterable) {

        return new IterableFilter<E>(translator, iterable);
    }

    /**
     * Creates a filter matching all the values contained in the specified collection.
     *
     * @param collection the values to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final Collection<?> collection) {

        final Translator<E, ?> translator = Translators.object();

        return new CollectionFilter<E>(translator, collection);
    }

    /**
     * Creates a filter matching all the values returned by the specified iterable.
     *
     * @param iterable the iterable returning the values to match.
     * @param <E>      the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> containedIn(final Iterable<?> iterable) {

        final Translator<E, ?> translator = Translators.object();

        return new IterableFilter<E>(translator, iterable);
    }

    /**
     * Creates a filter matching the first <code>count</code> elements.
     *
     * @param count the elements count.
     * @return the new filter.
     */
    public static <E> Filter<E> first(final int count) {

        return new FirstFilter<E>(count);
    }

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * collection.
     *
     * @param indexes the collection of indexes.
     * @param <E>     the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> indexes(final Collection<Integer> indexes) {

        return new IndexCollectionFilter<E>(indexes);
    }

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * iterable.
     *
     * @param indexes the index iterable.
     * @param <E>     the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> indexes(final Iterable<Integer> indexes) {

        return new IndexIterableFilter<E>(indexes);
    }

    /**
     * Creates a filter matching all the elements whose index is included in the specified index
     * array.
     *
     * @param indexes the array of indexes.
     * @param <E>     the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> indexes(final int... indexes) {

        return new IndexesFilter<E>(indexes);
    }

    /**
     * Creates a filter which is the inverse of the specified one. That is, all the originally
     * matching elements will not match anymore and viceversa.
     *
     * @param filter the original filter.
     * @param <E>    the filtered element type.
     * @return the inverted filter.
     */
    public static <E> Filter<E> inverse(final Filter<E> filter) {

        return inverse(advanced(filter));
    }

    /**
     * Creates a filter which is the inverse of the specified one. That is, all the originally
     * matching elements will not match anymore and viceversa.
     *
     * @param filter the original filter.
     * @param <E>    the filtered element type.
     * @return the inverted filter.
     */
    public static <E> AdvancedFilter<E> inverse(final AdvancedFilter<E> filter) {

        return new AdvancedFilter<E>() {

            @Override
            public void initialize(final FilterIterator<E> iterator) {

                filter.initialize(iterator);
            }

            @Override
            public boolean matches(final E element, final int count, final int index) {

                return !filter.matches(element, count, index);
            }
        };
    }

    /**
     * Creates a filter matching the last <code>count</code> elements in the iterattion.
     *
     * @param count the elements count.
     * @param <E>   the filtered element type.
     * @return the new filter.
     */
    public static <E> AdvancedFilter<E> last(final int count) {

        return new LastFilter<E>(count);
    }

    /**
     * Creates a filter from the specified {@link IntFilter} by translating the iteration elements
     * using the passed {@link com.bmd.android.collection.translator.ToIntTranslator} instance.
     *
     * @param translator the translator to int values.
     * @param filter     the int filter.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToIntTranslator<E> translator,
            final IntFilter filter) {

        return new Filter<E>() {

            @Override
            public boolean matches(final E element, final int count, final int index) {

                return filter.matches(translator.translate(element), count, index);
            }
        };
    }

    /**
     * Creates a filter from the specified {@link LongFilter} by translating the iteration elements
     * using the passed {@link com.bmd.android.collection.translator.ToLongTranslator} instance.
     *
     * @param translator the translator to long values.
     * @param filter     the long filter.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToLongTranslator<E> translator,
            final LongFilter filter) {

        return new Filter<E>() {

            @Override
            public boolean matches(final E element, final int count, final int index) {

                return filter.matches(translator.translate(element), count, index);
            }
        };
    }

    /**
     * Creates a filter from the specified {@link BooleanFilter} by translating the iteration
     * elements using the passed {@link com.bmd.android.collection.translator.ToBooleanTranslator}
     * instance.
     *
     * @param translator the translator to boolean values.
     * @param filter     the boolean filter.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToBooleanTranslator<E> translator,
            final BooleanFilter filter) {

        return new Filter<E>() {

            @Override
            public boolean matches(final E element, final int count, final int index) {

                return filter.matches(translator.translate(element), count, index);
            }
        };
    }

    /**
     * Creates a filter matching the specified value by translating the iteration elements using
     * the passed {@link com.bmd.android.collection.translator.ToIntTranslator} instance.
     *
     * @param translator the translator to int values.
     * @param value      the int value to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToIntTranslator<E> translator, final int value) {

        return new IntValueFilter<E>(translator, value);
    }

    /**
     * Creates a filter matching the specified value by translating the iteration elements using
     * the passed {@link com.bmd.android.collection.translator.ToLongTranslator} instance.
     *
     * @param translator the translator to long values.
     * @param value      the long value to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToLongTranslator<E> translator, final long value) {

        return new LongValueFilter<E>(translator, value);
    }

    /**
     * Creates a filter matching the specified value.
     *
     * @param value the value to match.
     * @param <E>   the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final Object value) {

        final Translator<E, ?> translator = Translators.object();

        if (value == null) {

            return new NullFilter<E>(translator);
        }

        return new ObjectFilter<E>(translator, value);
    }

    /**
     * Creates a filter matching the specified value by translating the iteration elements using
     * the passed {@link com.bmd.android.collection.translator.Translator} instance.
     *
     * @param translator the translator.
     * @param value      the value to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final Translator<E, ?> translator, final Object value) {

        if (value == null) {

            return new NullFilter<E>(translator);
        }

        return new ObjectFilter<E>(translator, value);
    }

    /**
     * Creates a filter matching the specified value by translating the iteration elements using
     * the passed {@link com.bmd.android.collection.translator.ToBooleanTranslator} instance.
     *
     * @param translator the translator to boolean values.
     * @param value      the boolean value to match.
     * @param <E>        the filtered element type.
     * @return the new filter.
     */
    public static <E> Filter<E> matching(final ToBooleanTranslator<E> translator,
            final boolean value) {

        return new BooleanValueFilter<E>(translator, value);
    }
}
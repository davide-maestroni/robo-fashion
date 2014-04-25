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

/**
 * Utility class providing helper methods for creating and managing
 * {@link com.bmd.android.collection.entry.SparseEntry} objects.
 * <p/>
 * Created by davide on 3/18/14.
 */
public class SparseEntries {

    /**
     * Avoid direct instantiation.
     */
    private SparseEntries() {

    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.LongSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <V> LongSparseObjectEntry<V> entry(final long key, final V value) {

        return parcelableEntry(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ObjectSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <K>   The key type.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <K, V> ObjectSparseObjectEntry<K, V> entry(final K key, final V value) {

        return parcelableEntry(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.IntSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <V> IntSparseObjectEntry<V> entry(final int key, final V value) {

        return parcelableEntry(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.IntSparseBooleanEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static IntSparseBooleanEntry entry(final int key, final boolean value) {

        return parcelableEntry(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.IntSparseIntEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static IntSparseIntEntry entry(final int key, final int value) {

        return parcelableEntry(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.IntSparseLongEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static IntSparseLongEntry entry(final int key, final long value) {

        return parcelableEntry(key, value);
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @param <K>   The key type.
     * @param <V>   The value type.
     * @return Whether the entry is equal to the passed object.
     */
    public static <K, V> boolean equal(final ObjectSparseObjectEntry<K, V> entry,
            final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof ObjectSparseObjectEntry)) {

            return false;
        }

        final ObjectSparseObjectEntry that = (ObjectSparseObjectEntry) other;

        final K key = entry.getKey();

        if ((key != null) ? !key.equals(that.getKey()) : (that.getKey() != null)) {

            return false;
        }

        final V value = entry.getValue();

        return ((value != null) ? value.equals(that.getValue()) : (that.getValue() == null));
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @param <V>   The value type.
     * @return Whether the entry is equal to the passed object.
     */
    public static <V> boolean equal(final LongSparseObjectEntry<V> entry, final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof LongSparseObjectEntry)) {

            return false;
        }

        final LongSparseObjectEntry that = (LongSparseObjectEntry) other;

        if (entry.getKey() != that.getKey()) {

            return false;
        }

        final V value = entry.getValue();

        return ((value != null) ? value.equals(that.getValue()) : (that.getValue() == null));
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @param <V>   The value type.
     * @return Whether the entry is equal to the passed object.
     */
    public static <V> boolean equal(final IntSparseObjectEntry<V> entry, final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof IntSparseObjectEntry)) {

            return false;
        }

        final IntSparseObjectEntry that = (IntSparseObjectEntry) other;

        if (entry.getKey() != that.getKey()) {

            return false;
        }

        final V value = entry.getValue();

        return ((value != null) ? value.equals(that.getValue()) : (that.getValue() == null));
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @return Whether the entry is equal to the passed object.
     */
    public static boolean equal(final IntSparseBooleanEntry entry, final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof IntSparseBooleanEntry)) {

            return false;
        }

        final IntSparseBooleanEntry that = (IntSparseBooleanEntry) other;

        return ((entry.getKey() == that.getKey()) && (entry.getValue() == that.getValue()));
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @return Whether the entry is equal to the passed object.
     */
    public static boolean equal(final IntSparseIntEntry entry, final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof IntSparseIntEntry)) {

            return false;
        }

        final IntSparseIntEntry that = (IntSparseIntEntry) other;

        return ((entry.getKey() == that.getKey()) && (entry.getValue() == that.getValue()));
    }

    /**
     * Returns <code>true</code> if the specified entry is equal to the passed object by using
     * the same rules as {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param other The object to compare.
     * @return Whether the entry is equal to the passed object.
     */
    public static boolean equal(final IntSparseLongEntry entry, final Object other) {

        if (entry == other) {

            return true;
        }

        if (entry == null) {

            return false;
        }

        if (!(other instanceof IntSparseLongEntry)) {

            return false;
        }

        final IntSparseLongEntry that = (IntSparseLongEntry) other;

        return ((entry.getKey() == that.getKey()) && (entry.getValue() == that.getValue()));
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param <K>   The key type.
     * @param <V>   The value type.
     * @return The hash code.
     */
    public static <K, V> int hashCode(final ObjectSparseObjectEntry<K, V> entry) {

        if (entry == null) {

            return 0;
        }

        final K key = entry.getKey();

        final V value = entry.getValue();

        int result = ((key != null) ? key.hashCode() : 0);

        result ^= ((value != null) ? value.hashCode() : 0);

        return result;
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param <V>   The value type.
     * @return The hash code.
     */
    public static <V> int hashCode(final LongSparseObjectEntry<V> entry) {

        if (entry == null) {

            return 0;
        }

        final long key = entry.getKey();

        final V value = entry.getValue();

        int result = (int) (key ^ (key >>> 32));

        result ^= ((value != null) ? value.hashCode() : 0);

        return result;
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @param <V>   The value type.
     * @return The hash code.
     */
    public static <V> int hashCode(final IntSparseObjectEntry<V> entry) {

        if (entry == null) {

            return 0;
        }

        final V value = entry.getValue();

        int result = entry.getKey();

        result ^= ((value != null) ? value.hashCode() : 0);

        return result;
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @return The hash code.
     */
    public static int hashCode(final IntSparseIntEntry entry) {

        if (entry == null) {

            return 0;
        }

        int result = entry.getKey();

        result ^= entry.getValue();

        return result;
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @return The hash code.
     */
    public static int hashCode(final IntSparseLongEntry entry) {

        if (entry == null) {

            return 0;
        }

        final long value = entry.getValue();

        int result = entry.getKey();

        result ^= (int) (value ^ (value >>> 32));

        return result;
    }

    /**
     * Returns the hash code of the specified entry computed by using the same rules as
     * {@link java.util.Map.Entry} objects.
     *
     * @param entry The entry.
     * @return The hash code.
     */
    public static int hashCode(final IntSparseBooleanEntry entry) {

        if (entry == null) {

            return 0;
        }

        int result = entry.getKey();

        result ^= (entry.getValue() ? 1231 : 1237);

        return result;
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableIntSparseLongEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static ParcelableIntSparseLongEntry parcelableEntry(final int key, final long value) {

        return new ParcelableIntSparseLongEntryImpl(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableIntSparseIntEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static ParcelableIntSparseIntEntry parcelableEntry(final int key, final int value) {

        return new ParcelableIntSparseIntEntryImpl(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableIntSparseBooleanEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new entry.
     */
    public static ParcelableIntSparseBooleanEntry parcelableEntry(final int key,
            final boolean value) {

        return new ParcelableIntSparseBooleanEntryImpl(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableIntSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <V> ParcelableIntSparseObjectEntry<V> parcelableEntry(final int key,
            final V value) {

        return new ParcelableIntSparseObjectEntryImpl<V>(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableObjectSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <K>   The key type.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <K, V> ParcelableObjectSparseObjectEntry<K, V> parcelableEntry(final K key,
            final V value) {

        return new ParcelableObjectSparseObjectEntryImpl<K, V>(key, value);
    }

    /**
     * Create a new {@link com.bmd.android.collection.entry.ParcelableLongSparseObjectEntry} with the
     * specified key and value.
     *
     * @param key   The key.
     * @param value The value.
     * @param <V>   The value type.
     * @return The new entry.
     */
    public static <V> ParcelableLongSparseObjectEntry<V> parcelableEntry(final long key,
            final V value) {

        return new ParcelableLongSparseObjectEntryImpl<V>(key, value);
    }
}
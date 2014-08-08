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
package com.bmd.android.collection.translator;

import com.bmd.android.collection.entry.IntSparseEntry;
import com.bmd.android.collection.entry.LongSparseEntry;
import com.bmd.android.collection.entry.ObjectSparseEntry;
import com.bmd.android.collection.entry.SparseBooleanArrayEntry;
import com.bmd.android.collection.entry.SparseIntArrayEntry;
import com.bmd.android.collection.entry.SparseLongArrayEntry;
import com.bmd.android.collection.entry.SparseObjectEntry;

/**
 * Utility class providing helper methods for creating and managing
 * {@link com.bmd.android.collection.translator.Translator} objects.
 * <p/>
 * Created by davide on 3/15/14.
 */
public class Translators {

    private static Translator<SparseBooleanArrayEntry, ?> sBooleanValueObjectTranslator;

    private static ToBooleanTranslator<SparseBooleanArrayEntry> sBooleanValueTranslator;

    private static Translator<?, ?> sElementObjectTranslator;

    private static FullBooleanTranslator sIdentityBooleanTranslator;

    private static FullIntTranslator sIdentityIntTranslator;

    private static FullLongTranslator sIdentityLongTranslator;

    private static FullTranslator<?, ?> sIdentityTranslator;

    private static Translator<IntSparseEntry, ?> sIntKeyObjectTranslator;

    private static ToIntTranslator<IntSparseEntry> sIntKeyTranslator;

    private static Translator<SparseIntArrayEntry, ?> sIntValueObjectTranslator;

    private static ToIntTranslator<SparseIntArrayEntry> sIntValueTranslator;

    private static Translator<LongSparseEntry, ?> sLongKeyObjectTranslator;

    private static ToLongTranslator<LongSparseEntry> sLongKeyTranslator;

    private static Translator<SparseLongArrayEntry, ?> sLongValueObjectTranslator;

    private static ToLongTranslator<SparseLongArrayEntry> sLongValueTranslator;

    private static Translator<ObjectSparseEntry<?>, ?> sObjectKeyTranslator;

    private static Translator<SparseObjectEntry<?>, ?> sObjectValueTranslator;

    /**
     * Avoid direct instantiation.
     */
    private Translators() {

    }

    /**
     * Returns a {@link com.bmd.android.collection.translator.FullBooleanTranslator} instance that pass
     * the specified values through the translation unchanged.
     *
     * @return The translator.
     */
    public static FullBooleanTranslator booleanIdentity() {

        if (sIdentityBooleanTranslator == null) {

            sIdentityBooleanTranslator = new FullBooleanTranslator() {

                @Override
                public boolean revert(final boolean value) {

                    return value;
                }

                @Override
                public boolean translate(final boolean value) {

                    return value;
                }
            };
        }

        return sIdentityBooleanTranslator;
    }

    /**
     * Returns a translator which extracts the boolean value from
     * {@link com.bmd.android.collection.entry.SparseBooleanArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseBooleanArrayEntry> ToBooleanTranslator<E> booleanValue() {

        if (sBooleanValueTranslator == null) {

            sBooleanValueTranslator = new ToBooleanTranslator<SparseBooleanArrayEntry>() {

                @Override
                public boolean translate(final SparseBooleanArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (ToBooleanTranslator<E>) sBooleanValueTranslator;
    }

    /**
     * Returns a translator which extracts the boolean value as an object from
     * {@link com.bmd.android.collection.entry.SparseBooleanArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseBooleanArrayEntry> Translator<E, ?> booleanValueObject() {

        if (sBooleanValueObjectTranslator == null) {

            sBooleanValueObjectTranslator = new Translator<SparseBooleanArrayEntry, Object>() {

                @Override
                public Object translate(final SparseBooleanArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sBooleanValueObjectTranslator;
    }

    /**
     * Creates an {@link com.bmd.android.collection.translator.FullBooleanTranslator} wrapping the
     * specified simple {@link com.bmd.android.collection.translator.BooleanTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator The translator to wrap.
     * @return The full translator.
     */
    public static FullBooleanTranslator full(final BooleanTranslator translator) {

        if (translator instanceof FullBooleanTranslator) {

            return (FullBooleanTranslator) translator;
        }

        return new FullBooleanTranslator() {

            @Override
            public boolean revert(final boolean value) {

                throw new UnsupportedOperationException();
            }

            @Override
            public boolean translate(final boolean value) {

                return translator.translate(value);
            }
        };
    }

    /**
     * Creates an {@link com.bmd.android.collection.translator.FullLongTranslator} wrapping the
     * specified simple {@link com.bmd.android.collection.translator.LongTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator The translator to wrap.
     * @return The full translator.
     */
    public static FullLongTranslator full(final LongTranslator translator) {

        if (translator instanceof FullLongTranslator) {

            return (FullLongTranslator) translator;
        }

        return new FullLongTranslator() {

            @Override
            public long revert(final long value) {

                throw new UnsupportedOperationException();
            }

            @Override
            public long translate(final long value) {

                return translator.translate(value);
            }
        };
    }

    /**
     * Creates an {@link com.bmd.android.collection.translator.FullIntTranslator} wrapping the
     * specified simple {@link com.bmd.android.collection.translator.IntTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator The translator to wrap.
     * @return The full translator.
     */
    public static FullIntTranslator full(final IntTranslator translator) {

        if (translator instanceof FullIntTranslator) {

            return (FullIntTranslator) translator;
        }

        return new FullIntTranslator() {

            @Override
            public int revert(final int value) {

                throw new UnsupportedOperationException();
            }

            @Override
            public int translate(final int value) {

                return translator.translate(value);
            }
        };
    }

    /**
     * Creates an {@link com.bmd.android.collection.translator.FullTranslator} wrapping the
     * specified simple {@link com.bmd.android.collection.translator.Translator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator The translator to wrap.
     * @param <I>        The input object type.
     * @param <O>        The output element type.
     * @return The full translator.
     */
    public static <I, O> FullTranslator<I, O> full(final Translator<I, O> translator) {

        if (translator instanceof FullTranslator) {

            return (FullTranslator<I, O>) translator;
        }

        return new FullTranslator<I, O>() {

            @Override
            public I revert(final O element) {

                throw new UnsupportedOperationException();
            }

            @Override
            public O translate(final I element) {

                return translator.translate(element);
            }
        };
    }

    /**
     * Returns a {@link com.bmd.android.collection.translator.FullTranslator} instance that pass
     * the specified elements through the translation unchanged.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E> FullTranslator<E, E> identity() {

        if (sIdentityTranslator == null) {

            sIdentityTranslator = new FullTranslator<Object, Object>() {

                @Override
                public Object revert(final Object element) {

                    return element;
                }

                @Override
                public Object translate(final Object element) {

                    return element;
                }
            };
        }

        //noinspection unchecked
        return (FullTranslator<E, E>) sIdentityTranslator;
    }

    /**
     * Returns a {@link com.bmd.android.collection.translator.FullIntTranslator} instance that pass
     * the specified values through the translation unchanged.
     *
     * @return The translator.
     */
    public static FullIntTranslator intIdentity() {

        if (sIdentityIntTranslator == null) {

            sIdentityIntTranslator = new FullIntTranslator() {

                @Override
                public int revert(final int value) {

                    return value;
                }

                @Override
                public int translate(final int value) {

                    return value;
                }
            };
        }

        return sIdentityIntTranslator;
    }

    /**
     * Returns a translator which extracts the int key from
     * {@link com.bmd.android.collection.entry.IntSparseEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends IntSparseEntry> ToIntTranslator<E> intKey() {

        if (sIntKeyTranslator == null) {

            sIntKeyTranslator = new ToIntTranslator<IntSparseEntry>() {

                @Override
                public int translate(final IntSparseEntry element) {

                    return element.getKey();
                }
            };
        }

        //noinspection unchecked
        return (ToIntTranslator<E>) sIntKeyTranslator;
    }

    /**
     * Returns a translator which extracts the int key as an object from
     * {@link com.bmd.android.collection.entry.IntSparseEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends IntSparseEntry> Translator<E, ?> intKeyObject() {

        if (sIntKeyObjectTranslator == null) {

            sIntKeyObjectTranslator = new Translator<IntSparseEntry, Object>() {

                @Override
                public Object translate(final IntSparseEntry element) {

                    return element.getKey();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sIntKeyObjectTranslator;
    }

    /**
     * Returns a translator which extracts the int value from
     * {@link com.bmd.android.collection.entry.SparseIntArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseIntArrayEntry> ToIntTranslator<E> intValue() {

        if (sIntValueTranslator == null) {

            sIntValueTranslator = new ToIntTranslator<SparseIntArrayEntry>() {

                @Override
                public int translate(final SparseIntArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (ToIntTranslator<E>) sIntValueTranslator;
    }

    /**
     * Returns a translator which extracts the int value as an object from
     * {@link com.bmd.android.collection.entry.SparseIntArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseIntArrayEntry> Translator<E, ?> intValueObject() {

        if (sIntValueObjectTranslator == null) {

            sIntValueObjectTranslator = new Translator<SparseIntArrayEntry, Object>() {

                @Override
                public Object translate(final SparseIntArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sIntValueObjectTranslator;
    }

    /**
     * Returns a {@link com.bmd.android.collection.translator.FullLongTranslator} instance that pass
     * the specified values through the translation unchanged.
     *
     * @return The translator.
     */
    public static FullLongTranslator longIdentity() {

        if (sIdentityLongTranslator == null) {

            sIdentityLongTranslator = new FullLongTranslator() {

                @Override
                public long revert(final long value) {

                    return value;
                }

                @Override
                public long translate(final long value) {

                    return value;
                }
            };
        }

        return sIdentityLongTranslator;
    }

    /**
     * Returns a translator which extracts the long key from
     * {@link com.bmd.android.collection.entry.LongSparseEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends LongSparseEntry> ToLongTranslator<E> longKey() {

        if (sLongKeyTranslator == null) {

            sLongKeyTranslator = new ToLongTranslator<LongSparseEntry>() {

                @Override
                public long translate(final LongSparseEntry element) {

                    return element.getKey();
                }
            };
        }

        //noinspection unchecked
        return (ToLongTranslator<E>) sLongKeyTranslator;
    }

    /**
     * Returns a translator which extracts the long key as an object from
     * {@link com.bmd.android.collection.entry.LongSparseEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends LongSparseEntry> Translator<E, ?> longKeyObject() {

        if (sLongKeyObjectTranslator == null) {

            sLongKeyObjectTranslator = new Translator<LongSparseEntry, Object>() {

                @Override
                public Object translate(final LongSparseEntry element) {

                    return element.getKey();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sLongKeyObjectTranslator;
    }

    /**
     * Returns a translator which extracts the long value from
     * {@link com.bmd.android.collection.entry.SparseLongArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseLongArrayEntry> ToLongTranslator<E> longValue() {

        if (sLongValueTranslator == null) {

            sLongValueTranslator = new ToLongTranslator<SparseLongArrayEntry>() {

                @Override
                public long translate(final SparseLongArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (ToLongTranslator<E>) sLongValueTranslator;
    }

    /**
     * Returns a translator which extracts the long value as an object from
     * {@link com.bmd.android.collection.entry.SparseLongArrayEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseLongArrayEntry> Translator<E, ?> longValueObject() {

        if (sLongValueObjectTranslator == null) {

            sLongValueObjectTranslator = new Translator<SparseLongArrayEntry, Object>() {

                @Override
                public Object translate(final SparseLongArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sLongValueObjectTranslator;
    }

    /**
     * Returns a translator which returns elements as objects.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E> Translator<E, ?> object() {

        if (sElementObjectTranslator == null) {

            sElementObjectTranslator = new Translator<ObjectSparseEntry<?>, Object>() {

                @Override
                public Object translate(final ObjectSparseEntry<?> element) {

                    return element;
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sElementObjectTranslator;
    }

    /**
     * Returns a translator which extracts the key as an object from
     * {@link com.bmd.android.collection.entry.ObjectSparseEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends ObjectSparseEntry<?>> Translator<E, ?> objectKey() {

        if (sObjectKeyTranslator == null) {

            sObjectKeyTranslator = new Translator<ObjectSparseEntry<?>, Object>() {

                @Override
                public Object translate(final ObjectSparseEntry<?> element) {

                    return element.getKey();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sObjectKeyTranslator;
    }

    /**
     * Returns a translator which extracts the value as an object from
     * {@link com.bmd.android.collection.entry.SparseObjectEntry} elements.
     *
     * @param <E> The element type.
     * @return The translator.
     */
    public static <E extends SparseObjectEntry<?>> Translator<E, ?> objectValue() {

        if (sObjectValueTranslator == null) {

            sObjectValueTranslator = new Translator<SparseObjectEntry<?>, Object>() {

                @Override
                public Object translate(final SparseObjectEntry<?> element) {

                    return element.getValue();
                }
            };
        }

        //noinspection unchecked
        return (Translator<E, ?>) sObjectValueTranslator;
    }
}
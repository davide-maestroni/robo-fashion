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
package com.gh.bmd.rfc.android.translator;

import com.gh.bmd.rfc.android.entry.IntSparseEntry;
import com.gh.bmd.rfc.android.entry.LongSparseEntry;
import com.gh.bmd.rfc.android.entry.ObjectSparseEntry;
import com.gh.bmd.rfc.android.entry.SparseBooleanArrayEntry;
import com.gh.bmd.rfc.android.entry.SparseIntArrayEntry;
import com.gh.bmd.rfc.android.entry.SparseLongArrayEntry;
import com.gh.bmd.rfc.android.entry.SparseObjectEntry;

/**
 * Utility class providing helper methods for creating and managing {@link Translator} objects.
 * <p/>
 * Created by davide on 3/15/14.
 */
public class Translators {

    private static volatile Translator<SparseBooleanArrayEntry, ?> sBooleanValueObjectTranslator;

    private static volatile ToBooleanTranslator<SparseBooleanArrayEntry> sBooleanValueTranslator;

    private static volatile Translator<?, ?> sElementObjectTranslator;

    private static volatile FullBooleanTranslator sIdentityBooleanTranslator;

    private static volatile FullIntTranslator sIdentityIntTranslator;

    private static volatile FullLongTranslator sIdentityLongTranslator;

    private static volatile FullTranslator<?, ?> sIdentityTranslator;

    private static volatile Translator<IntSparseEntry, ?> sIntKeyObjectTranslator;

    private static volatile ToIntTranslator<IntSparseEntry> sIntKeyTranslator;

    private static volatile Translator<SparseIntArrayEntry, ?> sIntValueObjectTranslator;

    private static volatile ToIntTranslator<SparseIntArrayEntry> sIntValueTranslator;

    private static volatile Translator<LongSparseEntry, ?> sLongKeyObjectTranslator;

    private static volatile ToLongTranslator<LongSparseEntry> sLongKeyTranslator;

    private static volatile Translator<SparseLongArrayEntry, ?> sLongValueObjectTranslator;

    private static volatile ToLongTranslator<SparseLongArrayEntry> sLongValueTranslator;

    private static volatile Translator<ObjectSparseEntry<?>, ?> sObjectKeyTranslator;

    private static volatile Translator<SparseObjectEntry<?>, ?> sObjectValueTranslator;

    /**
     * Avoid direct instantiation.
     */
    private Translators() {

    }

    /**
     * Returns a {@link FullBooleanTranslator} instance that pass the specified values through the
     * translation unchanged.
     *
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseBooleanArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseBooleanArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * Creates an {@link FullBooleanTranslator} wrapping the specified simple
     * {@link BooleanTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator the translator to wrap.
     * @return the full translator.
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
     * Creates an {@link FullLongTranslator} wrapping the specified simple {@link LongTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator the translator to wrap.
     * @return the full translator.
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
     * Creates an {@link FullIntTranslator} wrapping the specified simple {@link IntTranslator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator the translator to wrap.
     * @return the full translator.
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
     * Creates an {@link FullTranslator} wrapping the specified simple {@link Translator}.
     * <p/>
     * Not that the returned translator will throw an exception if the reverse translation method
     * is called.
     *
     * @param translator the translator to wrap.
     * @param <I>        the input object type.
     * @param <O>        the output element type.
     * @return the full translator.
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
     * Returns a {@link FullTranslator} instance that pass the specified elements through the
     * translation unchanged.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * Returns a {@link FullIntTranslator} instance that pass the specified values through the
     * translation unchanged.
     *
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.IntSparseEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.IntSparseEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseIntArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseIntArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * Returns a {@link FullLongTranslator} instance that pass the specified values through the
     * translation unchanged.
     *
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.LongSparseEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.LongSparseEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseLongArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseLongArrayEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.ObjectSparseEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
     * {@link com.gh.bmd.rfc.android.entry.SparseObjectEntry} elements.
     *
     * @param <E> the element type.
     * @return the translator.
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
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

import android.os.Debug;
import android.support.v4.util.SparseArrayCompat;
import android.test.AndroidTestCase;

import com.bmd.android.collection.entry.SparseArrayEntry;
import com.bmd.android.collection.iterator.SparseArrayCompatIterable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Performance test class.
 * <p/>
 * Created by davide on 3/21/14.
 */
public class PerformanceTest extends AndroidTestCase {

    private static final boolean ACTIVE_TRACING = false;

    private static final int REPEAT_COUNT = 1000;

    public void testIterationSpeed() throws IOException {

        final SparseArrayCompat<String> arrayCompat = new SparseArrayCompat<String>();

        for (int i = 0; i < 10000; i++) {

            arrayCompat.append(i, String.valueOf(i));
        }

        Writer writer;

        try {

            final File out = new File(getContext().getExternalFilesDir(null), "profile.txt");
            writer = new FileWriter(out);

        } catch (final IOException ignored) {

            writer = new StringWriter();
        }

        final String test = "test";

        long now = System.currentTimeMillis();

        if (ACTIVE_TRACING) {

            Debug.startMethodTracing("sparse");
        }

        for (int i = 0; i < REPEAT_COUNT; ++i) {

            final int length = arrayCompat.size();

            for (int j = 0; j < length; ++j) {

                if (test.equals(arrayCompat.valueAt(j))) {

                    break;
                }
            }
        }

        if (ACTIVE_TRACING) {

            Debug.stopMethodTracing();
        }

        final long sparseTime = System.currentTimeMillis() - now;

        writer.write("Sparse: " + sparseTime + "\n");

        now = System.currentTimeMillis();

        final SparseArrayCompatIterable<String> iterable = AndroidCollections.iterate(arrayCompat);

        if (ACTIVE_TRACING) {

            Debug.startMethodTracing("iterator");
        }

        for (int i = 0; i < REPEAT_COUNT; ++i) {

            for (final SparseArrayEntry<String> entry : iterable) {

                if (test.equals(entry.getValue())) {

                    break;
                }
            }
        }

        if (ACTIVE_TRACING) {

            Debug.stopMethodTracing();
        }

        final long iteratorTime = System.currentTimeMillis() - now;

        writer.write("Iterator: " + iteratorTime + "\n");

        final Map<Integer, String> map = iterable.toMap();
        final Set<Entry<Integer, String>> entrySet = map.entrySet();

        now = System.currentTimeMillis();

        if (ACTIVE_TRACING) {

            Debug.startMethodTracing("entryset");
        }

        for (int i = 0; i < REPEAT_COUNT; ++i) {

            for (final Entry<Integer, String> entry : entrySet) {

                if (test.equals(entry.getValue())) {

                    break;
                }
            }
        }

        if (ACTIVE_TRACING) {

            Debug.stopMethodTracing();
        }

        final long entrySetTime = System.currentTimeMillis() - now;

        writer.write("EntrySet: " + entrySetTime + "\n");

        assertThat(iteratorTime).isLessThan(entrySetTime);

        writer.flush();
        writer.close();
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        // Force class loading
        final SparseArrayCompat<String> arrayCompat = new SparseArrayCompat<String>();

        for (int i = 0; i < 5; i++) {

            arrayCompat.append(i, String.valueOf(i));
        }

        AndroidCollections.iterate(arrayCompat).only().from(2).but().last(2).keys().retain();
    }
}
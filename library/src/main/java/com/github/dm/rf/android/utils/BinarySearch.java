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
package com.github.dm.rf.android.utils;

/**
 * Utility class implementing a binary search algorithm.
 * <p/>
 * Created by davide-maestroni on 3/27/14.
 */
public class BinarySearch {

    /**
     * Avoid direct instantiation.
     */
    private BinarySearch() {

    }

    /**
     * Checks if the specified array contains the passed values in the first <code>count</code>
     * elements.
     * <p/>
     * Note that the elements in the array must be sorted previously to call this method, otherwise
     * unexpected results will be generated.
     *
     * @param array the array to analyze.
     * @param count the max number of elements to analyze.
     * @param value the value to search for.
     * @return whether the value has been found in the first <code>count</code> elements.
     */
    public static boolean contains(final int[] array, final int count, final int value) {

        // Re-implementing binary search since it is not available in API level 8

        int parent = count;

        int current = parent >> 1;

        int match = array[current];

        while (match != value) {

            final int next;

            if (value > match) {

                next = current + ((parent - current) >> 1);

            } else {

                next = current >> 1;
                parent = current;
            }

            if (next == current) {

                return false;
            }

            current = next;

            match = array[current];
        }

        return true;
    }
}
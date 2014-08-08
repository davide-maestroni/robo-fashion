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

/**
 * Filter matching the last <code>count</code> elements.
 * <p/>
 * Created by davide on 3/11/14.
 *
 * @param <E> the filtered element type.
 */
class LastFilter<E> implements AdvancedFilter<E> {

    private final int mCount;

    private int mMaxCount;

    public LastFilter(final int count) {

        mCount = count;
    }

    @Override
    public void initialize(final FilterIterator<E> iterator) {

        int maxCount = -1;

        while (iterator.hasNext()) {

            ++maxCount;

            iterator.next();
        }

        mMaxCount = maxCount;
    }

    @Override
    public boolean matches(final E element, final int count, final int index) {

        return ((mMaxCount - count) < mCount);
    }
}
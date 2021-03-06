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
package com.github.dm.rf.android.internal;

import java.util.NoSuchElementException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract implementation of a {@link SparseIterator} which loops the elements in the reverse
 * order.
 * <p/>
 * This class implements the common logic leaving to the subclass the handling of low level data.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <E> the element type.
 */
abstract class AbstractReverseSparseIterator<E> implements SparseIterator<E> {

    private final int mTotalCount;

    private int mCurrPosition;

    private boolean mHasCurrent;

    public AbstractReverseSparseIterator(final int totalCount) {

        mTotalCount = totalCount;
        mCurrPosition = mTotalCount;
    }

    @Override
    public boolean hasNext() {

        return (mCurrPosition > 0);
    }

    @Override
    @SuppressFBWarnings(value = "IT_NO_SUCH_ELEMENT",
                        justification = "getElementAt() can throw it")
    public E next() {

        final E next = getElementAt(--mCurrPosition);

        mHasCurrent = true;

        return next;
    }

    @Override
    public void remove() {

        if (!mHasCurrent) {

            throw new NoSuchElementException();
        }

        removeElement();

        mHasCurrent = false;
    }

    @Override
    public int originalIndex() {

        return mCurrPosition;
    }

    @Override
    public void reset() {

        mCurrPosition = mTotalCount;
        mHasCurrent = false;
    }

    /**
     * Gets the element at the specified position in the sparse collection.
     *
     * @param position the element position.
     * @return the element.
     */
    protected abstract E getElementAt(int position);

    /**
     * Removes the last element got from the sparse collection.
     *
     * @see #getElementAt(int)
     */
    protected abstract void removeElement();
}
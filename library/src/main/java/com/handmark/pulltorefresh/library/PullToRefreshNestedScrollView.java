/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
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
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class PullToRefreshNestedScrollView extends PullToRefreshBase<PullToRefreshNestedScrollView.InternalNestedScrollView> {

    public PullToRefreshNestedScrollView(Context context) {
        super(context);
    }

    public PullToRefreshNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshNestedScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshNestedScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }


    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected InternalNestedScrollView createRefreshableView(Context context, AttributeSet attrs) {
        InternalNestedScrollView scrollView = new InternalNestedScrollView(context, attrs);

        scrollView.setId(R.id.scrollview);
        return scrollView;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return mRefreshableView.getScrollY() == 0;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View scrollViewChild = mRefreshableView.getChildAt(0);
        if (null != scrollViewChild) {
            return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }
        return false;
    }

    final class InternalNestedScrollView extends NestedScrollView {

        public InternalNestedScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                       int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
            System.out.println("overScrollBy deltaX=" + deltaX + ",deltaY=" + deltaY + ",scrollX=" + scrollX + ",scrollY=" + scrollY);


            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshNestedScrollView.this, deltaX, scrollX, deltaY, scrollY,
                    getScrollRange(), isTouchEvent);

            return returnValue;
        }


        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            super.onScrollChanged(l, t, oldl, oldt);
            System.out.println("onScrollChanged l=" + l + ",t=" + t + ",oldl=" + oldl + ",oldt=" + oldt);
        }

        /**
         * Taken from the AOSP ScrollView source
         */
        private int getScrollRange() {
            int scrollRange = 0;
            if (getChildCount() > 0) {
                View child = getChildAt(0);
                scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
            }
            return scrollRange;
        }
    }
}

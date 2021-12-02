package com.oc.mareu;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.oc.mareu.ui.mareu.ListMeetingActivity;

/**
 * Instrumented test, which will execute on an Android device.
 */

public class MeetingInstrumentedTest {

    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * Check if activity AddMeetingActivity starts
     */
    @Test
    public void addMeetingActivityStarts() {

        onView(withId(R.id.add_meeting)).perform(click());
        onView(allOf(withId(R.id.activity_add), isDisplayed()));

    }



}
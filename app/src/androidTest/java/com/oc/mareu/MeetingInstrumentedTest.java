package com.oc.mareu;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.oc.mareu.ui.mareu.ListMeetingActivity;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(JUnit4.class)
public class MeetingInstrumentedTest {

    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);


    /**
     * Check if ListMeetingActivity starts(main application screen)
     */
    @Test
    public void listMeetingActivityStarts() {

        onView(allOf(withId(R.id.activity_meeting), isDisplayed()));

    }

    /**
     * Check if AddMeetingActivity starts
     */
    @Test
    public void addMeetingActivityStarts() {

        onView(withId(R.id.add_meeting)).perform(click());
        onView(allOf(withId(R.id.activity_add), isDisplayed()));

    }

    /**
     * Check if on click filter button cardView is displayed
     */
    @Test
    public void filterButtonDisplayCardView() {

        onView(withId(R.id.action_search)).perform(click());
        onView(allOf(withId(R.id.filterCardView), isDisplayed()));

    }

    /**
     *  Check ColorSpinner
     */
    @Test
    public void checkColorSpinner() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.spinner_color)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("vert")))
                .perform(click());
        onView(withId(R.id.spinner_color))
                .check(matches(withSpinnerText(containsString("vert"))));

    }

    /**
     *  Check RoomSpinner
     */
    @Test
    public void checkRoomSpinner() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.spinner_room)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Réunion A")))
                .perform(click());
        onView(withId(R.id.spinner_room))
                .check(matches(withSpinnerText(containsString("Réunion A"))));

    }


}
package com.oc.mareu;

import static android.app.PendingIntent.getActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
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
     * onClick on FloatingActionButton
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
     *  Check addMeting ColorSpinner
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
     *  Check addMeeting RoomSpinner
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

    /**
     * Check filter roomSpinner
     */
    @Test
    public void checkFilterRoomSpinner() {

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.spinner_room_filter)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Réunion A")))
                .perform(click());
        onView(withId(R.id.spinner_room_filter))
                .check(matches(withSpinnerText(containsString("Réunion A"))));

    }


    /**
     * Check if DatePicker is displayed
     * and 'Ok' button works
     */
    @Test
    public void addMeetingDatePicker() {

        onView(withId(R.id.add_meeting)).perform(click());
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.textView_date),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0), 2)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("android.widget.LinearLayout")),
                                        0),2), isDisplayed()));
        materialButton.perform(click());

    }

    /**
     * Check if TimePicker is displayed
     * and 'Ok' button works
     */
    @Test
    public void addMeetingTimePicker() {

        onView(withId(R.id.add_meeting)).perform(click());
        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.textView_hour),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),3)));
        materialTextView2.perform(scrollTo(), click());
        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("android.widget.LinearLayout")),
                                        0),2), isDisplayed()));
        materialButton2.perform(click());

    }

    /**
     * Check if DatePicker is displayed in filter
     * and ok button works
     */
    @Test
    public void filterDatePicker() {

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),1),0),isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.textView_DateFilter),
                        childAtPosition(
                                allOf(withId(R.id.hidden_view),
                                        childAtPosition(
                                                withId(R.id.filterCardView),
                                                0)),4), isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("android.widget.LinearLayout")),
                                        0),2), isDisplayed()));
        materialButton.perform(click());
    }


//    /**
//     * check toast is displayed
//     */
//    @Test
//    public void chekToastMessage() {
//
//        onView(withId(R.id.action_search)).perform(click());
//        onView(withId(R.id.roomFilterBtn)).perform(click());
//        onView(withText(R.string.toast_string)).inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
//
//    }


    /**
     * Matcher method for child position
     * @param parentMatcher
     * @param position
     * @return
     */
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }

}
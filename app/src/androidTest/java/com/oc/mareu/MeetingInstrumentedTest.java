package com.oc.mareu;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import com.oc.mareu.ui.mareu.ListMeetingActivity;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(JUnit4.class)
public class MeetingInstrumentedTest {

    private final int ITEMS_COUNT = 0;
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
     * Check if ListMeetingActivity starts(main application screen)
     */
    @Test
    public void listMeetingActivityStarts() {

        onView(allOf(withId(R.id.activity_meeting), isDisplayed()));

    }

    /**
     * Check if AddMeetingActivity starts
     * onClick on FloatingActionButton
     * check if back home arrow button works
     */
    @Test
    public void addMeetingActivityStarts() {

        onView(withId(R.id.add_meeting)).perform(click());
        onView(allOf(withId(R.id.activity_add), isDisplayed()));
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

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

    /**
     * Testing AddMeetingActivity
     * testing listView members
     * and delete button in ListMeetingActivity
     */
    @Test
    public void testAddMeetingAndDeleteButton() {

        onView(allOf(withId(R.id.activity_meeting), isDisplayed()));
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(ITEMS_COUNT)));
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting),
                        childAtPosition(
                                allOf(withId(R.id.activity_meeting),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),2),isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_color),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),0)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(Matchers.is("android.widget.PopupWindow$PopupBackgroundView")),
                        0)).atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_room),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),1)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(Matchers.is("android.widget.PopupWindow$PopupBackgroundView")),
                        0)).atPosition(1);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.textView_date),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),2)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("android.widget.LinearLayout")),
                                        0),2),isDisplayed()));
        materialButton.perform(click());

        onView(withId(R.id.textView_date)).check(matches(not(withText(""))));

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
                                        0),2),isDisplayed()));
        materialButton2.perform(click());

        onView(withId(R.id.textView_hour)).check(matches(not(withText(""))));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText_creator),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),4)));
        appCompatEditText.perform(scrollTo(), replaceText("Piotr"), closeSoftKeyboard());

        onView(ViewMatchers.withId(R.id.listView_seeMembers))
                .check(matches(hasMinimumChildCount(ITEMS_COUNT)));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText_member),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),5)));
        appCompatEditText2.perform(scrollTo(), replaceText("mag@hotmail.fr"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_add_members), withText("Add members"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),6)));
        materialButton3.perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.listView_seeMembers))
                .check(matches(hasMinimumChildCount(ITEMS_COUNT+1)));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editText_member),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),5)));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editText_member),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),5)));
        appCompatEditText4.perform(scrollTo(), replaceText("whois@whois.fr"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_add_members), withText("Add members"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),6)));
        materialButton4.perform(scrollTo(), click());


        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.button_add_meeting), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(Matchers.is("androidx.cardview.widget.CardView")),
                                        0),
                                8)));
        materialButton6.perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(ITEMS_COUNT+1)));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_meetings),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(ITEMS_COUNT)));
    }


    /**
     * Matcher method for child position
     * @param parentMatcher
     * @param position
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
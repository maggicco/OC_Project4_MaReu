package com.oc.mareu;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;

import com.oc.mareu.ui.mareu.AddMeetingActivity;
import com.oc.mareu.ui.mareu.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

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

        onView(
            allOf(withId(R.id.add_meeting),
                    withParent(allOf(withId(R.id.activity_meeting),
                            withParent(withId(android.R.id.content)))),
                    isDisplayed()));
//        onView(withId(R.id.button_add_meeting))
//                .perform(click());
//        intended(hasComponent(new ComponentName(getTargetContext(),AddMeetingActivity.class)));
//    }
//    ViewInteraction imageButton = onView(
//            allOf(withId(R.id.add_meeting),
//                    withParent(allOf(withId(R.id.activity_meeting),
//                            withParent(withId(android.R.id.content)))),
//                    isDisplayed()));
//        imageButton.check(matches(isDisplayed()));
    }

}
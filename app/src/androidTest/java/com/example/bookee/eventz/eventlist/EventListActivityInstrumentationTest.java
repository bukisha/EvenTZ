package com.example.bookee.eventz.eventlist;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.home.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.bookee.eventz.util.EspressoHelper.waitFor;

@RunWith(AndroidJUnit4.class)
public class EventListActivityInstrumentationTest {

    private static final String CATEGORY_ID = "104";

    @Rule
    public ActivityTestRule<EventListActivity> eventsRule = new ActivityTestRule<>(EventListActivity.class, true, false);

    @Before
    public void initActivity() {
        Intent intent = new Intent();
        intent.putExtra(HomeActivity.CATEGORY_ID_KEY, CATEGORY_ID);
        eventsRule.launchActivity(intent);
    }

    @Test
    public void viewsDisplayedCorrectly() throws InterruptedException {
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitFor(5000));
        onView(withId(R.id.events_recycler_list)).check(matches(isDisplayed()));
        //pausing UI thread so that i can check what is displayed as result
        onView(isRoot()).perform(waitFor(5000));
        }

}
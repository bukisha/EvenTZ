package com.example.bookee.eventz.events;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EventsListActivityInstrumentationTest {

    private static final String CATEGORY_ID = "104";
    private Intent launchIntent;
    @Rule
    public ActivityTestRule<EventsListActivity> eventsRule = new ActivityTestRule<>(EventsListActivity.class, true, false);

    @Before
    public void setUp() {
        launchIntent = new Intent();
        launchIntent.putExtra(HomeActivity.CATEGORY_ID_KEY, CATEGORY_ID);
        eventsRule.launchActivity(launchIntent);
    }

    @Test
    public void viewsDisplayedCorrectly()  {
        //When
        eventsRule.launchActivity(launchIntent);
        //Then
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.events_recycler_list)).check(matches(isDisplayed()));
        }
}
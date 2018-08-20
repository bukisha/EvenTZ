package com.example.bookee.eventz.details;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bookee.eventz.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.bookee.eventz.util.EspressoHelper.waitFor;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DetailsActivityInstrumentationTest {
    private static final String TAG = "DetailsActivityTest";
    private static final String EVENT_ID_WITHOUT_LOGO = "45097691409";
    private static final String EVENT_ID_WITH_LOGO = "10609472217";
    private static final String TEST_EVENT_NAME = "Test Event in belgrade";
    private static final String TEST_CITY_NAME = "Europe/Belgrade";
    private static final String DESCRIPTION = "test Event... test Event...test Event... test Event...test Event... test Event...";

    @Rule
    public ActivityTestRule<DetailsActivity> detailsActivityRule = new ActivityTestRule<>(DetailsActivity.class, true, false);

    @Test
    public void isEventDisplayed() {
        //Given
        Intent withLogo = new Intent();
        withLogo.putExtra(DetailsActivity.EXTRA_EVENT_ID, EVENT_ID_WITHOUT_LOGO);
        //When
        detailsActivityRule.launchActivity(withLogo);
        //Then
        onView(withId(R.id.collapsing_toolbar_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.event_logo)).check(matches(isDisplayed()));
        onView(withId(R.id.event_date)).check(matches(isDisplayed()));
        onView(withId(R.id.event_name)).check(matches(isDisplayed()));
        onView(withId(R.id.nested_scroll_view)).check(matches(isDisplayed()));
       //TODO da li se event_description vidi kad skrolujes do njega mozda treba da se napravi novi matcher pogledaj na stackOverflow-u

    }
}
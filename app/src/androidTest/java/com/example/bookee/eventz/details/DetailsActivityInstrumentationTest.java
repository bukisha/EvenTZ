package com.example.bookee.eventz.details;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.bookee.eventz.util.EspressoHelper.waitFor;
import static com.example.bookee.eventz.util.EspressoHelper.withDrawable;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class DetailsActivityInstrumentationTest {
    private static final String TAG = "DetailsActivityTest";
    private static final String EVENT_ID_WITHOUT_LOGO = "46929530489";
    private static final String EVENT_ID_WITH_LOGO = "10609472217";
    private static final String TEST_EVENT_NAME = "Test Event in belgrade";
    private static final String TEST_CITY_NAME = "Europe/Belgrade";
    private static final String DESCRIPTION = "test event... test event...test event... test event...test event... test event...";

    private RetrofitEventsRepository retrofitEventsRepository;

    @Rule
    public ActivityTestRule<DetailsActivity> detailsActivityRule = new ActivityTestRule<>(DetailsActivity.class, true, false);

    @Test
    public void isEventDisplayed() throws InterruptedException {
        Intent withLogo=new Intent();
        withLogo.putExtra(DetailsActivity.EXTRA_EVENT_ID,EVENT_ID_WITHOUT_LOGO);
        detailsActivityRule.launchActivity(withLogo);
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.collapsing_toolbar_layout)).check(matches(not(isDisplayed())));
        onView(withId(R.id.nested_scroll_view)).check(matches(not(isDisplayed())));

        onView(isRoot()).perform(waitFor(5000));

        onView(withId(R.id.event_name)).check(matches(isDisplayed()));
        onView(withId(R.id.event_date)).check(matches(isDisplayed()));
        onView(withId(R.id.event_logo)).check(matches(isDisplayed()));
        onView(withId(R.id.event_description)).check(matches(isDisplayed()));
        onView(withId(R.id.follow_button)).check(matches(isDisplayed()));
        onView(withId(R.id.follow_button)).check(matches(isClickable()));

        onView(withId(R.id.event_logo)).check(matches(withDrawable(R.drawable.party,detailsActivityRule.getActivity().getApplicationContext())));



    }
}
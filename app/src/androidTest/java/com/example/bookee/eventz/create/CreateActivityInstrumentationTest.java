package com.example.bookee.eventz.create;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.home.HomeActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CreateActivityInstrumentationTest {
    private static final String CATEGORY = "category";
    private static final String SHORT_CATEGORY_NAME = "CAT";
    @Rule
    public ActivityTestRule<CreateActivity> createActivityActivityTestRule = new ActivityTestRule<>(CreateActivity.class, true, false);
    private Intent startIntent;
    private ArrayList<Category> categories;
    private ArrayList<String> shortNames;

    @Before
    public void setUp() {
        shortNames = new ArrayList<>();
        categories = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Category c = new Category();
            c.setName(CATEGORY + String.valueOf(i));
            c.setShortName(SHORT_CATEGORY_NAME + String.valueOf(i));
            shortNames.add(SHORT_CATEGORY_NAME + String.valueOf(i));
            c.setId(String.valueOf(i + 100));
            categories.add(c);
        }
        EventApp.setGlobalCategoryIds(shortNames);
        startIntent = new Intent();
        startIntent.putExtra(HomeActivity.EXTRA_CATEGORY_LIST, categories);

    }

    @Test
    public void isGuiDisplayed() {
        //When
        createActivityActivityTestRule.launchActivity(startIntent);
        //Then
        onView(withId(R.id.spinner_category_chose)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.event_chose_category_label)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.event_name_label)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.create_event_name)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.event_description_label)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.create_event_info)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.set_date_button)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.set_time_button)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.button_set_image)).perform(scrollTo()).check(matches(isDisplayed())).check(isCompletelyBelow(withId(R.id.set_time_button)));
    }

}
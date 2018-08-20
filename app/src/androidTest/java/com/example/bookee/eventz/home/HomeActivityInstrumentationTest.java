package com.example.bookee.eventz.home;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Category;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HomeActivityInstrumentationTest {
    private static final String CATEGORY = "category";
    private static final String SHORT_CATEGORY_NAME ="CAT" ;
    private ArrayList<Category> categories;
    private ArrayList<String> shortNames;
    private Intent testLaunchIntent;
    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<>(HomeActivity.class, true, false);

    @Before
    public void setUp() {

        shortNames=new ArrayList<>();
        categories = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Category c = new Category();
            c.setName(CATEGORY + String.valueOf(i));
            shortNames.add(SHORT_CATEGORY_NAME+String.valueOf(i));
            c.setId(String.valueOf(i + 100));
            categories.add(c);
        }
        EventApp.setGlobalCategoryIds(shortNames);
        testLaunchIntent = new Intent();
        testLaunchIntent.putExtra(HomeActivity.EXTRA_CATEGORY_LIST, categories);
    }

    @Test
    public void categoriesDisplayed() {
        //Given

        //When
        homeActivityTestRule.launchActivity(testLaunchIntent);
        //Then
        onView(withId(R.id.category_recycler_list)).check(matches(isDisplayed()));
        for (int i = 0; i < categories.size(); i++) {
            onView(withText(categories.get(i).getName())).check(matches(isDisplayed()));
        }
    }

    @Test
    public void isFabDisplayed() {
        //Given

        //When
        homeActivityTestRule.launchActivity(testLaunchIntent);
        //Then
        onView(withId(R.id.floating_action_button)).check(matches(isDisplayed()));
    }

    @Test
    public void fabStartsCreateActivity() {
        //Given

        //When
        homeActivityTestRule.launchActivity(testLaunchIntent);
        onView(withId(R.id.floating_action_button)).perform(click());
        //Then
        onView(withId(R.id.spinner_category_chose)).check(matches(isDisplayed()));
    }
}
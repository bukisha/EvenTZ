package com.example.bookee.eventz.home;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Category;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeActivityInstrumentationTest {

    private static final String CATEGORY ="category" ;
    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<>(HomeActivity.class, true, false);

    @Test
    public void categoriesDisplayed() {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Category c = new Category();
            c.setName(CATEGORY + String.valueOf(i));
            c.setId(String.valueOf(i + 100));
            categories.add(c);
        }
        Intent intent = new Intent();
        intent.putExtra(HomeActivity.EXTRA_CATEGORY_LIST, categories);
        homeActivityTestRule.launchActivity(intent);
        onView(withId(R.id.category_recycler_list)).check(matches(isDisplayed()));

    }
}
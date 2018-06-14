package com.example.bookee.eventz.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public class EspressoHelper {

    public static ViewAction waitFor(final long millis) {
        return  new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
    public static Matcher<View> withDrawable(final int resourceId , final Context context) {

        return new Matcher<View>() {
            @Override
            public boolean matches(Object target) {
                ImageView targetImageView = (ImageView) target;
                Drawable expectedDrawable= ContextCompat.getDrawable(context,resourceId);
                return targetImageView.getBackground().equals(expectedDrawable);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("Error expected something else but got "+item.toString());
            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
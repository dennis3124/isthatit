package com.example.guessit.guessit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

/**
 * Created by Xiaotian on 2/10/2017.
 */

@RunWith(AndroidJUnit4.class)
public class hintButtonTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void readyButton_HintActivity() {
        // Press the ready button
        onView(withId(R.id.button_toHintGiver)).perform(click());
        // Check if a dialog is popped up
        onView(withId(R.id.button_toHintGiver)).check(matches(isEnabled()));
    }

    @Test
    public void setHintButton_HintActivity() {
        // Press the setHint button
        onView(withId(R.id.button_setHint)).perform(click());
        // Check if an input dialog is popped up
        onView(withId(R.id.button_setHint)).check(matches(isEnabled()));
    }

    @Test
    public void hintButton_HintActivity() {
        // Press the hint button
        onView(withId(R.id.button_hint)).perform(click());
        // Check if an dialog is popped up
        onView(withId(R.id.button_hint)).check(matches(isEnabled()));
    }

}

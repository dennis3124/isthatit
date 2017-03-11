package com.example.guessit.guessit;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by zhan1803 on 3/2/2017.
 */

@RunWith(AndroidJUnit4.class)
public class exitButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void exitButton_GamePage() {
        onView(withId(R.id.startgame)).perform(click());
        onView(withId(R.id.usernamefield)).perform(typeText("jjj"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.checkusername2)).perform(click());
        onView(withId(R.id.gotofaction)).perform(click());
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.button_toHintGiver)).perform(click());
        onView(withId(R.id.exitGameButton)).perform(click());
        onView(withId(R.id.exitGameButton)).check(matches(isEnabled()));
    }

    @Test
    public void exitButton_ResultPage() {
        onView(withId(R.id.startgame)).perform(click());
        onView(withId(R.id.usernamefield)).perform(typeText("jjj"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.checkusername2)).perform(click());
        onView(withId(R.id.gotofaction)).perform(click());
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.button_toHintGiver)).perform(click());
        onView(withId(R.id.testResult)).perform(click());
        onView(withId(R.id.exitGameButton2)).perform(click());
        onView(withId(R.id.exitGameButton2)).check(matches(isEnabled()));
    }

}
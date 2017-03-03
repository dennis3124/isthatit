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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by AiLi on 3/3/2017.
 */
@RunWith(AndroidJUnit4.class)
public class HintActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainact = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void onCreate() throws Exception {
        // Main activity launches first and selects join game
        onView(withId(R.id.startgame)).perform(click());
        // Types in John
        onView(withId(R.id.usernamefield)).perform(typeText("John"));
        Espresso.closeSoftKeyboard();
        // Selects check user name
        onView(withId(R.id.checkusername2)).perform(click());
        // Selects Choose a faction
        onView(withId(R.id.gotofaction)).perform(click());
        // Selects Monster
        onView(withId(R.id.button4)).perform(click());
        // Some kind of check
        //intended(toPackage("com.example.guessit.guessit/.MonsterActivity"));
        onView(withId(R.id.imageButton)).perform(click());
        onView(withId(R.id.button_setHint)).perform(click());
        onView(wtihId(R.id.))
        //onView(withId(R.id.))
    }

}
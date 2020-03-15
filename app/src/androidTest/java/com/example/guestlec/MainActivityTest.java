package com.example.guestlec;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.atvEmailLog),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText.perform(scrollTo(), replaceText("kuma"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.atvEmailLog), withText("kuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.atvEmailLog), withText("kuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.atvEmailLog), withText("kuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText4.perform(scrollTo(), replaceText("indra.kumar24710@gmail.comkuma"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.comkuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.comkuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.comkuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.comkuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.comkuma"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText9.perform(scrollTo(), replaceText("indra.kumar24710@gmail.com"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.atvEmailLog), withText("indra.kumar24710@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.atvPasswordLog),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatEditText11.perform(scrollTo(), replaceText("12345678"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSignIn), withText("Sign in"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom),
                                        0),
                                4),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.lectures),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

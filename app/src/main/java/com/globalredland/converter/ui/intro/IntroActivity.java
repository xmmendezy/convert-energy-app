package com.globalredland.converter.ui.intro;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.globalredland.converter.R;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment Slide1 = new Fragment(R.layout.fragment_welcome_slide1);
        Fragment Slide2 = new Fragment(R.layout.fragment_welcome_slide2);
        Fragment Slide3 = new Fragment(R.layout.fragment_welcome_slide3);
        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(Slide1);
        addSlide(Slide2);
        addSlide(Slide3);

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#FFFFFF"));
        setColorSkipButton(Color.parseColor("#e60b0b"));
        setColorDoneText(Color.parseColor("#e60b0b"));
        setNextArrowColor(Color.parseColor("#e60b0b"));
        setIndicatorColor(Color.parseColor("#e60b0b"),Color.parseColor("#D81B60"));
        setSeparatorColor(Color.parseColor("#e60b0b"));

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}

package com.hjianfei.museum_beacon_exhibition.view.activity.splash;

import com.hjianfei.museum_beacon_exhibition.R;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

public class SplashActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .titlePage(R.drawable.photo, "Welcome", R.color.orange_background)
                .basicPage(R.drawable.photo, "Simple to use", "Add a welcome screen to your app with only a few lines of code.", R.color.red_background)
                .parallaxPage(R.layout.parallax_example, "Easy parallax", "Supply a layout and parallax effects will automatically be applied", R.color.purple_background, 0.2f, 2f)
                .basicPage(R.drawable.photo, "Customizable", "All elements of the welcome screen can be customized easily.", R.color.blue_background)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }
}

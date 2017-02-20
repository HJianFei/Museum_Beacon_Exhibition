package com.hjianfei.museum_beacon_exhibition.view.activity.splash;

import com.hjianfei.museum_beacon_exhibition.R;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.umeng.analytics.MobclickAgent;

public class SplashActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {

        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .titlePage(R.drawable.banner1, "史博展\n一站式博物馆导览", R.color.primary)
                .basicPage(R.drawable.china_history, "中国朝代历史", "五千年悠悠岁月，留下了绵延不绝的历史传承，成就的是一首大气天成的英雄赞歌。", R.color.red_background)
                .basicPage(R.drawable.forforeign_history, "外国历史", "了解中外历史文化的发展历程。视野才会更开阔，看问题会更高远。", R.color.blue_background)
                .parallaxPage(R.layout.parallax_example, "在线博物馆", "即使游客不在博物馆也可以通过在线浏览的方式欣赏博物馆中收藏的文物。", R.color.purple_background, 0.2f, 2f)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

package com.prettyyes.user.app.ui.how;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.MyFragmentPagerAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.fragments.collect.CollectGoosFragment;
import com.prettyyes.user.app.fragments.collect.CollectKolActFragment;
import com.prettyyes.user.app.fragments.collect.CollectKolFragment;
import com.prettyyes.user.app.fragments.collect.CollectQuestionFragment;
import com.prettyyes.user.app.fragments.collect.CollectTopicFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class CollectionActivity extends BaseActivity {
    @ViewInject(R.id.tabLayout_collection)
    private TabLayout tabLayout;
    @ViewInject(R.id.vp_collection)
    private ViewPager vp;
    private String[] titles = new String[]{"问题", "专场", "好手", "话题", "商品"};
    private ArrayList<Fragment> fragments;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        setActivtytitle(R.string.mine_item_collect);
    }

    @Override
    protected void loadData() {


        fragments = new ArrayList<>();
        fragments.add(new CollectQuestionFragment());
        fragments.add(new CollectKolActFragment());
        fragments.add(new CollectKolFragment());
        fragments.add(new CollectTopicFragment());
        fragments.add(new CollectGoosFragment());

        vp.setOffscreenPageLimit(fragments.size());
        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles) {
        });
        tabLayout.setTabMode(TabLayout.MODE_FIXED | TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
    }
}

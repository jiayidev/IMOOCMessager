package net.brian.italker.push.frags.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.brian.italker.common.app.Fragment;
import net.brian.italker.push.R;
import net.brian.italker.push.activities.SearchActivity;

/**
 * 搜索群的界面实现
 */
public class SearchGroupFragment extends Fragment
implements SearchActivity.SearchFragment{


    public SearchGroupFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}

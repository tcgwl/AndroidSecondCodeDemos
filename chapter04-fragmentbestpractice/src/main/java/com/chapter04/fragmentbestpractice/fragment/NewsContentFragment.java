package com.chapter04.fragmentbestpractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chapter04.fragmentbestpractice.R;

/**
 * Created by Archer on 2017/11/2.
 */
public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_fragment, container, false);
        return view;
    }

    public void refresh(String newTitle, String newContent) {
        View newsContentContainer = view.findViewById(R.id.news_content_container);
        newsContentContainer.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(newTitle);
        newsContentText.setText(newContent);
    }
}

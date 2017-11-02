package com.chapter04.fragmentbestpractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chapter04.fragmentbestpractice.NewsContentActivity;
import com.chapter04.fragmentbestpractice.R;
import com.chapter04.fragmentbestpractice.bean.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Archer on 2017/11/2.
 */

public class NewsTitleFragment extends Fragment {
    /**
     * 是否为双页模式
     */
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);
        RecyclerView newsTitleRecycleView = (RecyclerView) view.findViewById(R.id.news_title_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecycleView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecycleView.setAdapter(adapter);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
        private List<News> mNewsList;
        public NewsAdapter(List<News> data) {
            mNewsList = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title_item, parent, false);
            final MyViewHolder holder = new MyViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    News news = mNewsList.get(position);
                    String newsTitle = news.getTitle();
                    String newsContent = news.getContent();
                    if (isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(newsTitle, newsContent);
                    } else {
                        NewsContentActivity.actionStart(getActivity(), newsTitle, newsContent);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitleText;
            public MyViewHolder(View v) {
                super(v);
                newsTitleText = (TextView) v.findViewById(R.id.news_title);
            }
        }
     }
}

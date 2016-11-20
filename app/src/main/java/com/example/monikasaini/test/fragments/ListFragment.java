package com.example.monikasaini.test.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monikasaini.test.R;
import com.example.monikasaini.test.adapters.StoryAdapter;
import com.example.monikasaini.test.data.Story;
import com.example.monikasaini.test.data.StoryItem;
import com.example.monikasaini.test.data.User;
import com.example.monikasaini.test.events.OpenDetailPageEvent;
import com.example.monikasaini.test.events.UpdateListEvent;
import com.example.monikasaini.test.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Monika on 20/11/16.
 */


public class ListFragment extends Fragment {
    private View mRootView;
    private StoryAdapter storyAdapter;
    private RecyclerView storyRecyclerView;
    private ArrayList<User> userList;
    private ArrayList<Story> storyList;
    public ArrayList<StoryItem> storyItems;
    private Context context;
    private RecyclerView.LayoutManager storyLayoutManager;
    private DetailsFragment detailsFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_list, container, false);
        storyRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        storyLayoutManager = new LinearLayoutManager(context);
        storyRecyclerView.setLayoutManager(storyLayoutManager);
        storyAdapter = new StoryAdapter(storyItems, context);
        storyRecyclerView.setAdapter(storyAdapter);
        return mRootView;

    }

    public void buildList() {

        for (int i = 0; i < storyList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (userList.get(j).getId().equals(storyList.get(i).getUserId())) {
                    StoryItem storyItem = new StoryItem(userList.get(j), storyList.get(i));
                    storyItems.add(i, storyItem);
                }
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

    }

    @Subscribe
    public void handleUpdateListEvent(UpdateListEvent event) {
        StoryItem storyItem = event.getStoryItem();
        String userId = storyItem.getUser().getId();
        for (int j = 0; j < storyItems.size(); j++) {
            if (userId.equals(storyItems.get(j).getUser().getId())) {
                storyItems.get(j).getUser().setFollowing(true);
                storyAdapter.notifyItemChanged(j);
            }

        }

    }


    @Subscribe
    public void handleOpenDetailPageEvent(OpenDetailPageEvent event) {
        int position = event.getPosition();
        StoryItem storyItem = storyItems.get(position);
        detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("storyItem", storyItem);
        detailsFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, detailsFragment, "2");
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userList = new ArrayList<User>();
        storyList = new ArrayList<Story>();
        storyItems = new ArrayList<StoryItem>();
        context = getActivity();
        String jsonString = Utils.loadJSONFromAsset(context);
        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(jsonString);
        Gson gson = new Gson();
        userList.add(0, gson.fromJson(array.get(0), User.class));
        userList.add(1, gson.fromJson(array.get(1), User.class));

        for (int i = 2; i < array.size(); i++) {
            storyList.add(gson.fromJson(array.get(i), Story.class));
        }
        buildList();

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

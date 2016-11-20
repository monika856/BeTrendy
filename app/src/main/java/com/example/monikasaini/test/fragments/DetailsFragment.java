package com.example.monikasaini.test.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monikasaini.test.R;
import com.example.monikasaini.test.data.StoryItem;
import com.example.monikasaini.test.events.UpdateListEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Monika on 20/11/16.
 */

public class DetailsFragment extends Fragment {
    private View mRootView;
    StoryItem storyItem;
    TextView userName, createdOn, title, description, likes, comments;
    ImageView storyImage, userImage;
    Button followButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_details, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null)
            storyItem = (StoryItem) bundle.getSerializable("storyItem");
        findViews();
        if (storyItem != null) {
            userName.setText(storyItem.getUser().getUserName());
            createdOn.setText(storyItem.getStory().getVerb());
            title.setText(storyItem.getStory().getTitle());
            description.setText(storyItem.getStory().getDescription());
            likes.setText(String.valueOf(storyItem.getStory().getLikesCount()) + " likes");
            comments.setText(String.valueOf(storyItem.getStory().getCommentCount()) + " comments");
            if (storyItem.getUser().isFollowing()) {
                followButton.setText(getString(R.string.following));
                followButton.setEnabled(false);
            } else {
                followButton.setText(getString(R.string.follow));
                followButton.setEnabled(true);
            }
            Picasso.with(getActivity())
                    .load(storyItem.getStory().getSi())
                    .placeholder(R.drawable.test)
                    .into(storyImage);
            Picasso.with(getActivity())
                    .load(storyItem.getUser().getImage())
                    .placeholder(R.drawable.user_name)
                    .into(userImage);
        }
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followButton.setText(getString(R.string.following));
                EventBus.getDefault().postSticky(new UpdateListEvent(storyItem));
            }
        });

        return mRootView;
    }

    private void findViews() {
        userName = (TextView) mRootView.findViewById(R.id.user_name);
        createdOn = (TextView) mRootView.findViewById(R.id.created_on);
        userImage = (ImageView) mRootView.findViewById(R.id.user_image);
        title = (TextView) mRootView.findViewById(R.id.story_title);
        description = (TextView) mRootView.findViewById(R.id.description);
        storyImage = (ImageView) mRootView.findViewById(R.id.storyImage);
        likes = (TextView) mRootView.findViewById(R.id.likes);
        comments = (TextView) mRootView.findViewById(R.id.comments);
        followButton = (Button) mRootView.findViewById(R.id.follow_Button);

    }


}

package com.example.monikasaini.test.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.monikasaini.test.R;
import com.example.monikasaini.test.data.Story;
import com.example.monikasaini.test.data.StoryItem;
import com.example.monikasaini.test.events.OpenDetailPageEvent;
import com.example.monikasaini.test.events.UpdateListEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

/**
 * Created by Monika on 20/11/16.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    List<StoryItem> list = Collections.emptyList();
    Context context;
    Picasso picasso;


    public StoryAdapter(List<StoryItem> list, Context context) {
        this.list = list;
        this.context = context;
        Picasso.Builder picassoBuilder = new Picasso.Builder(context);
        picasso = picassoBuilder.build();
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        StoryViewHolder storyViewHolder = new StoryViewHolder(v);
        return storyViewHolder;
    }

    @Override
    public void onBindViewHolder(final StoryViewHolder holder, final int position) {
        final StoryItem storyItem = list.get(position);
        holder.title.setText(storyItem.getStory().getTitle());
        holder.description.setText(storyItem.getStory().getDescription());


        picasso.load(storyItem.getStory().getSi())
                .placeholder(R.drawable.test)
                .into(holder.storyImage);


        picasso.load(storyItem.getUser().getImage())
                .placeholder(R.drawable.user_name)
                .into(holder.userImage);

        if (storyItem.getUser().isFollowing()) {
            holder.followButton.setText(context.getString(R.string.following));
            holder.followButton.setEnabled(false);
        } else {
            holder.followButton.setText(context.getString(R.string.follow));
            holder.followButton.setEnabled(true);
        }

        holder.userName.setText(storyItem.getUser().getUserName());
        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new UpdateListEvent(storyItem));


            }
        });
        holder.titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailPage(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void insert(int position, StoryItem storyItem) {
        list.add(position, storyItem);
        notifyItemInserted(position);

    }

    public void remove(Story story) {
        int position = list.indexOf(story);
        list.remove(position);
        notifyItemRemoved(position);
    }


    private void openDetailPage(int position) {
        EventBus.getDefault().post(new OpenDetailPageEvent(position));
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title, description;
        TextView userName;
        ImageView userImage;
        ImageView storyImage;
        LinearLayout titleLayout;
        Button followButton;

        StoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.titleText);
            description = (TextView) itemView.findViewById(R.id.descriptionText);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userImage = (ImageView) itemView.findViewById(R.id.user_image);
            storyImage = (ImageView) itemView.findViewById(R.id.story_image);
            titleLayout = (LinearLayout) itemView.findViewById(R.id.story_title);
            followButton = (Button) itemView.findViewById(R.id.follow_Button);

        }
    }
}

package com.example.monikasaini.test.events;

import com.example.monikasaini.test.data.StoryItem;

/**
 * Created by Monika on 20/11/16.
 */

public class UpdateListEvent {
    private StoryItem storyItem;

    public UpdateListEvent(StoryItem storyItem) {
        this.storyItem = storyItem;
    }

    public StoryItem getStoryItem() {
        return storyItem;
    }
}

package com.example.monikasaini.test.data;

import java.io.Serializable;

/**
 * Created by Monika on 20/11/16.
 */

public class StoryItem implements Serializable {
    private User user;
    private Story story;

    public StoryItem(User user, Story story) {
        this.user = user;
        this.story = story;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }


}

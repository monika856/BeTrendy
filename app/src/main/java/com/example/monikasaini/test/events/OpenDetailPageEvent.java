package com.example.monikasaini.test.events;

import com.example.monikasaini.test.data.StoryItem;

/**
 * Created by Monika on 20/11/16.
 */

public class OpenDetailPageEvent {
    private int position;
    public OpenDetailPageEvent(int position){
        this.position = position;

    }

    public int getPosition() {
        return position;
    }


}

package com.kiwi.meetapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class Event implements Parcelable {

    private String creatorName, place, date, description, name, visibility;
    private int image;

    public Event(String creatorName, String name, String place, String date, String description, String visibility) {
        this.creatorName = creatorName;
        this.name = name;
        this.place = place;
        this.date = date;
        this.description = description;
        this.visibility =  visibility;
    }

    public Event(String creatorName, String name, String description, String date, String visibility) {
        this.creatorName = creatorName;
        this.name = name;
        this.description = description;
        this.date = date;
        this.visibility = visibility;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatorName);
        dest.writeString(place);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(visibility);
        dest.writeInt(image);
    }

    public Event(Parcel in){
        creatorName = in.readString();
        place = in.readString();
        date = in.readString();
        description = in.readString();
        name = in.readString();
        visibility = in.readString();
        image = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>(){
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

}

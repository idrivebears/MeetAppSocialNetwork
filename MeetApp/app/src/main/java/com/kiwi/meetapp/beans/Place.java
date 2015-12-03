package com.kiwi.meetapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos Alexis on 17/11/2015.
 */
public class Place implements Parcelable {

    private String name, description;
    private double latitude, longitude;
    private int imageID, category;

    public Place() {
        name = "";
        description = "";
    }

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Place(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(String name, String description, double latitude, double longitude, int category) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(imageID);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(category);
    }

    public Place(Parcel in){
        name = in.readString();
        description = in.readString();
        imageID = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        category = in.readInt();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>(){
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

}

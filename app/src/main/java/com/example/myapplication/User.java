package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Parcelable 인터페이스의 구현
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(score);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        name = in.readString();
        score = in.readInt();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

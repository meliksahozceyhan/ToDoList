package com.meliksah.todolist.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem implements Parcelable {
    private String name;
    private Boolean isComplete;

    protected ToDoItem(Parcel in) {
        name = in.readString();
        isComplete = in.readByte() != 0;
    }

    public static final Creator<ToDoItem> CREATOR = new Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };

    public String getJSONString() {
        return "{\"name\":\"" + this.name + "\"," +
                "\"isComplete\":" + this.isComplete +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isComplete ? 1 : 0));
    }
}

package com.meliksah.todolist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDoList implements Parcelable {
    private String name;
    private Integer id;
    private String note;
    private String createdAt;
    private ArrayList<ToDoItem> toDoItems;

    public ToDoList(Integer id){
        this.id = id;
    }

    protected ToDoList(Parcel in) {
        id = in.readInt();
        name = in.readString();
        note = in.readString();
        createdAt = in.readString();
        toDoItems = in.readArrayList(ToDoItem.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(note);
        dest.writeString(createdAt);
        dest.writeList(toDoItems);
    }
    public String generateJSON(){
        String jsonStr = "[";
        for(ToDoItem item :toDoItems){
            jsonStr += item.getJSONString();
            jsonStr+=",";
        }
        jsonStr = jsonStr.substring(0,jsonStr.length()-1);
        jsonStr +="]";
        return jsonStr;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<ToDoList> CREATOR = new Creator<ToDoList>() {
        @Override
        public ToDoList createFromParcel(Parcel in) {
            return new ToDoList(in);
        }

        @Override
        public ToDoList[] newArray(int size) {
            return new ToDoList[size];
        }
    };
}

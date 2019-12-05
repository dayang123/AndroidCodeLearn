package com.example.administrator.myapplication.multiprogress.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class AidlMessage implements Parcelable {

    String message;


    protected AidlMessage(Parcel in) {
        message = in.readString();
    }

    public static final Creator<AidlMessage> CREATOR = new Creator<AidlMessage>() {
        @Override
        public AidlMessage createFromParcel(Parcel in) {       // 反序列化 生成对象
            return new AidlMessage(in);
        }

        @Override
        public AidlMessage[] newArray(int size) {
            return new AidlMessage[size];
        }
    };

    // 实现 parcelable接口的内容
    @Override
    public int describeContents() {
        return 0;
    }

    // 实现序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }
}

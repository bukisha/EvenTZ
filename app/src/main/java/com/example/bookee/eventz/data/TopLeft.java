
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TopLeft implements Serializable{

    @SerializedName("x")
    private Long mX;
    @SerializedName("y")
    private Long mY;

    public Long getX() {
        return mX;
    }

    public void setX(Long x) {
        mX = x;
    }

    public Long getY() {
        return mY;
    }

    public void setY(Long y) {
        mY = y;
    }

}

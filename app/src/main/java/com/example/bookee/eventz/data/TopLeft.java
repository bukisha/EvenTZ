
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TopLeft implements Serializable{

    @SerializedName("x")
    private Long x;
    @SerializedName("y")
    private Long y;

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

}

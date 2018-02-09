package com.app.emaneraky.Omrity.Model;

public class SearchDetail {
    private String name;
    private String image;
    private String detail;

    public SearchDetail() {
    }

    public SearchDetail(String name,String detail ,String image) {
        this.name = name;
        this.image = image;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

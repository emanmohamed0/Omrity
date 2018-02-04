package com.app.emaneraky.Omrity;

public class DetailOffer {
    private String image;
    private String company_name;
    private String bus;
    private String food;
    private String price;
    private String hotels;

    public DetailOffer() {
    }

    public DetailOffer(String image, String company_name, String bus, String food, String price, String hotels) {
        this.image = image;
        this.company_name = company_name;
        this.bus = bus;
        this.food = food;
        this.price = price;
        this.hotels = hotels;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHotels() {
        return hotels;
    }

    public void setHotels(String hotels) {
        this.hotels = hotels;
    }
}

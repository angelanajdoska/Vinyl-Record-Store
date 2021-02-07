package com.example.vinylrecordstore.models;

public class Orders {
    private int id;
    private String album;
    private String price;
    private int image;
    public Orders(int id, String album, String price, int image){
        this.id = id;
        this.album = album;
        this.price = price;
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
}

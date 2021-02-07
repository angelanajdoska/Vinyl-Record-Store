package com.example.vinylrecordstore.models;

public class Products {
    private int id;
    private String artist;
    private String album;
    private String price;
    private int image;
    public Products(int id, String album, String artist, String price, int image){
        this.id = id;
        this.album = album;
        this.artist = artist;
        this.price = price;
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
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

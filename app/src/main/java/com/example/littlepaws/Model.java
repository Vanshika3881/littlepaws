package com.example.littlepaws;

public class Model {
    private String imageUrl;
    private String description;

    private String address;

    private String contact;



    public Model() {
        // Default constructor required for calls to DataSnapshot.getValue(ImageModel.class)
    }

    public Model(String imageUrl, String description,String address,String contact) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.address= address;
        this.contact= contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getDescription() {
        return description;
    }
    public String getAddress(){return address;}
    public String getContact(){return contact;}
}
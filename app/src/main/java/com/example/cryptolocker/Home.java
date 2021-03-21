package com.example.cryptolocker;

public class Home {
    private int id;
    private String title,subtitle1,subtitle2;
    private int image;

    private String category;


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSubTitle1() {
        return subtitle1;
    }
    public String getSubTitle2() {
        return subtitle2;
    }
    public int getImage() {
        return image;
    }

    public String getCategory() { return category; }




    public Home(int id, String title, String subtitle1, String subtitle2, int image, String category) {
        this.id = id;
        this.title = title;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.image = image;

        this.category=category;
    }

    public Home() {
    }

}


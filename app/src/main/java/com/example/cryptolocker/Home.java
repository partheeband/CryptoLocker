package com.example.cryptolocker;

public class Home {
    private String title, subTitle1, subTitle2,category;



    public String getTitle() {
        return title;
    }
    public String getSubTitle1() {
        return subTitle1;
    }
    public String getSubTitle2() {
        return subTitle2;
    }
    public String getCategory() { return category; }


    public Home(String title, String subTitle1, String subTitle2, String category) {
        this.title = title;
        this.subTitle1 = subTitle1;
        this.subTitle2 = subTitle2;
        this.category = category;
    }

    public Home() {
    }

}


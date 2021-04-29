package com.example.cryptolocker;

import java.util.ArrayList;

public class Home {
    private String title, subTitle1, subTitle2,category;
    private ArrayList<String> decryptedData;


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

    public void decryptHome(String uid){
        decryptedData=Aes256.decrypt(title,subTitle1,subTitle2,category,uid);
        this.title=decryptedData.get(0);
        this.subTitle1=decryptedData.get(1);
        this.subTitle2=decryptedData.get(2);
        this.category=decryptedData.get(3);
    }

    public Home() {
    }

}


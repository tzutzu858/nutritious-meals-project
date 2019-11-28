package com.example.bottom;

public class menushowContact {
    private String id1;
    private String tel;
    private String eatdate;
    private String when;
    private String dish1;
    private String dish2;
    private String dish3;
    private String dish4;
    private String dish5;

    public menushowContact(String id1, String tel, String eatdate, String when, String dish1, String dish2, String dish3, String dish4, String dish5){
        this.id1=id1;
        this.tel=tel;
        this.eatdate=eatdate;
        this.when=when;
        this.dish1=dish1;
        this.dish2=dish2;
        this.dish3=dish3;
        this.dish4=dish4;
        this.dish5=dish5;
    }
    public String getId1() {
        return id1;
    }

    public String getTel() {
        return tel;
    }

    public String getEatdate() {
        return eatdate;
    }

    public String getWhen() {
        return when;
    }

    public String getDish1() {
        return dish1;
    }

    public String getDish2() {
        return dish2;
    }

    public String getDish3() {
        return dish3;
    }

    public String getDish4() {
        return dish4;
    }

    public String getDish5() {
        return dish5;
    }
}

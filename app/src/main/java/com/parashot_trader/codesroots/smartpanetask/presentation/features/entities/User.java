package com.parashot_trader.codesroots.smartpanetask.presentation.features.entities;

public class User {

  private   String name,usernam,mobile, mail,password,repassword;
   private int gender;

    public User() {
    }

    public User(String usernam, String password) {
        this.usernam = usernam;
        this.password = password;
    }

    public User(String name, String usernam, String mobile, String mail, String password, String repassword, int gender) {
        this.name = name;
        this.usernam = usernam;
        this.mobile = mobile;
        this.mail = mail;
        this.password = password;
        this.repassword = repassword;
        this.gender = gender;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsernam() {
        return usernam;
    }

    public void setUsernam(String usernam) {
        this.usernam = usernam;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}

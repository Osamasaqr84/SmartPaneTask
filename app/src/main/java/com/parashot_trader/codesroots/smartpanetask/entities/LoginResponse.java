package com.parashot_trader.codesroots.smartpanetask.entities;

public class LoginResponse {


    /**
     * success : ok
     * Id : 3048
     * UserName : osama
     * message : error
     * Image :
     */

    private String success;
    private int Id;
    private String UserName;
    private String message;
    private String Image;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
}

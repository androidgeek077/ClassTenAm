package app.infosys.threepmclassdemoapp.Models;

public class UserModel {
    String name;
    String address;
    String phonono;
    String id;
    String imgurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonono() {
        return phonono;
    }

    public void setPhonono(String phonono) {
        this.phonono = phonono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public UserModel() {
    }

    public UserModel(String name, String address, String phonono, String id, String imgurl) {
        this.name = name;
        this.address = address;
        this.phonono = phonono;
        this.id = id;
        this.imgurl = imgurl;
    }
}
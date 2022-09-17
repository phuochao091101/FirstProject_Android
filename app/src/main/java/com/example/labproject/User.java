package com.example.labproject;

public class User {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String role;
    private Boolean active=true;
    private String province;

    public User(int id, String name, String phone, String email, String role,  String Province) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.active = true;
        this.province=Province;
    }

    public User() {
    }

    @Override
    public String toString() {
        return
                "\n"+ id + "\n"+
                "name='" + name + '\'' + "\n"+
                "phone='" + phone + '\'' + "\n"+
                "email='" + email + '\'' +"\n"+
                "role='" + role + '\'' +"\n"+
                "active=" + active + "\n"+
                "province='" + province + '\'' + "\n"+ "\n";
    }

    public  String getProvince(){return  province;}
    public  void setProvince(String province){this.province=province;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

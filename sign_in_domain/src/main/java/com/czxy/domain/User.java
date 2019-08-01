package com.czxy.domain;


//用户表
public class User {

  private Integer uid; // 主键  用户id
  private String username; //用户名
  private String password; //密码
  private String phone; // 电话  唯一约束
  private String email;  // 邮箱
  private String img;  //头像
  private String remark;  //个性签名
  private String sex; // 性别


  @Override
  public String toString() {
    return "User{" +
            "uid=" + uid +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", img='" + img + '\'' +
            ", remark='" + remark + '\'' +
            ", sex='" + sex + '\'' +
            '}';
  }

  public User(Integer uid, String username, String password, String phone, String email, String img, String remark, String sex) {
    this.uid = uid;
    this.username = username;
    this.password = password;
    this.phone = phone;
    this.email = email;
    this.img = img;
    this.remark = remark;
    this.sex = sex;
  }

  public User() {
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}

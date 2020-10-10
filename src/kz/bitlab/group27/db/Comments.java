package kz.bitlab.group27.db;

import java.util.Date;

public class Comments {

    private Long id;
    private Hotels hotel;
    private Users user;
    private String comment;
    private Date addedDate;

    public Comments(Long id, Hotels hotel, Users user, String comment, Date addedDate) {
        this.id = id;
        this.hotel = hotel;
        this.user = user;
        this.comment = comment;
        this.addedDate = addedDate;
    }

    public Comments(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotels getHotel() {
        return hotel;
    }

    public void setHotel(Hotels hotel) {
        this.hotel = hotel;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}

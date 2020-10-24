package kz.bitlab.group27.db;

import java.util.Date;

public class Hotels {

    private Long id;
    private String name;
    private Users author;
    private String description;
    private int stars;
    private int price;
    private Date addedDate;
    private int likes;

    public Hotels(Long id, String name, Users author, String description, int stars, int price, Date addedDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.stars = stars;
        this.price = price;
        this.addedDate = addedDate;
    }

    public Hotels(Long id, String name, Users author, String description, int stars, int price, Date addedDate, int likes) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.stars = stars;
        this.price = price;
        this.addedDate = addedDate;
        this.likes = likes;
    }

    public Hotels(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

package com.chanaka.alevel.DB_Model;

public class BookModel {
/*    private   String  id;
    private   String  Title;
    private   String  Description;
    private   String  Genre;
    private   double  Price;
    private   String  booktImageUrk;*/


    private   String  id;
    private   String  section;
    private   String  subject;
    private   int     year;
    private   String  booktImageUrk;
    private   String  key_;


    public String getKey_() {
        return key_;
    }

    public void setKey_(String key_) {
        this.key_ = key_;
    }

    public BookModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBooktImageUrk() {
        return booktImageUrk;
    }

    public void setBooktImageUrk(String booktImageUrk) {
        this.booktImageUrk = booktImageUrk;
    }
    /*
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getBooktImageUrk() {
        return booktImageUrk;
    }

    public void setBooktImageUrk(String booktImageUrk) {
        this.booktImageUrk = booktImageUrk;
    }*/
}

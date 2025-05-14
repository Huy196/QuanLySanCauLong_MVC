package com.example.quanlysancaulong.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int image_id;

    @ManyToOne
    @JoinColumn(name = "court_id",nullable = false)
    private Court court;

    private String link;

    public Image() {
    }

    public Image(int image_id, Court court, String link) {
        this.image_id = image_id;
        this.court = court;
        this.link = link;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", court=" + court +
                ", link='" + link + '\'' +
                '}';
    }
}

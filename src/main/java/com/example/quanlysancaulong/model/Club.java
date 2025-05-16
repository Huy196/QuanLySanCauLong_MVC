package com.example.quanlysancaulong.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int club_id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String name;
    private String type;
    private String address;
    private String image;

    private String status;
    private String link_file;
    private LocalDateTime create_at;

    private String cover_image;
    private Time open_time;
    private Time close_time;

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public Club(int club_id, User user, String name, String type, String address, String image, String status, String link_file, LocalDateTime create_at, String cover_image, Time open_time, Time close_time) {
        this.club_id = club_id;
        this.user = user;
        this.name = name;
        this.type = type;
        this.address = address;
        this.image = image;
        this.status = status;
        this.link_file = link_file;
        this.create_at = create_at;
        this.cover_image = cover_image;
        this.open_time = open_time;
        this.close_time = close_time;
    }

    public Time getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Time open_time) {
        this.open_time = open_time;
    }

    public Time getClose_time() {
        return close_time;
    }

    public void setClose_time(Time close_time) {
        this.close_time = close_time;
    }

    public Club() {
    }

    public Club(int club_id, User user, String name, String type, String address, String image, String status, String link_file, LocalDateTime create_at) {
        this.club_id = club_id;
        this.user = user;
        this.name = name;
        this.type = type;
        this.address = address;
        this.image = image;
        this.status = status;
        this.link_file = link_file;
        this.create_at = create_at;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink_file() {
        return link_file;
    }

    public void setLink_file(String link_file) {
        this.link_file = link_file;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Club{" +
                "club_id=" + club_id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", link_file='" + link_file + '\'' +
                ", create_at=" + create_at +
                ", cover_image='" + cover_image + '\'' +
                ", open_time=" + open_time +
                ", close_time=" + close_time +
                '}';
    }
}

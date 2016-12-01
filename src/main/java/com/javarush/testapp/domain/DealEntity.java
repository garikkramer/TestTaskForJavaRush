package com.javarush.testapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by Asus on 30.11.2016.
 */

@Entity
@Table(name = "deal", schema = "test")
public class DealEntity implements Serializable {
    private static final long serialVersionUID = 35145646513L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "IS_DONE")
    private Boolean isDone;


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


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DealEntity that = (DealEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (isDone != null ? !isDone.equals(that.isDone) : that.isDone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isDone != null ? isDone.hashCode() : 0);
        return result;
    }
}

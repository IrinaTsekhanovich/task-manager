package webservice.model;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Boolean status;
    private Date date;

    public Task(int id, String name, String description, Boolean status, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.date = date;
    }

    public Task(){
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package webservice.model;

public class TaskOld {
    private int id;
    private String name;

    public TaskOld(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TaskOld() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

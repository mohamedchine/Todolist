package com.example.to_dolist;

import java.util.ArrayList;

public class task {
    public static ArrayList<task>  tasklist= new ArrayList<>();
    private String tasktitle;
    private String deadline;
    private String terminated;
    private String description;
    private int id;
    public task( int id,String tasktitle, String deadline, String terminated,String description) {
        this.tasktitle = tasktitle;
        this.deadline = deadline;
        this.terminated = terminated;
        this.id=id;
        this.description=description;
    }

    public static task gettaskbyid(int lidmtaatask) {
        for(task t : tasklist){
            if(t.getId()==lidmtaatask){
                return t;
            }
        }
        return null;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTerminated() {
        return terminated;
    }

    public void setTerminated(String terminated) {
        this.terminated = terminated;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

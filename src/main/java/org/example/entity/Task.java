package org.example.entity;





public class Task {
    public static final String PATH = "C:\\Users\\Admin\\Desktop\\Tasks.txt";


    private Long id;
    private String title;
    private String discription;


    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }
}

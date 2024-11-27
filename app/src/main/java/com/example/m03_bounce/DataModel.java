package com.example.m03_bounce;

// Object DB ... see Room for Android Studio
// https://developer.android.com/training/data-storage/room
public class DataModel {

    private long id;
    private String name;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private int color;

    // Default constructor
    public DataModel() {
        this.id = 0;
        this.name = "";
        this.x = 0.0f;
        this.y = 0.0f;
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.color = 0;
    }

    // Constructor with parameters
    public DataModel(long id, String name, float x, float y, float dx, float dy, int color) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    // Getters and setters for all fields

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }


    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    // toString method
    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                ", color=" + color +
                '}';
    }
}
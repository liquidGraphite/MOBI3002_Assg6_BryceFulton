package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Represents a ball in the bouncing ball simulation.
 */
public class Ball {

    private float radius = 50f;      // Ball's radius
    private float x;                 // Ball's center x-coordinate
    private float y;                 // Ball's center y-coordinate
    private float speedX;            // Ball's velocity in the x-direction
    private float speedY;            // Ball's velocity in the y-direction
    private int color;               // Ball's color

    private RectF bounds;            // Rectangle representing the ball's bounds
    private Paint paint;             // Paint object for styling the ball

    /**
     * Constructor to create a ball with specified properties.
     *
     * @param color  The color of the ball.
     * @param x      The x-coordinate of the ball's center.
     * @param y      The y-coordinate of the ball's center.
     * @param speedX The velocity of the ball in the x-direction.
     * @param speedY The velocity of the ball in the y-direction.
     */
    public Ball(int color, float x, float y, float speedX, float speedY) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);
    }

    /**
     * Moves the ball with collision detection against the box boundaries.
     *
     * @param box The bounding box within which the ball moves.
     */
    public void moveWithCollisionDetection(Box box) {
        // Update the ball's position
        x += speedX;
        y += speedY;

        // Check for collision with the left and right boundaries
        if (x + radius > box.getXMax()) {
            x = box.getXMax() - radius;
            speedX = -speedX;
        } else if (x - radius < box.getXMin()) {
            x = box.getXMin() + radius;
            speedX = -speedX;
        }

        // Check for collision with the top and bottom boundaries
        if (y + radius > box.getYMax()) {
            y = box.getYMax() - radius;
            speedY = -speedY;
        } else if (y - radius < box.getYMin()) {
            y = box.getYMin() + radius;
            speedY = -speedY;
        }
    }

    /**
     * Draws the ball on the given canvas.
     *
     * @param canvas The canvas on which to draw the ball.
     */
    public void draw(Canvas canvas) {
        // Update the bounds of the ball
        bounds.set(x - radius, y - radius, x + radius, y + radius);
        // Draw the ball as an oval (circle)
        canvas.drawOval(bounds, paint);
    }

    // Getters and setters for the ball's properties

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

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color); // Update the paint color
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
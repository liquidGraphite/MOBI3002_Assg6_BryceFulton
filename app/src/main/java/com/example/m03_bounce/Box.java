package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Represents the bounding box within which the balls move.
 */
public class Box {

    private int xMin, xMax, yMin, yMax; // Made private for encapsulation
    private Paint paint;  // Paint style and color
    private Rect bounds;  // Rectangle representing the box's bounds

    /**
     * Constructor to create a box with the specified color.
     *
     * @param color The color of the box.
     */
    public Box(int color) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
    }

    /**
     * Sets the dimensions of the box.
     *
     * @param x      The x-coordinate of the top-left corner.
     * @param y      The y-coordinate of the top-left corner.
     * @param width  The width of the box.
     * @param height The height of the box.
     */
    public void set(int x, int y, int width, int height) {
        xMin = x;
        xMax = x + width - 1;
        yMin = y;
        yMax = y + height - 1;
        // The box's bounds do not change unless the view's size changes
        bounds.set(xMin, yMin, xMax, yMax);
    }

    /**
     * Draws the box on the given canvas.
     *
     * @param canvas The canvas on which to draw the box.
     */
    public void draw(Canvas canvas) {
        canvas.drawRect(bounds, paint);
    }

    // Getter methods for the box boundaries

    /**
     * Gets the minimum x-coordinate (left boundary) of the box.
     *
     * @return The minimum x-coordinate.
     */
    public int getXMin() {
        return xMin;
    }

    /**
     * Gets the maximum x-coordinate (right boundary) of the box.
     *
     * @return The maximum x-coordinate.
     */
    public int getXMax() {
        return xMax;
    }

    /**
     * Gets the minimum y-coordinate (top boundary) of the box.
     *
     * @return The minimum y-coordinate.
     */
    public int getYMin() {
        return yMin;
    }

    /**
     * Gets the maximum y-coordinate (bottom boundary) of the box.
     *
     * @return The maximum y-coordinate.
     */
    public int getYMax() {
        return yMax;
    }
}

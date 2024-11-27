package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom View class for displaying and animating bouncing balls.
 */
public class BouncingBallView extends View {

    private List<Ball> balls; // List of Ball objects
    private Box box; // The bounding box
    private static final String TAG = "BouncingBallView";

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v(TAG, "Constructor BouncingBallView");

        // Initialize the box
        box = new Box(android.graphics.Color.BLACK); // Set the box color

        // Initialize the list of balls
        balls = new ArrayList<>();

        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();

        // To enable touch mode
        this.setFocusableInTouchMode(true);
    }

    // Method to load balls from the database
    public void loadBallsFromDatabase(List<DataModel> dataModels) {
        balls.clear();
        for (DataModel data : dataModels) {
            Ball ball = new Ball(
                    data.getColor(),
                    data.getX(),
                    data.getY(),
                    data.getDx(),
                    data.getDy()
            );
            balls.add(ball);
            Log.d(TAG, "Loaded ball from database: " + data.toString());
        }
        invalidate(); // Redraw the view
    }

    // Method to add a new ball based on user input
    public void addBall(DataModel dataModel) {
        Ball ball = new Ball(
                dataModel.getColor(),
                dataModel.getX(),
                dataModel.getY(),
                dataModel.getDx(),
                dataModel.getDy()
        );
        balls.add(ball);
        invalidate(); // Redraw the view
        Log.d(TAG, "Added new ball: " + dataModel.toString());
    }

    // Method to clear all balls from the view
    public void clearBalls() {
        balls.clear();
        invalidate(); // Redraw the view
        Log.d(TAG, "All balls cleared from the view.");
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the box
        box.draw(canvas);

        // Draw and update each ball
        for (Ball ball : balls) {
            ball.draw(canvas);  // Draw the ball
            ball.moveWithCollisionDetection(box);  // Update the ball's position
        }

        // Schedule a redraw
        invalidate();
    }

    // Called back when the view is first created or its size changes.
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the balls
        box.set(0, 0, w, h);
        Log.d(TAG, "onSizeChanged w=" + w + " h=" + h);
    }
}

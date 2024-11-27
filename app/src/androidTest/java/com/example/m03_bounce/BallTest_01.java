package com.example.m03_bounce;

import android.graphics.Color;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BallTest_01 {

    @Test
    public void testBallMovement() {
        Ball ball = new Ball(Color.RED, 5.0f, 5.0f, 1.0f, 1.0f);
        Box box = new Box(Color.BLACK);
        box.set(0, 0, 100, 100);

        // Simulate one movement
        ball.moveWithCollisionDetection(box);

        // Check if the ball moved correctly
        Boolean moveWorked = ( (ball.getX() == 6.0f) && (ball.getY() == 6.0f) );
        assertTrue("Ball did not move correctly", moveWorked);
    }
}
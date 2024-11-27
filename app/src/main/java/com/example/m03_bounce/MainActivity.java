package com.example.m03_bounce;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private BouncingBallView bbView;
    private DBClass dbClass;

    // UI elements
    private EditText editTextName;
    private EditText editTextX;
    private EditText editTextY;
    private EditText editTextDX;
    private EditText editTextDY;
    private Spinner spinnerColor;
    private Button buttonAdd;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure your layout includes the necessary input fields

        // Initialize database helper
        dbClass = new DBClass(this);

        // Get the view object to reference it later
        bbView = findViewById(R.id.custView);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextX = findViewById(R.id.editTextX);
        editTextY = findViewById(R.id.editTextY);
        editTextDX = findViewById(R.id.editTextDX);
        editTextDY = findViewById(R.id.editTextDY);
        spinnerColor = findViewById(R.id.spinnerColor);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonClear = findViewById(R.id.buttonClear);

        // Load balls from the database when the app starts
        bbView.loadBallsFromDatabase(dbClass.findAll());

        // Set up the "Add" button click listener
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClick();
            }
        });

        // Set up the "Clear" button click listener
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearButtonClick();
            }
        });
    }

    // Method to handle "Add" button click
    public void onAddButtonClick() {
        // Retrieve input data
        String name = editTextName.getText().toString().trim();
        String xText = editTextX.getText().toString().trim();
        String yText = editTextY.getText().toString().trim();
        String dxText = editTextDX.getText().toString().trim();
        String dyText = editTextDY.getText().toString().trim();
        String colorName = spinnerColor.getSelectedItem().toString();

        // Validate inputs
        if (name.isEmpty() || xText.isEmpty() || yText.isEmpty() ||
                dxText.isEmpty() || dyText.isEmpty()) {
            // Show an error message (you can use a Toast)
            Log.e("MainActivity", "Please fill in all fields.");
            return;
        }

        try {
            float x = Float.parseFloat(xText);
            float y = Float.parseFloat(yText);
            float dx = Float.parseFloat(dxText);
            float dy = Float.parseFloat(dyText);
            int color = getColorFromName(colorName);

            // Create a new DataModel object
            DataModel dataModel = new DataModel(0, name, x, y, dx, dy, color);

            // Save to database
            dbClass.save(dataModel);

            // Add the ball to the view
            bbView.addBall(dataModel);

            // Clear input fields
            editTextName.setText("");
            editTextX.setText("");
            editTextY.setText("");
            editTextDX.setText("");
            editTextDY.setText("");
            spinnerColor.setSelection(0);

            Log.d("MainActivity", "Ball added: " + dataModel.toString());

        } catch (NumberFormatException e) {
            Log.e("MainActivity", "Invalid input: " + e.getMessage());
        }
    }

    // Method to handle "Clear" button click
    public void onClearButtonClick() {
        // Clear the database
        dbClass.clearAll();

        // Clear balls from the view
        bbView.clearBalls();

        Log.d("MainActivity", "All balls cleared from database and view.");
    }

    // Helper method to get color integer from color name
    private int getColorFromName(String colorName) {
        int color;
        switch (colorName.toLowerCase()) {
            case "red":
                color = android.graphics.Color.RED;
                break;
            case "green":
                color = android.graphics.Color.GREEN;
                break;
            case "blue":
                color = android.graphics.Color.BLUE;
                break;
            case "yellow":
                color = android.graphics.Color.YELLOW;
                break;
            case "cyan":
                color = android.graphics.Color.CYAN;
                break;
            case "magenta":
                color = android.graphics.Color.MAGENTA;
                break;
            default:
                color = android.graphics.Color.WHITE;
                break;
        }
        return color;
    }
}
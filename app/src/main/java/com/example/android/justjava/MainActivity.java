package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //displayPrice(numberOfCoffees * price);
        String priceMessage = "Total: $"+numberOfCoffees*price+"\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     *  This method increment numberOfCoffees value and it is called when + button is clicked
     */
    public void increment(View view) {
        numberOfCoffees++;
        findViewById(R.id.decrement_button).setEnabled(true);
        if (numberOfCoffees > 99) findViewById(R.id.increment_button).setEnabled(false);
        display();
    }

    /**
     *  This method decrement numberOfCoffees value and it is called when - button is clicked
     */
    public void decrement(View view) {
        numberOfCoffees--;
        findViewById(R.id.increment_button).setEnabled(true);
        if (numberOfCoffees < 1) findViewById(R.id.decrement_button).setEnabled(false);
        display();
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen in the price place.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
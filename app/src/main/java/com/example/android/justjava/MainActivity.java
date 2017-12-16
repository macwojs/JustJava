package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantityOfCoffees = 2;
    int priceOfCoffee = 5;
    String name = "Kapitan Kunal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *  This method increment numberOfCoffees value and it is called when + button is clicked
     */
    public void increment(View view) {
        quantityOfCoffees++;
        findViewById(R.id.decrement_button).setEnabled(true);
        if (quantityOfCoffees > 99) findViewById(R.id.increment_button).setEnabled(false);
        displayQuantity();
    }

    /**
     *  This method decrement numberOfCoffees value and it is called when - button is clicked
     */
    public void decrement(View view) {
        quantityOfCoffees--;
        findViewById(R.id.increment_button).setEnabled(true);
        if (quantityOfCoffees < 1) findViewById(R.id.decrement_button).setEnabled(false);
        displayQuantity();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(quantityOfCoffees, priceOfCoffee);
        String priceMessage = createOrderSummary(price, name);
        displaySubmit(priceMessage);
    }

    /**
     * This method calculate price of order
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        return quantity * pricePerCup;
    }

    /**
     * This method create summary
     * @param price of our coffee
     * @param name of customer
     * @return summary message
     */
    private String createOrderSummary (int price, String name) {
        return "Name: "+name+"\n"+
                "Quantity: "+quantityOfCoffees+"\n"+
                "Total: "+price+"$"+"\n"+
                "Thank you!";
    }


    /**
     * This method displays the given quantity value on the screen.
     * We modified quantity_text_view form activity_main.
     */
    private void displayQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        String quantity = ""+quantityOfCoffees;
        quantityTextView.setText(quantity);
    }

    /**
     * This method displays the given text on the screen in the price place.
     * We modified price_text_view form activity_main.
     */
    private void displaySubmit(String message) {
        TextView priceTextView = findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}
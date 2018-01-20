package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantityOfCoffees = 2;
    int priceOfCoffee = 5;
    boolean checkBoxCream = false;
    boolean checkBoxChoco = false;
    String name = "";

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
        findViewById(R.id.order_button).setEnabled(true);
        if (quantityOfCoffees > 99) {
            findViewById(R.id.increment_button).setEnabled(false);
            Toast.makeText(this, R.string.toomuch, Toast.LENGTH_SHORT).show();
        }
        displayQuantity();
    }

    /**
     *  This method decrement numberOfCoffees value and it is called when - button is clicked
     */
    public void decrement(View view) {
        quantityOfCoffees--;
        findViewById(R.id.increment_button).setEnabled(true);
        if (quantityOfCoffees < 1) {
            findViewById(R.id.decrement_button).setEnabled(false);
            findViewById(R.id.order_button).setEnabled(false);
            Toast.makeText(this, R.string.tooless, Toast.LENGTH_SHORT).show();
        }
        displayQuantity();
    }

    /**
     * This method is called when the order button is clicked.
     */
    private String submitOrder(){
        CheckBox creamCheckBox = findViewById(R.id.cream_checkbox);
        checkBoxCream = creamCheckBox.isChecked();
        CheckBox chocoCheckBox = findViewById(R.id.choco_checkbox);
        checkBoxChoco = chocoCheckBox.isChecked();

        EditText nameInput = findViewById(R.id.name_input);
        name = nameInput.getText().toString();

        priceOfCoffee = 5;
        int price = calculatePrice(quantityOfCoffees, priceOfCoffee);
        String submitMessage = createOrderSummary(price, name);

        return submitMessage;
    }

    /**
     * This method calculate price of order
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        if (checkBoxCream){
            pricePerCup = pricePerCup + 1;
        } else pricePerCup = 5;

        if (checkBoxChoco){
            pricePerCup = pricePerCup + 2;
        }
        return quantity * pricePerCup;
    }

    /**
     * This method create summary
     * @param price of our coffee
     * @param name of customer
     * @return summary message
     */
    private String createOrderSummary (int price, String name) {
        return getString(R.string.name) +name+"\n"+
                getString(R.string.add_cream)+" "+checkBoxCream+"\n"+
                getString(R.string.add_choco)+" "+checkBoxChoco+"\n"+
                getString(R.string.quantity)+quantityOfCoffees+"\n"+
                getString(R.string.total)+price+"$"+"\n"+
                getString(R.string.thanks);
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

    public void orderNow(View view) {
        String submit = submitOrder();
        displaySubmit(submit);
    }

    public void orderMail(View view) {
        String submit = submitOrder();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.order);
        intent.putExtra(Intent.EXTRA_TEXT, submit);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}
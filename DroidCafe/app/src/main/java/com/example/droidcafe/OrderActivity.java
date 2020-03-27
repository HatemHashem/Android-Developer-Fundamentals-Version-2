package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.droidcafe.MainActivity.EXTRA_MESSAGE;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView receivedTextView;
    private RadioButton nexdayRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        receivedTextView = findViewById(R.id.received_message_textView);
        nexdayRadioButton=findViewById(R.id.next_day);
        nexdayRadioButton.setChecked(true);
        Intent intent = getIntent();
        if (intent.getStringExtra(EXTRA_MESSAGE) != null) {
            receivedTextView.setText(intent.getStringExtra(EXTRA_MESSAGE));
        } else {
            receivedTextView.setText(R.string.no_order);
        }
        Spinner spinner=findViewById(R.id.label_spinner);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.sameday:
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service));
                }
                break;
            case R.id.pickup:
                if (checked) {
                    displayToast(getString(R.string.pick_up));
                }
                break;
            case R.id.next_day:
                if (checked) {
                    displayToast(getString(R.string.next_day_ground_delivery));
                }
                break;
            default:
                //
                break;

        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

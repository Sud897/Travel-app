package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookLadakhActivity extends AppCompatActivity {
    Button btnGangtok;
    Calendar myCalender;
    String duration,packageId,packageCost,extraInclusion,picUp,dropOff,departureDate,endDate;
    String []passenger=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    AutoCompleteTextView travellers;
    EditText departureInput;
    TextInputLayout departureLayout,travellersLayout;
    String passengerCount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ladakh);
        travellers=findViewById(R.id.passenger);
        travellersLayout=findViewById(R.id.travellersLayout);
        departureInput=findViewById(R.id.departure_input);
        departureLayout=findViewById(R.id.departure_layout);

        btnGangtok=findViewById(R.id.book_gangtok);
        myCalender=Calendar.getInstance();

        ArrayAdapter<String> passengerAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,passenger);
        travellers.setAdapter(passengerAdapter);   //display the data in dropdown list
        travellers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                passengerCount = (String)parent.getItemAtPosition(position);
                Log.e("on_traveller_select", "onItemSelected: "+passengerCount);
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH,month);
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();

            }
        };
        departureInput.setOnClickListener(v -> {
            new DatePickerDialog(this,date,myCalender
                    .get(Calendar.YEAR),myCalender
                    .get(Calendar.MONTH),myCalender
                    .get(Calendar.DAY_OF_MONTH)).show();
        });

    duration="13";
    packageId="TRAVELMANIA001LADAKH01";
    packageCost="15999";
    extraInclusion=null;
    picUp="Delhi";
    dropOff="Delhi";
        btnGangtok.setOnClickListener(v -> {
        departureLayout.setError("");
        if (validateField(departureDate,passengerCount)) {
            travellersLayout.setError("");
            departureLayout.setError("");
            Intent intent = new Intent(this, PaymentGateWayActivity.class);
            intent.putExtra("DURATION", duration);
            intent.putExtra("PACKAGE_ID", packageId);
            intent.putExtra("PACKAGE_COST", packageCost);
            intent.putExtra("EXTRA_INCLUSION", extraInclusion);
            intent.putExtra("PICK_UP", picUp);
            intent.putExtra("DROP_OFF", dropOff);
            intent.putExtra("DEPARTURE_DATE", departureDate);
            intent.putExtra("END_DATE", endDate);
            if(passengerCount=="") passengerCount="0";
            intent.putExtra("PASSENGER_COUNT", passengerCount);
            startActivity(intent);
            Toast.makeText(this, "Make payment", Toast.LENGTH_SHORT).show();
        }
    });
}

    private boolean validateField(String departureDate, String passengerCount) {
        if(departureDate.isEmpty())
            departureLayout.setError("Required Field");
        else if(passengerCount.isEmpty())
            travellersLayout.setError("Required Field");
        else
            return true;
        return false;
    }


    private void updateLabel() {
        String myFormat="dd MMM,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        departureInput.setText(sdf.format(myCalender.getTime()));

        departureDate=departureInput.getText().toString();
        myCalender.add(Calendar.DATE, 7);
        endDate= sdf.format(myCalender.getTime());//myCalender.getTime().toString();
        Log.e("get_cal_date", "onCreate: departureDate"+departureDate+" \n endDate "+endDate );
    }
}
package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExploringActivity extends AppCompatActivity {
    TextView durationView,costView,pickUpView,dropOffView,specialInclusion;
    ImageView imgView;
    Button btnBook;

    Calendar myCalender;
    String departureDate,endDate;
    String []passenger=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    AutoCompleteTextView travellers;
    EditText departureInput;
    TextInputLayout departureLayout,travellersLayout;
    String passengerCount="";
    int imageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploring);
        durationView=findViewById(R.id.duration);
        costView=findViewById(R.id.cost);
        pickUpView=findViewById(R.id.pickUp);
        dropOffView=findViewById(R.id.dropOff);
        imgView=findViewById(R.id.packageImg);
        specialInclusion=findViewById(R.id.inclusion);

        btnBook=findViewById(R.id.bookPackage);

        travellers=findViewById(R.id.passenger);
        travellersLayout=findViewById(R.id.travellersLayout);
        departureInput=findViewById(R.id.departure_input);
        departureLayout=findViewById(R.id.departure_layout);

        myCalender= Calendar.getInstance();
        Bundle bundle = getIntent().getExtras();
        //Resources res = getResources();

        String duration=getIntent().getStringExtra("DURATION");
        String durationTxt=getIntent().getStringExtra("DURATION_TXT");
        String packageCost=getIntent().getStringExtra("PACKAGE_COST");
        String packageCostTxt=getIntent().getStringExtra("PACKAGE_COST_TXT");
        String pickUp=getIntent().getStringExtra("PICK_UP");
        String dropOff=getIntent().getStringExtra("DROP_OFF");
        String inclusion=getIntent().getStringExtra("SPECIAL_INCLUSION");
        String packageID=getIntent().getStringExtra("PACKAGE_ID");
        if (bundle != null) {
            imageValue = bundle.getInt("IMAGEVIEW");
        }
        imgView.setImageResource(imageValue);
        //String packageImage=getIntent().getStringExtra("IMAGEVIEW");
        //Bitmap packageImage = (Bitmap) getIntent().getParcelableExtra("IMAGEVIEW");
        //int image=Integer.parseInt(packageImage);

        durationView.setText(durationTxt);
        costView.setText(packageCostTxt);
        pickUpView.setText(pickUp);
        dropOffView.setText(dropOff);
        dropOffView.setText(dropOff);
        specialInclusion.setText(inclusion);


        //imgView.setImageBitmap(packageImage);
        //imgView.setImageDrawable(res.getDrawable(image));

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

        btnBook.setOnClickListener(v -> {
            departureLayout.setError("");
            if (validateField(departureDate,passengerCount)) {
                travellersLayout.setError("");
                departureLayout.setError("");
                Intent intent = new Intent(this, PaymentGateWayActivity.class);
                intent.putExtra("DURATION", duration);
                intent.putExtra("PACKAGE_ID", packageID);
                intent.putExtra("PACKAGE_COST", packageCost);
                intent.putExtra("EXTRA_INCLUSION", inclusion);
                intent.putExtra("PICK_UP", pickUp);
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
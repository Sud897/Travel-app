package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmania.common.AppConstant;
import com.example.travelmania.ui.MyTripFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentGateWayActivity extends AppCompatActivity {
    private Button btnPayNow;
    TextView package_id,package_cost,travellers,cost,service_tax,total_cost,startDepDate,endDepDate;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    String uid,bid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gate_way);

        btnPayNow=findViewById(R.id.btnPayment);

        package_cost=findViewById(R.id.packageCost);
        package_id=findViewById(R.id.packageId);
        travellers=findViewById(R.id.travellers_num);
        cost=findViewById(R.id.cost);
        service_tax=findViewById(R.id.packageTax);
        total_cost=findViewById(R.id.totalCost);
        startDepDate=findViewById(R.id.startDepDate);
        endDepDate=findViewById(R.id.endDepDate);
        auth=FirebaseAuth.getInstance();
        uid=auth.getUid();
        fireStore=FirebaseFirestore.getInstance();


        String passengerList=getIntent().getStringExtra("PASSENGER_COUNT");
        String duration=getIntent().getStringExtra("DURATION");
        String packageId=getIntent().getStringExtra("PACKAGE_ID");
        String packageCost=getIntent().getStringExtra("PACKAGE_COST");
        String extraInclusion=getIntent().getStringExtra("EXTRA_INCLUSION");
        String pickUp=getIntent().getStringExtra("PICK_UP");
        String dropOff=getIntent().getStringExtra("DROP_OFF");
        String departureDate=getIntent().getStringExtra("DEPARTURE_DATE");
        String endDate=getIntent().getStringExtra("END_DATE");


        int pCost=Integer.parseInt(packageCost);
        int traveller_no=Integer.parseInt(passengerList);
        int total=(pCost*traveller_no);
        int serviceTax=(total*12)/100;
        int totalCost=total+serviceTax;

        package_id.setText(packageId);
        package_cost.setText("₹"+packageCost);
        travellers.setText(passengerList);
        cost.setText("₹"+String.valueOf(total));
        service_tax.setText("₹"+String.valueOf(serviceTax));
        total_cost.setText("₹"+String.valueOf(totalCost));
        startDepDate.setText(departureDate);
        endDepDate.setText(endDate);

        btnPayNow.setOnClickListener(v -> {
            bid=getBookingId(20);
            createBookingProfile(bid,uid,packageId,packageCost,passengerList,totalCost,extraInclusion,duration,pickUp,dropOff,departureDate,endDate);
        });

    }

    private String getBookingId(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index= (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    private void createBookingProfile(String bid,String uid, String packageId, String packageCost, String passengerList, int totalCost, String extraInclusion, String duration, String pickUp, String dropOff,String startDepDate,String endDepDate) {
        Map<String,Object> bookMap=new HashMap<>();          //Collection framework
        bookMap.put(AppConstant.USER_ID,uid);
        bookMap.put(AppConstant.BOOKING_ID,bid);
        bookMap.put(AppConstant.BOOKING_DATE,null);
        bookMap.put(AppConstant.PACKAGE_ID,packageId);
        bookMap.put(AppConstant.PACKAGE_COST,packageCost);
        bookMap.put(AppConstant.TRAVELLERS,passengerList);
        bookMap.put(AppConstant.TOTAL_AMOUNT,totalCost);
        bookMap.put(AppConstant.SPECIAL_ATTRACTION,extraInclusion);
        bookMap.put(AppConstant.DURATION,duration);
        bookMap.put(AppConstant.PICK_UP,pickUp);
        bookMap.put(AppConstant.DROP_OFF,dropOff);
        bookMap.put(AppConstant.START_DATE,startDepDate);
        bookMap.put(AppConstant.END_DATE,endDepDate);
        bookMap.put(AppConstant.DESTINATION_PIC,null);

        fireStore.collection("bookingAccounts")
                .document(bid)
                .set(bookMap)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Booking Successful", Toast.LENGTH_SHORT).show();
//                        NavController navController = Navigation.findNavController(this, R.id.drawer_layout);
//                        navController.navigateUp();
//                        navController.navigate(R.id.nav_my_trip);
                        //startActivity(new Intent(this, MyTripFragment.class));
                        PaymentGateWayActivity.this.finish();
                    }
                    else{
                        Toast.makeText(this, "Error!\nBooking Unsuccessful" , Toast.LENGTH_SHORT).show();
                        Log.e("firebase_error", "createBookingProfile: \n"+uid+"\n"+task.getException());
                    }
                });
    }
}
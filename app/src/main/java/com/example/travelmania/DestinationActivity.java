package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DestinationActivity extends AppCompatActivity {

    String []destination=new String[]{"All","Andaman and Nicobar","Goa","Himachal Pradesh","Jammu and Kashmir","Kerala","Karnataka","Leh and Ladakh","Lakshadweep","Northeast","Rajasthan","Uttarakhand"};
    int[] allCard=new int[]{R.id.goa_layout1,R.id.andaman_layout1,R.id.uttarakhand_layout1,R.id.uttarakhand_layout2,R.id.uttarakhand_layout3,R.id.shilong_layout,R.id.lajong_layout,R.id.arunachal_layout,R.id.gangtok_layout};
    int[] andamanCard=new int[]{R.id.andaman_layout1,R.id.andaman_layout2};
    int[] goaCard=new int[]{R.id.goa_layout1,R.id.goa_layout2};
    int[] himachalCard=new int[]{R.id.shimla_layout,R.id.manali_layout};
    int[] kashmirCard=new int[]{R.id.jammu_layout,R.id.kashmir_layout};
    int[] keralaCard=new int[]{R.id.kerala_layout};
    int[] karnatakaCard=new int[]{R.id.karnataka_layout};
    int[] ladakhCard=new int[]{R.id.leh_layout,R.id.ladakh_layout};
    int[] lakshdweepCard=new int[]{R.id.lakshdweep_layout};
    int[] norteastCard=new int[]{R.id.shilong_layout,R.id.lajong_layout,R.id.arunachal_layout,R.id.gangtok_layout};
    int[] rajasthanCard=new int[]{R.id.jodhpur_layout,R.id.rajasthan_layout};
    int[] uttarakhandCard=new int[]{R.id.uttarakhand_layout1,R.id.uttarakhand_layout2,R.id.uttarakhand_layout3};
    AutoCompleteTextView destinationInput;
    Button btnRaj,btnJod, btnAndaman,btnAndamanPrime,btnGoa,btnGoaPrime,btnShimla,btnManali,btnJammu,btnKashmir,btnKerala,btnKarnataka,btnLeh,btnLadakh,btnLakshadweep,btnShilong,btnLajong,btnAp,btnGangtok,btnKedarnath,btnNainital,btnMussoorie;
    ImageView imgAndaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        destinationInput=findViewById(R.id.destination);
        btnAndaman=findViewById(R.id.andaman_explore);
        btnAndamanPrime=findViewById(R.id.andaman_explore2);
        btnGoa=findViewById(R.id.goa_explore);
        btnGoaPrime=findViewById(R.id.goa_explore2);
        btnShimla=findViewById(R.id.shimla_explore);
        btnManali=findViewById(R.id.manali_explore);
        btnJammu=findViewById(R.id.jammu_explore);
        btnKashmir=findViewById(R.id.kashmir_explore);
        btnKerala=findViewById(R.id.kerala_explore);
        btnKarnataka=findViewById(R.id.karnataka_explore);
        btnLeh=findViewById(R.id.leh_explore);
        btnLadakh=findViewById(R.id.ladakh_explore);
        btnLakshadweep=findViewById(R.id.lakshdweep_explore);
        btnShilong=findViewById(R.id.shilong_explore);
        btnLajong=findViewById(R.id.lajong_explore);
        btnAp=findViewById(R.id.arunachal_explore);
        btnGangtok=findViewById(R.id.gangtok_explore);
        btnKedarnath=findViewById(R.id.kedarnath_explore);
        btnNainital=findViewById(R.id.nainital_explore);
        btnMussoorie=findViewById(R.id.mussoorie_explore);
        btnRaj=findViewById(R.id.rajasthan_explore);
        btnJod=findViewById(R.id.jodhpur_explore);

        imgAndaman=findViewById(R.id.item_image1);

        //initialising with data
        ArrayAdapter<String> destinationAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,destination);

        //Attach adapter
        destinationInput.setAdapter(destinationAdapter);   //display the data in dropdown list

        //to set the data in the field on clicking event

        destinationInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggleLayout(position);
            }
        });

        btnAndaman.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "12N/13D");
            intent.putExtra("DURATION", "13");
            intent.putExtra("PACKAGE_COST_TXT", "₹42,999 / Person");
            intent.putExtra("PACKAGE_COST", "42999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.adaman_nicobar_resort);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding,Scuba Driving");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001ANDAMAN01");
            startActivity(intent);

        });
        btnAndamanPrime.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹22,999 / Person");
            intent.putExtra("PACKAGE_COST", "22999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.adaman_nicobar_resort);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001ANDAMAN02");
            startActivity(intent);

        });
        btnKedarnath.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹22,999 / Person");
            intent.putExtra("PACKAGE_COST", "22999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.kedarnath2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001KEDARNATH01");
            startActivity(intent);

        });
        btnNainital.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "7N/8D");
            intent.putExtra("DURATION", "8");
            intent.putExtra("PACKAGE_COST_TXT", "₹16,999 / Person");
            intent.putExtra("PACKAGE_COST", "16999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.nainital1);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "NO");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001NAINITAL01");
            startActivity(intent);

        });
        btnMussoorie.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹21,999 / Person");
            intent.putExtra("PACKAGE_COST", "21999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.adaman_nicobar_resort);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding,Scuba Driving");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001MUSSOORIE02");
            startActivity(intent);

        });
        btnGoa.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "10N/11D");
            intent.putExtra("DURATION", "11");
            intent.putExtra("PACKAGE_COST_TXT", "₹39,999 / Person");
            intent.putExtra("PACKAGE_COST", "39999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.goa3);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Scuba Driving");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001GOA02");
            startActivity(intent);

        });
        btnGoaPrime.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "12N/13D");
            intent.putExtra("DURATION", "13");
            intent.putExtra("PACKAGE_COST_TXT", "₹45,999 / Person");
            intent.putExtra("PACKAGE_COST", "45999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.adaman_nicobar_resort);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding,Scuba Driving");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001GOA02");
            startActivity(intent);

        });
        btnManali.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "7N/8D");
            intent.putExtra("DURATION", "8");
            intent.putExtra("PACKAGE_COST_TXT", "₹20,999 / Person");
            intent.putExtra("PACKAGE_COST", "20999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.manali2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "none");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001MANALI01");
            startActivity(intent);

        });
        btnShimla.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "8N/9D");
            intent.putExtra("DURATION", "9");
            intent.putExtra("PACKAGE_COST_TXT", "₹25,999 / Person");
            intent.putExtra("PACKAGE_COST", "25999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.shimla2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding,Scuba Driving");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001SHIMLA01");
            startActivity(intent);

        });
        btnJammu.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.jammu1);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001JAMMU01");
            startActivity(intent);

        });
        btnKashmir.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.kashmir2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001KASHMIR01");
            startActivity(intent);

        });
        btnKerala.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.kerala3);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001KERALA01");
            startActivity(intent);

        });
        btnKarnataka.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.adaman_nicobar_resort);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001KARNATAKA01");
            startActivity(intent);

        });
        btnLeh.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.leh);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001LEHLADAKH01");
            startActivity(intent);

        });
        btnLadakh.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹15,999 / Person");
            intent.putExtra("PACKAGE_COST", "15999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.ladakh3);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001LEHLADAKH02");
            startActivity(intent);

        });
        btnLakshadweep.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "10N/11D");
            intent.putExtra("DURATION", "11");
            intent.putExtra("PACKAGE_COST_TXT", "₹35,999 / Person");
            intent.putExtra("PACKAGE_COST", "35999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.lakshadweep1);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001LAKSHADWEEP01");
            startActivity(intent);

        });
        btnAp.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "8N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹25,999 / Person");
            intent.putExtra("PACKAGE_COST", "25999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.arunachal2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "Para Gliding");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001NE01");
            startActivity(intent);

        });
        btnShilong.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹25,999 / Person");
            intent.putExtra("PACKAGE_COST", "25999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.shilong);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001NE02");
            startActivity(intent);

        });
        btnLajong.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹25,999 / Person");
            intent.putExtra("PACKAGE_COST", "25999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.meghalaya);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001NE03");
            startActivity(intent);

        });
        btnGangtok.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "9N/10D");
            intent.putExtra("DURATION", "10");
            intent.putExtra("PACKAGE_COST_TXT", "₹25,999 / Person");
            intent.putExtra("PACKAGE_COST", "25999");
            intent.putExtra("PICK_UP", "Kolkata");
            intent.putExtra("DROP_OFF", "Kolkata");
            intent.putExtra("IMAGEVIEW", R.drawable.gangtok);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001NE04");
            startActivity(intent);

        });
        btnRaj.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "6N/7D");
            intent.putExtra("DURATION", "7");
            intent.putExtra("PACKAGE_COST_TXT", "₹16,999 / Person");
            intent.putExtra("PACKAGE_COST", "16999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.rajasthan);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001RAJASTHAN01");
            startActivity(intent);

        });
        btnJod.setOnClickListener(v -> {
            //imgAndaman.buildDrawingCache(true);
            //Bitmap AndamanImg=imgAndaman.getDrawingCache();
            //int AndamanImg = R.drawable.adaman_nicobar_resort;
            Intent intent=new Intent(DestinationActivity.this,ExploringActivity.class);
            intent.putExtra("DURATION_TXT", "7N/8D");
            intent.putExtra("DURATION", "8");
            intent.putExtra("PACKAGE_COST_TXT", "₹12,999 / Person");
            intent.putExtra("PACKAGE_COST", "12999");
            intent.putExtra("PICK_UP", "Delhi");
            intent.putExtra("DROP_OFF", "Delhi");
            intent.putExtra("IMAGEVIEW", R.drawable.rajasthan2);//set resource value in android java
            intent.putExtra("SPECIAL_INCLUSION", "None");
            intent.putExtra("PACKAGE_ID", "TRAVELMANIA001RAJASTHAN02");
            startActivity(intent);

        });
        toggleLayout(-1);

    }
    private void showHideLayout(int[] LayoutID,int visibility){
        for(int i=0;i<LayoutID.length;i++)
        findViewById(LayoutID[i]).setVisibility(visibility);
    }
    private void toggleLayout(int selectedGroup){
        if(selectedGroup == 0){
            //display all card
            showHideLayout(allCard,View.VISIBLE);
            //showHideLayout(goaCard,View.VISIBLE);
            //showHideLayout(uttarakhandCard,View.VISIBLE);
        }else if(selectedGroup == 1){
            //show goa layout only
            showHideLayout(andamanCard,View.VISIBLE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else if(selectedGroup == 2){
            //show goa layout only
            showHideLayout(goaCard,View.VISIBLE);
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 3){
            showHideLayout(himachalCard,View.VISIBLE);
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 4){
            showHideLayout(kashmirCard,View.VISIBLE);
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 5){
            showHideLayout(keralaCard,View.VISIBLE);
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 6){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.VISIBLE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 7){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.VISIBLE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 8){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.VISIBLE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 9){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.VISIBLE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 10){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.VISIBLE);
            showHideLayout(uttarakhandCard,View.GONE);
        }else  if (selectedGroup == 11){
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.VISIBLE);
        }else{
            showHideLayout(kashmirCard,View.GONE);
            showHideLayout(andamanCard,View.GONE);
            showHideLayout(goaCard,View.GONE);
            showHideLayout(himachalCard,View.GONE);
            showHideLayout(keralaCard,View.GONE);
            showHideLayout(karnatakaCard,View.GONE);
            showHideLayout(ladakhCard,View.GONE);
            showHideLayout(lakshdweepCard,View.GONE);
            showHideLayout(norteastCard,View.GONE);
            showHideLayout(rajasthanCard,View.GONE);
            showHideLayout(uttarakhandCard,View.GONE);
        }
    }
}
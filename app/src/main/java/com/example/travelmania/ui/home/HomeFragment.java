package com.example.travelmania.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelmania.BookGoaActivity;
import com.example.travelmania.BookKedarnathActivity;
import com.example.travelmania.BookLadakhActivity;
import com.example.travelmania.BookMussoorieActivity;
import com.example.travelmania.DestinationActivity;
import com.example.travelmania.Gangtok_book_Activity;
import com.example.travelmania.LoginActivity;
import com.example.travelmania.ProfileActivity;
import com.example.travelmania.R;
import com.example.travelmania.SignUpActivity;
import com.example.travelmania.SlideData;
import com.example.travelmania.SliderAdapter;
import com.example.travelmania.databinding.FragmentHomeBinding;
import com.example.travelmania.databinding.GangktokModalBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button btnKedarnath,btnGangtok,btnLadakh,btnGoa,btnMussooire,bookGangtok,bookKedarnath;
    EditText destination;



    String url1 = "https://images.pexels.com/photos/7846659/pexels-photo-7846659.jpeg?cs=srgb&dl=pexels-sanket-barik-7846659.jpg&fm=jpg";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/learning-app-a53fc.appspot.com/o/adaman_nicobar.jpg?alt=media&token=11f9414f-1aea-4882-9bbb-8c03723f5948";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/learning-app-a53fc.appspot.com/o/Lakshadweep1.jpg?alt=media&token=163f6f64-e21f-419d-afb7-5c72cb097c30";
    String url4 = "https://firebasestorage.googleapis.com/v0/b/learning-app-a53fc.appspot.com/o/meghalaya.jpg?alt=media&token=3012f459-4abf-4b1b-b3ba-4629b1ea88e4";
    String url5 = "https://firebasestorage.googleapis.com/v0/b/learning-app-a53fc.appspot.com/o/Shimla1.jpg?alt=media&token=7637ad8f-eee4-4037-abbb-f39e829cdb85";
    String url6 = "https://firebasestorage.googleapis.com/v0/b/learning-app-a53fc.appspot.com/o/east_sikkim1.jpg?alt=media&token=fca6af74-80eb-4f7d-9562-7b2dda5b95e2";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        destination=binding.destination;
        destination.setOnClickListener(v -> {
            navToDestination();
        });

        // we are creating array list for storing our image urls.
        ArrayList<SlideData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView =binding.slider;

        // adding the urls inside array list
        sliderDataArrayList.add(new SlideData(url1));
        sliderDataArrayList.add(new SlideData(url2));
        sliderDataArrayList.add(new SlideData(url3));
        sliderDataArrayList.add(new SlideData(url4));
        sliderDataArrayList.add(new SlideData(url5));
        sliderDataArrayList.add(new SlideData(url6));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setAdapter to sliderView.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autoCycle below method is used.
        sliderView.startAutoCycle();



        btnGangtok= binding.gangtokExplore;
        btnKedarnath= binding.kedarnathExplore;
        btnLadakh= binding.ladakhExplore;
        btnGoa= binding.goaExplore;
        btnMussooire= binding.mussoorieExplore;

        btnKedarnath.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BookKedarnathActivity.class));
        } );
        btnGangtok.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Gangtok_book_Activity.class));
        } );
        btnLadakh.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BookLadakhActivity.class));
        } );
        btnGoa.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BookGoaActivity.class));
        } );
        btnMussooire.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BookMussoorieActivity.class));
        } );

        //btnGoa.setOnClickListener(view -> openGoaBottomModal() );
        return root;
    }

    private void navToDestination() {
            //Code to route to the signUp activity
            Intent intent=new Intent(getActivity(), DestinationActivity.class);
            startActivity(intent);
    }

    //code to create modal page...
   /* private void openGangtokBottomModal() {
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getContext());
        GangktokModalBinding modalBinding=GangktokModalBinding.inflate(getLayoutInflater(), binding.getRoot(), false);
        bottomSheetDialog.setContentView(modalBinding.getRoot());

        bookGangtok=modalBinding.bookGangtok;
        bottomSheetDialog.show();
        bookGangtok.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), Gangtok_book_Activity.class));
            bottomSheetDialog.dismiss();
        });

    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
/*

String destination = [all,...];
LinearLayout andamanCards=[R.id.andaman_1,R.id.andaman2];
LinearLayout goaCards=[R.id.goa1,R.id.goa1];
chooseDestination.onCLickListener(){
    int selectedPlace = list.getPlaceIndex();
    if(sp==0){
        for(i=0;i<andamanCards.length;i++){
        fvbid(andamanCards[i].setVisibility(View.VISIBLE);
        }
        for(i=0;i<goa.length;i++){
        fvbid(goa[i].setVisibility(View.VISIBLE);
        }
    }else if(sp===1){
    for(i=0;i<andamanCards.length;i++){
        fvbid(andamanCards[i].setVisibility(View.VISIBLE);
        }
        for(i=0;i<goa.length;i++){
        fvbid(goa[i].setVisibility(View.GONE);
        }
    }
    .
}
 */
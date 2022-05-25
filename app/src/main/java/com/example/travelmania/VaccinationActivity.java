package com.example.travelmania;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import com.example.travelmania.databinding.ActivityVaccinationBinding;
import com.example.travelmania.databinding.ModalProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class VaccinationActivity extends AppCompatActivity {
    ImageView imgVac;
    FloatingActionButton favVac;
    Button ruleBtn,updateBtn;
    ActivityVaccinationBinding vaccinationBinding;
    public CircleImageView delete,openGallery,openCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vaccinationBinding= ActivityVaccinationBinding.inflate(getLayoutInflater());
        setContentView(vaccinationBinding.getRoot());
        imgVac=vaccinationBinding.imgVac;
        favVac=vaccinationBinding.favVac;
        ruleBtn=vaccinationBinding.btnRule;
        updateBtn=vaccinationBinding.btnUpdate;

        favVac.setOnClickListener(v -> {
            openBottomModal();
        });
    }
    // to accept and use the gallery pictures
    ActivityResultLauncher<Intent> galleryResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    assert result.getData() != null;
                    Uri imagePath = result.getData().getData();
                    //set
                    imgVac.setImageURI(imagePath);

                    //now add code to upload in the storage
                }
            });

    // accept and use the camera picture
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Bitmap bit = (Bitmap) result.getData().getExtras().get("data");
                        // set to image view
                        imgVac.setImageBitmap(bit);

                        //now add code to upload in the storage
                    }
                }
            });


    private void openBottomModal() {
        //code to open bottom sheet dialog
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        ModalProfileBinding modalProfileBinding=ModalProfileBinding.inflate(getLayoutInflater(),vaccinationBinding.getRoot(),false);
        bottomSheetDialog.setContentView(modalProfileBinding.getRoot()); //setting the display against modal

        //Mapping for the view in modal
        delete=modalProfileBinding.delete;
        openGallery=modalProfileBinding.openGallery;
        openCamera=modalProfileBinding.openCamera;

        bottomSheetDialog.show();//to make the modal visible



        delete.setOnClickListener(view -> {
            imgVac.setImageResource(R.drawable.document);
            bottomSheetDialog.dismiss();
        });

        openGallery.setOnClickListener(view -> {
            //code to open gallery to select picture

            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT); // open the library
            galleryIntent.setType("image/*"); // provide access to images folder(s)
            Intent chooser = Intent.createChooser(galleryIntent, "Pick an Image"); // choose or pick a picture
            galleryResult.launch(chooser); // result
            bottomSheetDialog.dismiss();
        });

        openCamera.setOnClickListener(view -> {
            //code to open camera to capture picture
            Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //open camera and allow to click image

            //startActivityForResult(cameraIntent,100); //This methode is not allowed for this version of android instead we can use
            cameraResult.launch(cameraIntent); // result
            bottomSheetDialog.dismiss();
        });

    }
}
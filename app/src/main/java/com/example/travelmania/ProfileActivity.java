package com.example.travelmania;

import static com.example.travelmania.common.AppConstant.USER_NAME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmania.common.AppConstant;
import com.example.travelmania.databinding.ActivityProfileBinding;
import com.example.travelmania.databinding.ModalProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding profileBinding;
    public CircleImageView circleImageView,delete,openGallery,openCamera;
    public FloatingActionButton fab;
    public Calendar myCalender;
    public EditText dobInput;
    public TextInputLayout nameLayout,passportLayout,districtLayout,pinLayout,stateLayout;
    public TextInputEditText nameInput,emailInput,phoneInput,passportInput,districtInput,pinInput,stateInput;
    public AutoCompleteTextView genderInput;
    Button updateBtn;
    String []Gender=new String[]{"Male","Female","Others"};
    MaterialCardView navToVaccination;



    FirebaseAuth fAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(profileBinding.getRoot());
        navToVaccination= profileBinding.myVaccinationCard;

        circleImageView=profileBinding.profileImage;
        fab=profileBinding.profileFavBtn;

        nameInput=profileBinding.nameInput;
        nameLayout=profileBinding.nameLayout;

        dobInput=profileBinding.dobInput;

        genderInput=profileBinding.genderDropDown;

        emailInput=profileBinding.emailInput;

        phoneInput=profileBinding.numberInput;

        passportInput=profileBinding.passportInput;
        passportLayout=profileBinding.passportLayout;

        districtInput=profileBinding.districtInput;
        districtLayout=profileBinding.districtLayout;

        pinInput=profileBinding.pinInput;
        pinLayout=profileBinding.pinLayout;

        stateInput=profileBinding.stateInput;
        stateLayout=profileBinding.stateLayout;

        updateBtn=profileBinding.updateBtn;

        navToVaccination.setOnClickListener(v -> {
            Intent intent=new Intent(this,VaccinationActivity.class);
            startActivity(intent);
        });

        myCalender=Calendar.getInstance();
        fAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        String uid=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference=firebaseFirestore.collection("userAccounts").document(uid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                //circleImageView.setImageResource(value.getString("pic"));
                nameInput.setText(value.getString("name"));
                dobInput.setText(value.getString("dob"));
                genderInput.setText(value.getString("gender"));
                emailInput.setText(value.getString("email"));
                phoneInput.setText(value.getString("number"));
                passportInput.setText(value.getString("passport_no"));
                districtInput.setText(value.getString("district"));
                pinInput.setText(value.getString("pin"));
                stateInput.setText(value.getString("state"));

            }
        });

        /*firebaseFirestore.collection("userAccounts")
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       DocumentSnapshot snapshot=task.getResult();  //fetching result in document format
                       Map<String,Object> map=snapshot.getData();   //saving in map variable
                       User user=new User(
                               String.valueOf(map.get("name")),
                               String.valueOf(map.get("dob")),
                               String.valueOf(map.get("gender")),
                               String.valueOf(map.get("email")),
                               String.valueOf(map.get("number")),
                               String.valueOf(map.get("passport_no.")),
                               String.valueOf(map.get("district")),
                               String.valueOf(map.get("pin")),
                               String.valueOf(map.get("state"))
                       );
                   }
                });*/

        fab.setOnClickListener(view -> openBottomModal());


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH,month);
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        dobInput.setOnClickListener(v -> {
            new DatePickerDialog(ProfileActivity.this, date, myCalender
                    .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH)).show();
        });

        //initialising with data
        ArrayAdapter<String> adapter=new ArrayAdapter<>(ProfileActivity.this,R.layout.support_simple_spinner_dropdown_item,Gender);

        //Attach adapter
        genderInput.setAdapter(adapter);   //display the data in dropdown list

        //to set the data in the field on clicking event
        genderInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue=parent.getItemAtPosition(position).toString();
                Log.i("TAG", "onItemSelected: "+selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updateBtn.setOnClickListener(v -> {
            nameLayout.setError("");
            passportLayout.setError("");
            districtLayout.setError("");
            pinLayout.setError("");
            stateLayout.setError("");
            String name=String.valueOf(nameInput.getText()).trim();
            String DOB=String.valueOf(dobInput.getText()).trim();
            String gender=String.valueOf(genderInput.getText()).trim();
            String passport=String.valueOf(passportInput.getText()).trim();
            String district=String.valueOf(districtInput.getText()).trim();
            String pin=String.valueOf(pinInput.getText()).trim();
            String state=String.valueOf(stateInput.getText()).trim();
            String pic=String.valueOf(circleImageView.getBackground()).trim();

            if (validateFields(name,passport,district,pin,state)){
                updateProfile(uid,pic,name,DOB,gender,passport,district,pin,state);
            }
            else
                callToast("Error!\nUpdate Unsuccessful");
        });

    }

    private void updateProfile(String uid, String pic, String name, String dob, String gender, String passport, String district, String pin, String state) {
        Map<String,Object> userMap=new HashMap<>();          //Collection framework
        userMap.put(AppConstant.USER_PIC,pic);
        userMap.put(USER_NAME,name);
        userMap.put(AppConstant.USER_DOB,dob);
        userMap.put(AppConstant.USER_GENDER,gender);
        userMap.put(AppConstant.USER_PASSPORT,passport);
        userMap.put(AppConstant.USER_DISTRICT,district);
        userMap.put(AppConstant.USER_PIN,pin);
        userMap.put(AppConstant.USER_STATE,state);

        firebaseFirestore.collection("userAccounts")
                .document(uid)
                .update(userMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        callToast("Update Successful");
                    else
                        callToast("Update Unsuccessful");
                });

    }

    private void callToast(String selectedValue) {
        Toast.makeText(ProfileActivity.this,selectedValue, Toast.LENGTH_SHORT).show();
    }

    private boolean validateFields(String name, String passport, String district, String pin, String state) {
        if(name.isEmpty()){
            nameLayout.setError("please provide your Name");
        }
        else if(!name.matches("^[a-zA-Z.\\s]+$")){
            nameLayout.setError("Please provide your Valid Name");
        }
        else if(!passport.matches("^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$")){
            passportLayout.setError("Please provide  Valid Passport Number");
        }
        else if(district.isEmpty()){
            districtLayout.setError("please provide your District");
        }
        else if(!district.matches("^[a-zA-Z.\\s]+$")){
            districtLayout.setError("Please provide  Valid District");
        }
        else if(pin.isEmpty()){
            pinLayout.setError("Please provide your Pin Code");
        }
        else if(/*number.length()<10|| number.length() > 13 ||*/ !pin.matches("^[+]?[0-9]{6}$")){
            pinLayout.setError("Please provide valid Pin Code");
        }
        else if(state.isEmpty()){
            stateLayout.setError("please provide your State");
        }
        else if(!state.matches("^[a-zA-Z.\\s]+$")){
            stateLayout.setError("Please provide  Valid State");
        }
        else
            return true;
        return false;
    }

    private void updateLabel() {
        String myFormat="dd MMM,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dobInput.setText(sdf.format(myCalender.getTime()));
    }


    // to accept and use the gallery pictures
    ActivityResultLauncher<Intent> galleryResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    assert result.getData() != null;
                    Uri imagePath = result.getData().getData();
                    //set
                    circleImageView.setImageURI(imagePath);

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
                        circleImageView.setImageBitmap(bit);

                        //now add code to upload in the storage
                    }
                }
            });

    private void openBottomModal() {
        //code to open bottom sheet dialog
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ProfileActivity.this);
        ModalProfileBinding modalProfileBinding=ModalProfileBinding.inflate(getLayoutInflater(),profileBinding.getRoot(),false);
        bottomSheetDialog.setContentView(modalProfileBinding.getRoot()); //setting the display against modal

        //Mapping for the view in modal
        delete=modalProfileBinding.delete;
        openGallery=modalProfileBinding.openGallery;
        openCamera=modalProfileBinding.openCamera;

        bottomSheetDialog.show();//to make the modal visible



        delete.setOnClickListener(view -> {
            circleImageView.setImageResource(R.drawable.default_person_logo);
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
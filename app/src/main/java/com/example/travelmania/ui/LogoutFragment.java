package com.example.travelmania.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelmania.LoginActivity;
import com.example.travelmania.R;
import com.example.travelmania.common.LocalSession;
import com.example.travelmania.databinding.FragmentLogoutBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends DialogFragment {

    FragmentLogoutBinding binding;
    Button btnCancel, btnLogout;
    FirebaseAuth auth;
    LocalSession session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBinding.bind(View.inflate(getContext(), R.layout.fragment_logout, null));
        btnCancel = binding.btnCancel;
        btnLogout = binding.btnLogout;
        session = new LocalSession(getContext());
        auth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            session.logout();
            //Snackbar.make(requireContext(),binding.getRoot(), "Successfully Logged out", Snackbar.LENGTH_LONG).show();
            Toast.makeText(getContext(), "Successfully Logged out", Toast.LENGTH_SHORT).show();
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finishAffinity();     //All the screen pushed in the navigation stack will be removed
        });

        btnCancel.setOnClickListener(v -> {
            //Snackbar.make(requireContext(),binding.getRoot(),"Cancelled",Snackbar.LENGTH_LONG).show();
            Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            getDialog().cancel();
        });
        return binding.getRoot();
    }
}
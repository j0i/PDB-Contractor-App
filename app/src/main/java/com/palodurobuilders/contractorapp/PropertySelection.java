package com.palodurobuilders.contractorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.util.HashMap;
import java.util.Map;

public class PropertySelection extends AppCompatActivity
{
    ImageButton mAddProjectButton;
    ImageButton mArchiveDrawerButton;
    Button mLogoutButton;
    FrameLayout mHouseSelectionFrame;
    private static final String TAG = "Property Selection";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_selection);

        setStatusBarColor();

        mAddProjectButton = findViewById(R.id.button_add_house);
        mArchiveDrawerButton = findViewById(R.id.button_archive_drawer);
        mLogoutButton = findViewById(R.id.button_logout);
        mHouseSelectionFrame = findViewById(R.id.frame_house_selection);

        mLogoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                testToast();
            }
        });
        mAddProjectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent propertyUtilitiesIntent = new Intent(getApplicationContext(), PropertyUtilities.class);
                startActivity(propertyUtilitiesIntent);
            }
        });
    }

    private void testToast()
    {
        Toast toast = Toast.makeText(this, R.string.toast_test, Toast.LENGTH_LONG);

        toast.show();
    }

    public void setStatusBarColor()
    {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}

/*This is the code for adding someone with attributes first, last, title to the users collection
* into the firebase firestore
*
*   */

       /* FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Jeff");
        user.put("last", "Thompas");
        user.put("Title", "Boss");

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });*/


/*This is for the Firebase Realtime Database we will use this for messaging*/
/*
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/

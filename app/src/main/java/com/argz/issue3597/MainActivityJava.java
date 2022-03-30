package com.argz.issue3597;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivityJava extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addData(View view) {
        DatabaseReference testRef = FirebaseDatabase.getInstance().getReference("tests");
        Map<String, Number> ob1 = new HashMap();
        Map<String, Number> ob2 = new HashMap();
        Map<String, Number> ob3 = new HashMap();
        Map<String, Number> ob4 = new HashMap();
        Map<String, Number> ob5 = new HashMap();
        ob1.put("search", 1);
        ob2.put("search", 3);
        ob3.put("search", 2);
        ob4.put("search", 5);
        ob5.put("search", 4);
        Map<String, Map<String, Number>> objs = new HashMap();
        objs.put("yo", ob1);
        objs.put("to", ob2);
        objs.put("lo", ob3);
        objs.put("fo", ob4);
        objs.put("eo", ob5);
        testRef.setValue(objs);
        Toast.makeText(this, "Update complete", Toast.LENGTH_SHORT).show();
    }

    public void test(View view){
        DatabaseReference testRef = FirebaseDatabase.getInstance().getReference("tests");

        testRef.orderByChild("search").startAfter((double) 3).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e(TAG, "Error getting data", task.getException());
            } else {
                Map<String, Map<String, Number>> result = (Map<String, Map<String, Number>>) task.getResult().getValue();
                Log.d(TAG, "onComplete: " + result.toString());
                Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

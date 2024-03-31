package com.example.dolmabank;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import  android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    private int getIconResource(String selectedItem) {
        switch (selectedItem) {
            case "Russian":
                return R.drawable.ic_russian;
            case "English":
                return R.drawable.ic_english;
            case "Ukrainian":
                return R.drawable.ic_ukrainian;
            case "Azerbaijani":
                return R.drawable.ic_azerbaijani;
            case "French":
                return R.drawable.ic_french;
            case "Deutsch":
                return R.drawable.ic_deutch;
            default:
                return -1; // Provide a default icon if the selected item doesn't have an associated icon
        }
    }
    String[] languages= {"Russian","English","Ukrainian","Azerbaijani","French","Deutsch"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterLanguages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);


        adapterLanguages = new CustomArrayAdapter(this, R.layout.dropdown_item_layout, languages);
        autoCompleteTxt.setAdapter(adapterLanguages);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                String item = parent.getItemAtPosition(position).toString();
                ImageView languageIcon = parent.findViewById(R.id.language_icon);
                int iconResId = getIconResource(item);
                autoCompleteTxt.setCompoundDrawablesWithIntrinsicBounds(iconResId,0,0,0);


                languageIcon.setImageResource(iconResId);


                Toast.makeText(getApplicationContext(),"Language:"+iconResId,Toast.LENGTH_SHORT).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
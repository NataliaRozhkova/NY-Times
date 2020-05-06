package com.example.nytimes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutAuthorActivity extends AppCompatActivity {



    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_author);
        editText = findViewById(R.id.edit_text);

        TextView messageButton = findViewById(R.id.message_button);

        messageButton.setOnClickListener((view) -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            email.putExtra(Intent.EXTRA_EMAIL, getResources().getStringArray(R.array.email_address));

            email.putExtra(Intent.EXTRA_TEXT, editText.getText());
            email.putExtra(Intent.EXTRA_SUBJECT, "Amazing android program");
            if (email.resolveActivity(getPackageManager()) != null) {
                startActivity(email);
            } else {
                showToast();
            }

        } );

        ImageView telegram = findViewById(R.id.telegram);
        telegram.setOnClickListener( (v) -> {
            Uri webpage = Uri.parse(getString(R.string.telegram_address));
            Intent openTelegram = new Intent(Intent.ACTION_VIEW, webpage);
            if (openTelegram.resolveActivity(getPackageManager()) != null) {
                startActivity(openTelegram);
            }
        });

        ImageView vkontakte = findViewById(R.id.vkontakte);
        vkontakte.setOnClickListener( (v) -> {
            Uri webpage = Uri.parse(getString(R.string.vkontakte_address));
            Intent openTelegram = new Intent(Intent.ACTION_VIEW, webpage);
            if (openTelegram.resolveActivity(getPackageManager()) != null) {
                startActivity(openTelegram);
            }
        });

        ImageView instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener( (v) -> {
            Uri webpage = Uri.parse(getString(R.string.instagram_address));
            Intent openTelegram = new Intent(Intent.ACTION_VIEW, webpage);
            if (openTelegram.resolveActivity(getPackageManager()) != null) {
                startActivity(openTelegram);
            }
        });

    }

    private void showToast() {
        Toast.makeText(this, "No Email app found", Toast.LENGTH_LONG).show();
    }

}

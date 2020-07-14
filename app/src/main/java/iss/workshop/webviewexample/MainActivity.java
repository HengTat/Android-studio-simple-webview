package iss.workshop.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTERNAL_URL="externalUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button launchBtn=findViewById(R.id.launchhBtn);
        launchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String externalURL="https://www.iss.nus.edu.sg/graduate-programmes/programme/detail/graduate-diploma-in-systems-analysis";
                launchExternalPage(externalURL);
            }
        });
    }
    void launchExternalPage(String externalUrl){
        Intent intent=new Intent(MainActivity.this, WebviewActivity.class);
        intent.putExtra(EXTERNAL_URL, externalUrl);
        startActivity(intent);
    }

}
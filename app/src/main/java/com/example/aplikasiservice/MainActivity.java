package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button mSetBtn;
    private Button mUnsetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mLimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioButton mTimeRadioGroup;
    public int mChangeTeam = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn = (Button)findViewById(R.id.btnSet);
        mUnsetBtn = (Button)findViewById(R.id.btnUnset);
        mMenitRadio = (RadioButton)findViewById(R.id.radio0);
        mLimaRadio = (RadioButton)findViewById(R.id.radio1);
        mTigaPuluhRadio = (RadioButton)findViewById(R.id.radio2);
        mJamRadio = (RadioButton)findViewById(R.id.radio3);
        mTimeRadioGroup = (RadioButton) findViewById(R.id.radioGroup);

        mUnsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mDisable = new Intent(MainActivity.this, WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioID = mTimeRadioGroup.getCheckedRadioButton();
                if (mMenitRadio.getId()==mRadioID) {mChangeTeam=60;}
                else if (mLimaRadio.getId()==mRadioID) {mChangeTeam=5*60;}
                else if (mTigaPuluhRadio.getId() == mRadioID) {mChangeTeam=30*60;}
                else if (mJamRadio.getId() == mRadioID) {mChangeTeam=60*60;}

                Intent mService = new Intent(MainActivity.this, WallpaperChangeService.class);
                /*membuat bundle dan menyimpan pasangan nilai dengan kuncinya*/
                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChangeTeam);
                /*Menaruh bundle kedalam intent*/
                mService.putExtras(mBundleTime);
                /*Memulai service*/
                startService(mService);
                /*mengakhiri service*/
                finish();
            }
        });

    }
}

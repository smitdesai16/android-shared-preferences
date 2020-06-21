package me.creatorguy.androidsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMainName;
    private Switch sMain;
    private TextView tvMainName, tvMainSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMainName = findViewById(R.id.etMainName);
        Button btnMainSave = findViewById(R.id.btnMainSave);
        sMain = findViewById(R.id.sMain);
        tvMainName = findViewById(R.id.tvMainName);
        tvMainSwitch = findViewById(R.id.tvMainSwitch);
        Button btnMainLoad = findViewById(R.id.btnMainLoad);

        btnMainSave.setOnClickListener(this);
        btnMainLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        switch (view.getId()) {
            case R.id.btnMainSave:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", etMainName.getText().toString());
                editor.putBoolean("switch", sMain.isChecked());
                editor.apply();

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnMainLoad:
                String name = sharedPreferences.getString("name", "");
                tvMainName.setText(name);

                boolean switchValue = sharedPreferences.getBoolean("switch", false);
                tvMainSwitch.setText(Boolean.toString(switchValue));
                break;
        }
    }
}
package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int ET_MAX_COUNT = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //TODO 测试edittext 超过10个字符就提示
        final EditText et = findViewById(R.id.et);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > ET_MAX_COUNT) {
                    et.setText(s.toString().substring(0, ET_MAX_COUNT));
                    et.setSelection(ET_MAX_COUNT);
                    Toast.makeText(MainActivity.this, "不能超过10个字符", Toast.LENGTH_SHORT).show();
                    if(!TextUtils.isEmpty(et.getText())) {
                        Log.d("MainActivity:10", et.getText().toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(et.getText().toString() != null) {
//                    Log.d("MainActivity:button", "editText 现在输出的是" + et.getText().toString());
//                }
                String desc = et.getText().toString();
                int inputCount = 0;
                for(int i = 0; i < desc.length(); i++) {
                    if(desc.charAt(i) != ' ') {
                        inputCount ++;
                        if(inputCount == 10) {
                            break;
                        }
                    }
                }
                if(inputCount < 10) {
                    inputCount = 0;
                    Toast.makeText(MainActivity.this, "至少10个字符", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } );
    }
}

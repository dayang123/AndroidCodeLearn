package com.example.administrator.myapplication.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import kotlinx.android.synthetic.main.activity_test_edittext.button
import kotlinx.android.synthetic.main.activity_test_edittext.et

class TestEditTextActivity : AppCompatActivity() {

    private val ET_MAX_COUNT = 90

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_edittext)
        initView()
    }

    private fun initView() {
        //TODO 测试edittext 超过10个字符就提示
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > ET_MAX_COUNT) {
                    et.setText(s.toString().subSequence(0, ET_MAX_COUNT))
                    et.setSelection(ET_MAX_COUNT)
                    Toast.makeText(this@TestEditTextActivity, "不能超过10个字符", Toast.LENGTH_SHORT).show()
                    if (!TextUtils.isEmpty(et.getText())) {
                        Log.d("MainActivity:10", et.getText().toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
        button.setOnClickListener(View.OnClickListener {
            //                if(et.getText().toString() != null) {
            //                    Log.d("MainActivity:button", "editText 现在输出的是" + et.getText().toString());
            //                }
            val desc = et.getText().toString()
            var inputCount = 0
            for (i in 0..desc.length) {
                if (desc.get(i) != ' ') {
                    inputCount++
                    if (inputCount == 10) {
                        break
                    }
                }
            }
            if (inputCount < 10) {
                inputCount = 0
                Toast.makeText(this, "至少10个字符", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
        })
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TestEditTextActivity::class.java))
        }
    }
}
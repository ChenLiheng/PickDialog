package info.devnote.pickdialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import info.devnote.pickdialog.window.PickDatePopupWindow;

public class MainActivity extends Activity {

    private TextView mTimeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mTimeContent = (TextView) findViewById(R.id.time_content);
        mTimeContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给出一个默认值或者是回去mTimeContent中的String格式化
                int year = 2000;
                int month = 1;
                int day = 1;
                String birthday = mTimeContent.getText().toString();

                if (!TextUtils.isEmpty(birthday)) {
                    try {
                        year = Integer.valueOf(birthday.split("-")[0]);
                        month = Integer.valueOf(birthday.split("-")[1]);
                        day = Integer.valueOf(birthday.split("-")[2]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                PickDatePopupWindow pickDatePopupWindow = new PickDatePopupWindow(MainActivity.this, year, month, day);
                pickDatePopupWindow.setDatePickListener(new PickDatePopupWindow.DatePickListener() {
                    @Override
                    public void onPickDate(Integer year, Integer month, Integer day) {
                        String ageText = String.format("%d-%02d-%02d", year, month, day);
                        mTimeContent.setText(ageText);
                    }
                });
                pickDatePopupWindow.showAtLocation(mTimeContent, Gravity.BOTTOM, 0, 0);
            }
        });
    }
}

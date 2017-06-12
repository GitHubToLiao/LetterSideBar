package fintechnet.izxjf.com.lettersidebar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  LetterSideBar letterSideBar;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letterSideBar = (LetterSideBar) findViewById(R.id.letterSideBar);
        mTextView = (TextView) findViewById(R.id.mTextView);
        letterSideBar.setOnLetterTouthListener(new LetterSideBar.onLetterTouthListener() {
            @Override
            public void onTouth(String currentLetter, boolean isTouth) {
                if(isTouth){
                    mTextView.setVisibility(View.VISIBLE);
                    mTextView.setTextColor(Color.BLUE);
                    mTextView.setText(currentLetter);
                }else {
                    mTextView.setVisibility(View.GONE);
                }
            }
        });
    }
}

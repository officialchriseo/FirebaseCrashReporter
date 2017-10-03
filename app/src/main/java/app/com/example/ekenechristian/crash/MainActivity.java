package app.com.example.ekenechristian.crash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity {

    private TextView sampleText;

    private Button fatalError, fatalError2, nonFatalError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fatalError = (Button) findViewById(R.id.fatal_error);
        fatalError2 = (Button) findViewById(R.id.fatal_error_2);
        nonFatalError = (Button) findViewById(R.id.non_fatal_error);

        fatalError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleText.setText("This will give a null pointer exception message");
            }
        });

        fatalError2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new ArrayIndexOutOfBoundsException();
            }
        });

        nonFatalError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sampleText == null){
                    FirebaseCrash.report(new Exception("Mind you, sample text has not been initialized"));
                    Toast.makeText(MainActivity.this, "Custom exception has been thrown", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

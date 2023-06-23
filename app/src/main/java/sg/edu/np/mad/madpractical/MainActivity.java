package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button follow = findViewById(R.id.follow);
        //Button message = findViewById(R.id.message);
        TextView name = findViewById(R.id.textView2);
        TextView des = findViewById(R.id.textView);
        User randomUser = (User) getIntent().getSerializableExtra("key");
        name.setText(randomUser.getName());
        des.setText(randomUser.getDescription());

        follow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!randomUser.isFollowed()){
                    follow.setText("unfollow");
                    randomUser.setFollowed(true);
                    Toast.makeText(getApplicationContext(),
                                    "followed",
                                    Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    follow.setText("follow");
                    randomUser.setFollowed(false);
                }
            }
        });
    }
}
package exercise.find.roots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ShowResultActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        long originalNumber = intent.getLongExtra("original_number", 0);
        long root1 = intent.getLongExtra("root1", 0);
        long root2 = intent.getLongExtra("root2", 0);
        long time = intent.getLongExtra("time_seconds", 0);

        String originalNumberS = "Original number is: " + String.valueOf(originalNumber);
        String root1S = "The first root is: " + String.valueOf(root1);
        String root2S = "The second root is: " + String.valueOf(root2);
        String timeS = "Time taken " + time + " s.";

        TextView originalNumberView = findViewById(R.id.originalNumber);
        originalNumberView.setText(originalNumberS);
        TextView root1View = findViewById(R.id.root1);
        root1View.setText(root1S);
        TextView root2View = findViewById(R.id.root2);
        root2View.setText(root2S);
        TextView timeView = findViewById(R.id.timeTaken);
        timeView.setText(timeS);

    }
}

package fr.loutry.epoxysample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import fr.loutry.epoxysample.ui.showcase.ShowcaseFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ShowcaseFragment.newInstance())
                    .commitNow();
        }
    }
}

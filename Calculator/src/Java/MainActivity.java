package ensf409.androidcalculator;
/**
 * @author  Karan Bengali and Naveed Kawsar
 * @since   March 28, 2017
 * @version 1.0
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Launches the start up page and runs Calculator activity
 * after the start button has been pressed
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Display the homepage on startup
     * @param savedInstanceState Reference to the Bundle object that
     *                           is passed into the onCreate method
     *                           of every Android activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays the calculator gui after the start button has been pressed
     * @param v Object that has been clicked
     */
    public void displayCalculator(View v){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
}

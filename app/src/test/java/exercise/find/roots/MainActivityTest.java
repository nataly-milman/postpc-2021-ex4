package exercise.find.roots;

import android.content.Intent;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityTest extends TestCase {

    @Test
    public void when_activityIsLaunching_then_theButtonShouldStartDisabled() {
        // create a MainActivity and let it think it's currently displayed on the screen
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

        // test: make sure that the "calculate" button is disabled
        Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);
        assertFalse(button.isEnabled());
    }

    @Test
    public void when_activityIsLaunching_then_theEditTextShouldStartEmpty() {
        // create a MainActivity and let it think it's currently displayed on the screen
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

        // test: make sure that the "input" edit-text has no text
        EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
        String input = inputEditText.getText().toString();
        assertTrue(input == null || input.isEmpty());
    }

    @Test
    public void when_userIsEnteringNumberInput_and_noCalculationAlreadyHappned_then_theButtonShouldBeEnabled() {
        // create a MainActivity and let it think it's currently displayed on the screen
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

        // find the edit-text and the button
        EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
        Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

        // test: insert input to the edit text and verify that the button is enabled
        inputEditText.setText("1234");
        assertTrue(button.isEnabled());
    }

    @Test
    public void enable_disable_onInputChanges() {
        // create a MainActivity and let it think it's currently displayed on the screen
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

        // find the edit-text and the button
        EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
        Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

        inputEditText.setText("1234");
        assertTrue(button.isEnabled());

        inputEditText.setText("");
        assertFalse(button.isEnabled());
    }

    @Test
    public void when_20sBroadcastAppears_ButtonEnabled() {
        // create a MainActivity and let it think it's currently displayed on the screen
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

        // find the edit-text and the button
        EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
        Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

        inputEditText.setText("2305843009213693951");  // prime number from moodle
        button.performClick();
        assertFalse(button.isEnabled());

        Intent mockIntent = new Intent("stopped_calculations");
        mockIntent.putExtra("time_until_give_up_seconds", 21);
        RuntimeEnvironment.application.sendBroadcast(mockIntent);
        Shadows.shadowOf(Looper.getMainLooper()).idle();

        assertTrue(button.isEnabled());
    }


}
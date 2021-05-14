package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class CalculateRootsService extends IntentService {

    private long root1;
    private long root2;

    public CalculateRootsService() {
        super("CalculateRootsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;
        long timeStartMs = System.currentTimeMillis();
        long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
        if (numberToCalculateRootsFor <= 0) {
            Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
            return;
        }

        calculateRoots(numberToCalculateRootsFor, timeStartMs);
        Intent curIntent = new Intent();

        if (root1 == -1) {
            curIntent.setAction("stopped_calculations");
            curIntent.putExtra("time_until_give_up_seconds",
                    TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()
                            - timeStartMs));
            curIntent.putExtra("original_number", numberToCalculateRootsFor);
        } else {
            curIntent.setAction("found_roots");
            curIntent.putExtra("original_number", numberToCalculateRootsFor);
            curIntent.putExtra("root1", root1);
            curIntent.putExtra("root2", root2);
            curIntent.putExtra("time_seconds",
                    TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()
                            - timeStartMs));
        }
        sendBroadcast(curIntent);
    }

    private void calculateRoots(long numberToCalculateRootsFor, long timeStartMs) {
        // base case
        if (numberToCalculateRootsFor == 1) {
            root1 = 1;
            root2 = 1;
            return;
        }
        // look for roots; check timeout
        for (long i = 2; i < Math.sqrt(numberToCalculateRootsFor) + 1; i++) {
            if (numberToCalculateRootsFor % i == 0) {
                root1 = i;
                root2 = numberToCalculateRootsFor / i;
                return;
            }
            if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timeStartMs) >= 20) {
                root1 = -1;
                root2 = -1;
                return;
            }

        }
        // otherwise it's prime
        root1 = numberToCalculateRootsFor;
        root2 = 1;
    }

}
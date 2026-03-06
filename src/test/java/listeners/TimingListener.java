package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TimingListener implements ISuiteListener{
    private long startTime;

    @Override
    public void onStart(ISuite suite) {
        startTime = System.currentTimeMillis();
        System.out.println("Suite execution started...");
    }

    @Override
    public void onFinish(ISuite suite) {
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;
        System.out.println("Suite execution finished.");
        System.out.println("Total execution time: " + duration + " seconds");
    }
}

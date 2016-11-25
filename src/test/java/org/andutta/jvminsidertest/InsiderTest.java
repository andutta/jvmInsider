package org.andutta.jvminsidertest;

/**
 * Created by anshumandutta on 11/21/16.
 */
public class InsiderTest {
    public void sleepMethod() throws InterruptedException {
        long sleepDuration = (long) (100 + Math.random() + 1000);
        System.out.printf ("sleeping for %d ms ..\n", sleepDuration);
        Thread.sleep(sleepDuration);
    }
}

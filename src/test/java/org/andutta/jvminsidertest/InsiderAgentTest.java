package org.andutta.jvminsidertest;

import org.junit.Test;

/**
 * Created by anshumandutta on 11/21/16.
 */
public class InsiderAgentTest {

    @Test
    public void testSleepMethodWhenClassInstantiated() throws InterruptedException {
        InsiderTest insiderTest = new InsiderTest();
        insiderTest.sleepMethod();
    }
}

package org.andutta.jvminsider;

import java.lang.instrument.Instrumentation;

/**
 * Created by anshumandutta on 11/20/16.
 */
public class InsiderAgent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Started JVM Insider agent \n");
        inst.addTransformer(new InsiderTransformer("org.andutta.jvminsidertest.InsiderTest","sleepMethod"));
    }
}

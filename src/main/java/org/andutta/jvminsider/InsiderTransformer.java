package org.andutta.jvminsider;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by anshumandutta on 11/20/16.
 */
public class InsiderTransformer implements ClassFileTransformer {

    private String forClass;
    private String forClassWithSlash;
    private String methodName;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;
        //System.out.println("Inside agent current class " + className + "\n");

        if (className.equals(forClassWithSlash)) {
            try {

                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(forClass);
                CtMethod m = cc.getDeclaredMethod(this.methodName);
                m.addLocalVariable("elapsedTime", CtClass.longType);
                m.insertBefore("elapsedTime = System.currentTimeMillis();");
                m.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                        + "System.out.println(\"Method Executed in ms: \" + elapsedTime);}");
                byteCode = cc.toBytecode();
                cc.detach();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byteCode;
    }

    public InsiderTransformer(String forClass, String methodName) {
        this.forClass = forClass;
        this.methodName = methodName;
        this.forClassWithSlash = forClass.replaceAll("\\.","/");
        System.out.println(forClass);
        System.out.println(methodName);
        System.out.println(forClassWithSlash);
    }
}

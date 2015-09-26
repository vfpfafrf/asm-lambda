package io.daydev.lambda;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;

import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;

/**
 * Created by dmitry on 26.09.15.
 */
public class ByteCodeUtils {


    static GeneratorAdapter createMethod(ClassVisitor cv, int access, String name, String desc) {
        return new GeneratorAdapter(cv.visitMethod(access, name, desc, null, null), access, name, desc);
    }

    static Method getDefineClass0() {
        try {
            Method m = Proxy.class.getDeclaredMethod("defineClass0", ClassLoader.class, String.class, byte[].class, int.class, int.class);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    static Handle findMetaFactoryHandle() {
        Method metafactory = null;
        for (Method m : LambdaMetafactory.class.getDeclaredMethods()) {
            if (m.getName().equals("metafactory")) {
                metafactory = m;
                break;
            }
        }
        return new Handle(H_INVOKESTATIC, Type.getInternalName(metafactory.getDeclaringClass()), metafactory.getName(), Type.getMethodDescriptor(metafactory));
    }

    /**
     * Functional interfaces can only have one abstract method, this this and return it.
     * If no methods are found, or multiple abstract methods are found, throws an IllegalArgumentException
     * @param iface Interface to find the method for
     * @return The method for the interface
     */
    static Method getInterfaceMethod(Class<?> iface) {
        if (iface.isInterface()) {
            Method func = null;

            for (Method m : iface.getMethods()) {
                if (Modifier.isStatic(m.getModifiers()) || !Modifier.isAbstract(m.getModifiers())) {
                    continue;
                }

                // Functional interfaces can only have one abstract method
                if (func != null) {
                    func = null;
                    break;
                }
                func = m;
            }
            if (func != null) {
                return func;
            }
        }

        throw new IllegalArgumentException(iface + " is not a functional interface");
    }
}

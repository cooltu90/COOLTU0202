package com.codingtu.cooltu.lib4j.tool;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;

public class ClassTool {

    public static boolean isObject(String name) {
        return isGivenClass(Object.class, name);
    }

    public static boolean isVoid(String name) {
        return isGivenClass(Void.class, name);
    }

    public static boolean isNotVoid(String name) {
        return StringTool.isNotBlank(name) && !ClassTool.isVoid(name);
    }

    public static boolean isString(String name) {
        return isGivenClass(String.class, name);
    }

    public static boolean isInt(String name) {
        return isGivenClass(int.class, name);
    }

    public static boolean isInteger(String name) {
        return isGivenClass(Integer.class, name);
    }

    public static boolean isDouble(String name) {
        return isGivenClass(double.class, name);
    }

    public static boolean isDOUBLE(String name) {
        return isGivenClass(Double.class, name);
    }

    public static boolean isFloat(String name) {
        return isGivenClass(float.class, name);
    }

    public static boolean isFLOAT(String name) {
        return isGivenClass(Float.class, name);
    }

    public static boolean isLong(String name) {
        return isGivenClass(long.class, name);
    }

    public static boolean isLONG(String name) {
        return isGivenClass(Long.class, name);
    }

    public static boolean isBoolean(String name) {
        return isGivenClass(boolean.class, name);
    }

    public static boolean isBOOLEAN(String name) {
        return isGivenClass(Boolean.class, name);
    }

    public static boolean isList(String name) {
        return name.startsWith(List.class.getCanonicalName());
    }

    private static boolean isGivenClass(Class cls, String name) {
        return cls.getCanonicalName().equals(name);
    }

    private static boolean isGivenClass(String cls, String name) {
        return cls.equals(name);
    }

    public static boolean isType(String type, Class... clazz) {
        for (int i = 0; i < CountTool.count(clazz); i++) {
            if (isGivenClass(clazz[i], type)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isType(String type, String... clazz) {
        for (int i = 0; i < CountTool.count(clazz); i++) {
            if (isGivenClass(clazz[i], type)) {
                return true;
            }
        }
        return false;
    }

    //    byte.class,
    //    short.class,
    //    char.class,
    //    int.class,
    //    long.class,
    //    float.class,
    //    double.class,
    //    boolean.class
    public static final Class[] BASE_CLASSES = new Class[]{
            String.class,
            Integer.class,
            int.class,
            Double.class,
            double.class,
            Float.class,
            float.class,
            Long.class,
            long.class,
            char.class,
            Character.class,
            short.class,
            Short.class,
            byte.class,
            Byte.class,
            Boolean.class,
            boolean.class
    };

    public static boolean isBaseClass(String classFullName) {
        for (int i = 0; i < BASE_CLASSES.length; i++) {
            if (BASE_CLASSES[i].getName().equals(classFullName)) {
                return true;
            }
        }
        return false;
    }

    public static String getAnnotationClass(AnnotationClassGetter getter) {
        String back = null;
        try {
            getter.get();
        } catch (MirroredTypeException mte) {
            back = mte.getTypeMirror().toString();
        }
        return back;
    }

    public static List<String> getAnnotationClasses(AnnotationClassGetter getter) {
        try {
            getter.get();
        } catch (MirroredTypesException mte) {
            List<? extends TypeMirror> typeMirrors = mte.getTypeMirrors();
            ArrayList<String> classNames = new ArrayList<String>();
            for (int i = 0; i < CountTool.count(typeMirrors); i++) {
                classNames.add(typeMirrors.get(i).toString());
            }
            return classNames;
        }
        return null;
    }

    public static String toPackageClassName(String k) {
        if (isInt(k)) {
            return Integer.class.getCanonicalName();
        }
        if (isBoolean(k)) {
            return Boolean.class.getCanonicalName();
        }
        if (isLong(k)) {
            return Long.class.getCanonicalName();
        }
        if (isFloat(k)) {
            return Float.class.getCanonicalName();
        }
        if (isDouble(k)) {
            return Double.class.getCanonicalName();
        }
        return k;
    }

    public static interface AnnotationClassGetter {
        Object get();
    }

    public static Class getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    public static String getClassFullName(String pkg, String simpleName) {
        return pkg + "." + simpleName;
    }

}

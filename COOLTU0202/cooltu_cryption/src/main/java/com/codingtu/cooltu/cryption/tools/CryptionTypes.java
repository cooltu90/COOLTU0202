package com.codingtu.cooltu.cryption.tools;


import com.codingtu.cooltu.cryption.types.Type0;
import com.codingtu.cooltu.cryption.types.Type1;

public class CryptionTypes {

    public static final int TYPE_1 = 1;
    public static final int TYPE_DEFAULT = TYPE_1;


    public static final int TYPE_LAST = TYPE_1;

    public static int lastType() {
        return TYPE_LAST;
    }

    public static Type0 getType(int type) {
        switch (type) {
            case CryptionTypes.TYPE_1:
                return new Type1();
        }
        return null;
    }

    public static Type0 getLastType() {
        return getType(TYPE_LAST);
    }

    public static Type0 getDefaultType() {
        return getType(TYPE_DEFAULT);
    }

}

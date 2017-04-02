package com.dd.dd_util;

import java.util.List;

public class ByteUtility {
    public static byte[] byteArrayFromByteList(List<Byte> list){
        byte[] outArr = null;
        if(list.size() > 0) {
            Byte[] tempArr = new Byte[list.size()];
            list.toArray(tempArr);
            outArr = new byte[list.size()];
            for (int i = 0; i < tempArr.length; i++) {
                outArr[i] = tempArr[i];
            }
        }
        return outArr;
    }
}

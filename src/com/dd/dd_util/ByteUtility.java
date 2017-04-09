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

    public static byte getBits(byte data, int numBits){
        if(numBits > 8 || numBits <= 0){
            throw new InvalidBitLengthException("");
        }
        data <<= (8 - numBits);
        return (byte)(data >> (8 - numBits));
    }

    public static byte getBits(byte data, int startBit, int numBits){
        int maxBit = startBit + numBits;
        if(maxBit > 8 || maxBit <= 0 || startBit <= 0){
            throw new InvalidBitLengthException("");
        }
        data <<= (8 - maxBit);
        data >>= (8 - maxBit + startBit);
        return (byte)data;
    }
}

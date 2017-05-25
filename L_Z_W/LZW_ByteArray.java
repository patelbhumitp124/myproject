/*
	
	Name				:	Bhumit Patel
	LID					:	L20382154
	Course				:	CPSC 5330_01_1 Adv. Multimedia Processing
	Instructor			: 	Prof. JIANGJIANG (JANE) LIU
	Assignment			:	Implement encoder and decoder for LZW compression algorithms using JAVA or C++.
	File Name			: 	LZW_ByteArray.java
	
*/
package L_Z_W;

import java.util.Arrays;

public class LZW_ByteArray {

    public static final int BITS_IN_BYTE = 8;
    public static final int BYTE_MASK = 255;

    private byte[] internal;

    public LZW_ByteArray() {
        internal = new byte[0];
    }

    public LZW_ByteArray(LZW_ByteArray another) {
        internal = another.internal.clone();
    }

    public LZW_ByteArray(byte[] array) {
        internal = array.clone();
    }

    public LZW_ByteArray(byte b1, byte... bytes) {
        int bytesSize = (bytes != null) ? bytes.length : 0;

        internal = new byte[bytesSize + 1];
        internal[0] = b1;
        for (int i = 1; i < internal.length; i++) {
            internal[i] = bytes[i - 1];
        }
    }

    public int size() {
        return internal.length;
    }

    public byte get(int index) {
        return internal[index];
    }

    public void set(int index, byte value) {
        internal[index] = value;
    }

    public LZW_ByteArray append(LZW_ByteArray another) {
        int size = size();
        int anotherSize = another.size();
        int newSize = size + anotherSize;
        byte[] newBuf = new byte[newSize];

        for (int i = 0; i < size; i++) {
            newBuf[i] = get(i);
        }
        for (int i = 0; i < anotherSize; i++) {
            newBuf[i + size] = another.get(i);
        }
        internal = newBuf;
        return this;
    }

    public LZW_ByteArray append(byte[] array) {
        return append(new LZW_ByteArray(array));
    }

    public LZW_ByteArray append(byte b1, byte... bytes) {
        return append(new LZW_ByteArray(b1, bytes));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LZW_ByteArray other = (LZW_ByteArray) obj;
        if (!Arrays.equals(internal, other.internal)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(internal);
        return result;
    }

    @Override
    public String toString() {
        return "ByteArray [internal=" + Arrays.toString(internal) + "]";
    }

    public static long GCD(long a, long b) {
        long tmp;

        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return Math.abs(a);
    }

    public static long LCM(long a, long b) {
        return Math.abs((a * b) / GCD(a, b));
    }
}

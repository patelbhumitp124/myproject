/*
	
	Name				:	Bhumit Patel
	LID					:	L20382154
	Course				:	CPSC 5330_01_1 Adv. Multimedia Processing
	Instructor			: 	Prof. JIANGJIANG (JANE) LIU
	Assignment			:	Implement encoder and decoder for LZW compression algorithms using JAVA or C++.
	File Name			: 	LZW_Input.java
	
*/
package L_Z_W;

import java.io.IOException;
import java.io.InputStream;

public class LZW_Input {

    /**
     * Backing input stream.
     */
    private InputStream in;

    /**
     * Length of code in bits.
     */
    private int codeWordLength;

    /**
     * Binary mask for clipping <code>codeWordLength</code> bits from integers
     * with binary operations.
     */
    private int mask;

    /**
     * Internal buffer for accumulating output.
     */
    private long buf; // 8 bytes (64 bits), i hope

    /**
     * Number of bits of internal buffer, actually used for storing codes.
     */
    private int bufUsageBits;

    /**
     * Number of bytes of internal buffer, actually used for storing codes.
     */
    private int bufUsageBytes;

    /**
     * Number of codes, that can be actually stored there.
     */
    private int bufUsageSymbols;

    /**
     * Number of codes actually stored in buf.
     */
    private int bufferedCodes;

    /**
     * End-of-file flag.
     */
    private boolean eof;

    public LZW_Input(InputStream in, int codeWordLength) {
        this.in = in;
        this.codeWordLength = codeWordLength;

        bufferedCodes = 0;
        buf = 0;
        bufUsageBits = (int) LZW_ByteArray.LCM(LZW_ByteArray.BITS_IN_BYTE, codeWordLength);
        bufUsageBytes = bufUsageBits / LZW_ByteArray.BITS_IN_BYTE;
        bufUsageSymbols = bufUsageBits / codeWordLength;
        mask = (1 << codeWordLength) - 1;
    }

    public int read() throws IOException {
        if ((bufferedCodes <= 0) && (!eof)) {
            buf = 0;
            for (int i = 0; i < bufUsageBytes; i++) {
                int read = in.read();
                if (-1 == read) {
                    // read = 0;
                    eof = true;
                }
                read = read & LZW_ByteArray.BYTE_MASK;
                read <<= i * LZW_ByteArray.BITS_IN_BYTE;
                buf |= read;
            }
            bufferedCodes = bufUsageSymbols;
        }
        if (bufferedCodes > 0) {
            int code = (int) (buf & mask);
            buf >>= codeWordLength;
            bufferedCodes--;
            if (code < (1 << codeWordLength) - 1) {
                return code;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public void close() throws IOException {
        in.close();
    }
}

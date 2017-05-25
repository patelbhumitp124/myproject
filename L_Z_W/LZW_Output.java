/*
	
	Name				:	Bhumit Patel
	LID					:	L20382154
	Course				:	CPSC 5330_01_1 Adv. Multimedia Processing
	Instructor			: 	Prof. JIANGJIANG (JANE) LIU
	Assignment			:	Implement encoder and decoder for LZW compression algorithms using JAVA or C++.
	File Name			: 	LZW_Output.java
	
*/
package L_Z_W;

import java.io.IOException;
import java.io.OutputStream;

public class LZW_Output {
	/**
	 * Backing output stream.
	 */
	private OutputStream out;

	/**
	 * Length of code in bits.
	 */
	int codeWordLength;

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
	 * Number of codes currently written to the backing stream.
	 */
	private int written;

	/**
	 * Number of bits of internal buffer, actually used for storing codes.
	 */
	private int bufUsageBits;

	/**
	 * Number of bytes of internal buffer, actually used for storing codes.
	 * 
	 * @see bufUsageBits
	 */
	private int bufUsageBytes;

	/**
	 * Number of codes, that can be actually stored there.
	 */
	private int bufUsageSymbols;

	public LZW_Output(OutputStream out, int codeWordLength) {
		this.out = out;
		this.codeWordLength = codeWordLength;

		written = 0;
		buf = 0;
		bufUsageBits = (int) LZW_ByteArray.LCM(LZW_ByteArray.BITS_IN_BYTE, codeWordLength);
		bufUsageBytes = bufUsageBits / LZW_ByteArray.BITS_IN_BYTE;
		bufUsageSymbols = bufUsageBits / codeWordLength;
		mask = (1 << codeWordLength) - 1;
	}

	public void write(int code) throws IOException {
		// Did you know, that Java is big-endian?
		code = (code & mask) << ((written) * codeWordLength);
		buf |= code;
		written++;
		if (written >= bufUsageSymbols) {
			for (int i = 0; i < bufUsageBytes; i++) {
				out.write((int) (buf & LZW_ByteArray.BYTE_MASK));
				buf >>= LZW_ByteArray.BITS_IN_BYTE;
			}
			written = 0;
			buf = 0;
		}
	}

	public void flush() throws IOException {
		while ((written < bufUsageSymbols) && (written != 0)) {
			write(-1);
		}
		out.flush();
	}

	public void close() throws IOException {
		out.close();
	}
}

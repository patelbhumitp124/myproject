/*
	
	Name				:	Bhumit Patel
	LID					:	L20382154
	Course				:	CPSC 5330_01_1 Adv. Multimedia Processing
	Instructor			: 	Prof. JIANGJIANG (JANE) LIU
	Assignment			:	Implement encoder and decoder for LZW compression algorithms using JAVA or C++.
	File Name			: 	LZW_Implementation.java
	
*/
package L_Z_W;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LZW_Implementation {
	public static final int INITIAL_DICT_SIZE = 256; // 0..255
	public static final int DEFAULT_CODEWORD_LENGTH = 12; // Bit

	private int codeWordLength = DEFAULT_CODEWORD_LENGTH;
	private Map<LZW_ByteArray, Integer> codingTable;
	private List<LZW_ByteArray> decodingTable;

	public int getCodeWordLength() {
		return codeWordLength;
	}

	public void compress(InputStream in, OutputStream out) throws IOException {
		init();

		int code = INITIAL_DICT_SIZE;

		InputStream bufferedIn = new BufferedInputStream(in);
		LZW_Output compressedOutput = new LZW_Output(new BufferedOutputStream(out),
				codeWordLength);

		int firstByte = bufferedIn.read();
		LZW_ByteArray w = new LZW_ByteArray((byte) firstByte);
		int K;

		while ((K = bufferedIn.read()) != -1) {
			LZW_ByteArray wK = new LZW_ByteArray(w).append((byte) K);
			if (codingTable.containsKey(wK)) {
				w = wK;
			} else {
				compressedOutput.write(codingTable.get(w));
				if (code < (1 << codeWordLength) - 1) {
					codingTable.put(wK, code++);
				}
				w = new LZW_ByteArray((byte) K);
			}
		}
		compressedOutput.write(codingTable.get(w));
		compressedOutput.flush();
		compressedOutput.close();
	}

	public void decompress(InputStream in, OutputStream out) throws IOException {
		init();

		LZW_Input compressedInput = new LZW_Input(new BufferedInputStream(in),
				codeWordLength);
		OutputStream bufferedOut = new BufferedOutputStream(out);

		int oldCode = compressedInput.read();
		bufferedOut.write(oldCode);
		int character = oldCode;
		int newCode;
		while ((newCode = compressedInput.read()) != -1) {
			LZW_ByteArray string;
			if (newCode >= decodingTable.size()) {
				string = new LZW_ByteArray(decodingTable.get(oldCode));
				string.append((byte) character);
			} else {
				string = decodingTable.get(newCode);
			}
			for (int i = 0; i < string.size(); i++) {
				bufferedOut.write(string.get(i));
			}
			character = string.get(0);
			decodingTable.add(new LZW_ByteArray(decodingTable.get(oldCode))
					.append((byte) character));
			oldCode = newCode;
		}

		bufferedOut.flush();
		bufferedOut.close();
	}

	private void init() {
		codingTable = new HashMap<LZW_ByteArray, Integer>();
		decodingTable = new ArrayList<LZW_ByteArray>();
		for (int i = 0; i < INITIAL_DICT_SIZE; i++) {
			codingTable.put(new LZW_ByteArray((byte) i), i);
			decodingTable.add(new LZW_ByteArray((byte) i));
		}
	}
}

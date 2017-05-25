import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCountSimple {
	// The name of the file to open.
	String inputFile = "Datafile2_128MB.txt";
	String outputFile = "output.txt";
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	Map<String, Integer> wordCounts = new TreeMap<String, Integer>();

	public void countWord(String stringToFind) {
		// This will reference one line at a time
		String line = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

			while ((line = bufferedReader.readLine()) != null) {
				// count occurrences
		           StringTokenizer tokenizer = new StringTokenizer(line);
		           while (tokenizer.hasMoreTokens()) {
		              String word = tokenizer.nextToken();
					if (!wordCounts.containsKey(word)) {
						wordCounts.put(word, 1);
					} else {
						wordCounts.put(word,
								wordCounts.get(word) + 1);
					}
				}
				Pattern p = Pattern.compile(stringToFind);
				Matcher m = p.matcher(line);
				while (m.find()) {
					if (!wordCounts.containsKey(stringToFind)) {
						wordCounts.put(stringToFind, 1);
					} else {
						wordCounts.put(stringToFind,
								wordCounts.get(stringToFind) + 1);
					}
				}
			}

			Set<Map.Entry<String, Integer>> entrySet = wordCounts.entrySet();
			String maxWord = "";
			int maxCount = 0;
			bufferedWriter.write("Words" + "\t\t" + "# of Occurances\n");
			for (Map.Entry<String, Integer> entry : entrySet) {
				bufferedWriter.write(entry.getKey() + "\t\t" + entry.getValue()
						+ "\n");
				if (entry.getValue() > maxCount) {
					maxWord = entry.getKey();
					maxCount = entry.getValue();
				}
			}
			bufferedWriter.write("------- High Frequency Word-----------\n ");
			bufferedWriter.write(maxWord + ":" + maxCount + "\n");

			// Always close files.
			bufferedWriter.close();
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file");
		} catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		/*if (args.length < 1) {
			System.out.println("usage: WordCountSimple StringToFind");
			System.exit(0);
		}*/
		WordCountSimple wc = new WordCountSimple();
		wc.countWord("The People");

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}

}

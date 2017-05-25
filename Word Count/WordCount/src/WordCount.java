import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
           
   public class WordCount extends Configured implements Tool{
           
    public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
       private final static IntWritable one = new IntWritable(1);
       private Text word = new Text();
           
       public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
           String line = value.toString();
           StringTokenizer tokenizer = new StringTokenizer(line);
           while (tokenizer.hasMoreTokens()) {
               word.set(tokenizer.nextToken());
               context.write(word, one);
           }
           Configuration conf = context.getConfiguration();
           
           String StringToFind=conf.get("StringToFind");
           Pattern p = Pattern.compile(StringToFind);
			Matcher m = p.matcher(line);
			while (m.find()) {
				word.set(StringToFind);
				context.write(word,one);
			}
       }
    } 
           
    public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    	
    	private Text tmpWord = new Text();
    	private int tmpFrequency = 0;
    	    
       public void reduce(Text key, Iterable<IntWritable> values, Context context) 
         throws IOException, InterruptedException {
           int sum = 0;
           for (IntWritable val : values) {
               sum += val.get();
           }
           context.write(key, new IntWritable(sum));
           //Check for high frequency word
           if(tmpFrequency < sum) {
        	   tmpFrequency = sum;
               tmpWord.set(key);  
            }
           
       }
    	
       @Override
       public void cleanup(Context context) throws IOException, InterruptedException {
       // write the word with the highest frequency
    	  String maxWord = tmpWord.toString();
			Text tempText = new Text(
				"------- High Frequency Word-----------\n"+ maxWord + ":");
           context.write(tempText, new IntWritable(tmpFrequency));
       }
    }
    
  
    
    @Override
    public int run(String[] args) throws Exception {
    	Configuration conf = this.getConf();
         
         
         if(args.length != 3){
      	   System.out.println("usage: InputFilePath OutputFilePath StringToFind");
      	   System.exit(0);
         }
         
         
         conf.set("StringToFind",args[2]);
             
         @SuppressWarnings("deprecation")
         Job job = new Job(conf, "wordcount");
         job.setJarByClass(WordCount.class);
         
         job.setOutputKeyClass(Text.class);
         job.setOutputValueClass(IntWritable.class);
             
         job.setMapperClass(Map.class);
         job.setReducerClass(Reduce.class);
         
         job.setInputFormatClass(TextInputFormat.class);
         job.setOutputFormatClass(TextOutputFormat.class);
        // job.setSortComparatorClass(IntComparator.class);
         job.setNumReduceTasks(1);
         
         FileInputFormat.addInputPath(job, new Path(args[0]));
         FileOutputFormat.setOutputPath(job, new Path(args[1]));
             
         return job.waitForCompletion(true) ? 0 : 1;
    }
           
   
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new WordCount(), args);
        System.exit(res);
    }
          
  }
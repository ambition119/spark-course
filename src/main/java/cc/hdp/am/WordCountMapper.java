package cc.hdp.am;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
 private Text out = new Text();
 private final IntWritable ONE = new IntWritable(1);
 @Override
 protected void map(LongWritable key, Text value,
   Context context)
   throws IOException, InterruptedException {
  String[] words = value.toString().split(" ");
  for (String word : words) {
   out.set(word);
   context.write(out, ONE);
  }
 }
}
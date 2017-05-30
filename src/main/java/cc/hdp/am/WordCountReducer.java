package cc.hdp.am;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
 private final IntWritable out = new IntWritable();
 @Override
 protected void reduce(Text key, Iterable<IntWritable> values,
   Context context)
   throws IOException, InterruptedException {
  int sum = 0;
  for (IntWritable value : values) {
   sum = sum + value.get();  //注意这里是value.get()
  }
  out.set(sum);
  context.write(key, out);
 }
}

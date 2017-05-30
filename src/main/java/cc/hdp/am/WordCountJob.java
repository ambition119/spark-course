package cc.hdp.am;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class WordCountJob extends Configured implements Tool {
 @Override
 public int run(String[] args) throws Exception {
  Job job = Job.getInstance(getConf(),"WordCountJob");
  Configuration conf = job.getConfiguration();
  job.setJarByClass(getClass());
 
 
  //设置文件输入输出
  Path in = new Path(args[0]);
  Path out = new Path(args[1]);
  //删除存在的目录
  out.getFileSystem(conf).delete(out, true);
  FileInputFormat.setInputPaths(job, in);
  FileOutputFormat.setOutputPath(job, out);
 
 
  //设置Mapper
  job.setMapperClass(WordCountMapper.class);
  job.setMapOutputKeyClass(Text.class);
  job.setMapOutputValueClass(IntWritable.class);
 
  //设置Reducer
  job.setReducerClass(WordCountReducer.class);
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(IntWritable.class);
 
  //设置文件格式
  job.setInputFormatClass(TextInputFormat.class);
  job.setOutputFormatClass(TextOutputFormat.class);
 
  return job.waitForCompletion(true)?0:1;
 }
 
 public static void main(String[] args) {
  int result = 0;
  try {
   result = ToolRunner.run(new Configuration(), new WordCountJob(),args);
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  System.exit(result);
 }
 
 //yarn jar /root/hdp/wordscount_2.jar cc.hdp.WordCountJob /exam/wordcount/constitution.txt /exam/wordcount/out
}
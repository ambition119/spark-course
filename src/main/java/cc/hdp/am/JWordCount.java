package cc.hdp.am;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class JWordCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("JWordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("/user/root/spark/file_in/README.md");

        JavaRDD<String> flatMapRDD = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String line) throws Exception {
                String[] arrs = line.split(" ");
                return Arrays.asList(arrs);
            }
        });

        JavaPairRDD<String, Integer> pairRDD = flatMapRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String str) throws Exception {
                return new Tuple2<String, Integer>(str, 1);
            }
        });

        JavaPairRDD<String, Integer> reduceByKey = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        reduceByKey.saveAsTextFile("/user/root/spark/file_out/Scala_WordCount");

        sc.stop();
    }

    public void sayName(String firstName,String midName,String lastName) {
        if(firstName == null || firstName == "") {
            firstName = "defaultName";
        }
        if (midName == null || midName == ""){
            midName = "Martin";
        }
        if (lastName == null || lastName == ""){
            lastName = "Odersky";
        }
        System.out.println(firstName + " " + midName + " " + lastName);
    }

    public void sayWords(String ... words){
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i] + "  ");
        }
    }

}

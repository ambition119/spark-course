package cc.hdp.dm

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/5 0005.
  */
object CreateRdd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CreateRdd").setMaster("local")
    val sc = new SparkContext(conf)

    //1.并行集合创建
    val nums = Array(1,2,3,4,5,6,7,8,9)
    //第二个表示创建3个partition
//    val numRDD = sc.parallelize(nums);
    val numRDD = sc.parallelize(nums,3);

    //    spark会为每个partition运行一个task进行处理，Spark官方建议是集群中每个CPU创建2-4个partition
//    println(numRDD.count())

    //2.本地文件创建
//    val localRdd = sc.textFile("F:\\vm_linux\\bigdata\\vmware.log",3)
    val textFile: RDD[String] = sc.textFile("file:\\F:\\vm_linux\\bigdata\\vmware.log",3)
//    val localRdd = sc.textFile("file://F:\\vm_linux\\bigdata\\vmware.log",3)  error
//    println(textFile.collect()(0))

    //3.hdfs文件创建
    //默认使用的是hdfs文件
//    val file: RDD[String] = sc.textFile("/user/root/spark/file_in/README.md")

    //4.其他，如hbase
//     val config: Configuration = HBaseConfiguration.create()
//     config.set("hbase.zookeeper.quorum","sandbox.hortonworks.com")
//    config.set("hbase.zookeeper.property.clientPort", "2181")
//    config.set(TableInputFormat.INPUT_TABLE, "iemployee")
//    val connection:Connection = ConnectionFactory.createConnection(config)
//    val admin: Admin = connection.getAdmin()

//    val hbaseRdd = sc.newAPIHadoopRDD(config, classOf[TableInputFormat],classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],classOf[org.apache.hadoop.hbase.client.Result])
//     println(hbaseRdd.count())

//    hbaseRdd.foreach{case (_,result) =>{
      //获取行键
//      val key = Bytes.toString(result.getRow)
      //通过列族和列名获取列
//      val insuranceDental = Bytes.toString(result.getValue(Bytes.toBytes("insurance"),Bytes.toBytes("dental")))
//      val skillsManagement = Bytes.toString(result.getValue(Bytes.toBytes("skills"),Bytes.toBytes("management")))
//      println("Row key:"+key+" insuranceDental: "+insuranceDental+" skillsManagement: "+skillsManagement)
//    }}
  }
}

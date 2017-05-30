package cc.hdp.ml

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionModel, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/10/3 0003.
  */
object LinearRegressionWithSGDExample {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("LinearRegressionWithSGDExample").setMaster("local")
    val sc = new SparkContext(conf)

    //Load and parse the data
    val data = sc.textFile("G:\\code\\scala\\spark_app\\src\\main\\resources\\cc.hdp.ml.data\\ridge-data\\lpsa.data")
    val parsedData = data.map{ line =>
      val parts = line.split(",")
      LabeledPoint(parts(0).toDouble,Vectors.dense(parts(1).split(" ").map(_.toDouble)))
    }.cache()

    //建模
    val numIterations = 100
    val stepSize = 0.00000001
    val model = LinearRegressionWithSGD.train(parsedData,numIterations,stepSize)

    //模型训练和评估
    val valuesAndPreds = parsedData.map{ point =>
      val prediction = model.predict(point.features)
      (point.label,prediction)
    }
//    均方误差（Mean Squared Error, MSE）是衡量“平均误差”的一种较方便的方法，可以评价数据的变化程度。均方根误差是均方误差的算术平方根。MSE的值越小，说明预测模型描述实验数据具有更好的精确度。
    val MSE = valuesAndPreds.map{
      case(v,p) => math.pow((v - p),2)
    }.mean()
    println("training Mean Squared Error = " + MSE)

    //模型保存和加载,在hdfs路径
    model.save(sc,"G:\\code\\scala\\spark_app\\src\\main\\resources\\cc.hdp.ml.data\\target\\tmp\\scalaLinearRegressionWithSGDModel")
    val sameModel = LinearRegressionModel.load(sc,"G:\\code\\scala\\spark_app\\src\\main\\resources\\cc.hdp.ml.data\\target\\tmp\\scalaLinearRegressionWithSGDModel")

    sc.stop()
  }
}

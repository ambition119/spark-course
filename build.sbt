import scala.collection.JavaConverters._

name := "spark_app"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "repo" at "http://repo.typesafe.com/typesafe/releases/",
  // "apache" at "https://repository.apache.org/content/repositories/releases",
  // HTTPS is unavailable for Maven Central
  "Maven Repository"     at "http://repo.maven.apache.org/maven2",
  "Apache Repository"    at "https://repository.apache.org/content/repositories/releases",
  "JBoss Repository"     at "https://repository.jboss.org/nexus/content/repositories/releases/",
  "MQTT Repository"      at "https://repo.eclipse.org/content/repositories/paho-releases/",
  "Cloudera Repository"  at "http://repository.cloudera.com/artifactory/cloudera-repos/",
  "Hortonworks Repository"  at "http://repo.hortonworks.com/content/repositories/releases/",
  "le_bigdata_mining"    at "http://10.150.144.28/nexus/content/repositories/releases/"
)

libraryDependencies ++= {
  val sversion = "1.6.3"
  Seq(
    "org.apache.spark" % "spark-core_2.11" % sversion,
    "org.apache.spark" % "spark-sql_2.11" % sversion,
    "org.apache.spark" % "spark-streaming_2.11" % sversion,
    "org.apache.spark" % "spark-mllib_2.11" % sversion
//     "mysql" % "mysql-connector-java" % "5.1.39"
//    "org.apache.hbase" % "hbase-client" % "1.1.1",
//    "org.apache.hbase" % "hbase-common" % "1.1.1",
//    "org.apache.hbase" % "hbase-server" % "1.1.1"
  )

}


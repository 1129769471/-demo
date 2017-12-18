package day04.demo

import akka.actor.Actor

import scala.io.Source

class Worker extends Actor {
  override def receive: Receive = {

    case Task(file) => {

      //读取文件，然后进行单词计数
      val wordAndCounts: Map[String, Int] = Source.fromFile(file).getLines()
        .map(_.split("\\|")(3).split(" ")(1).split("\\/")(3))
        .toList.map((_,1))
        .groupBy(_._1)
        .mapValues(_.length)
      //将结果返回给发送者
      sender() ! Result(wordAndCounts)
    }
  }
}

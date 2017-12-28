package scalapractice


  package sparkrddpackage
  import org.apache.log4j.{Level, Logger}
  import org.apache.spark.SparkConf
  import org.apache.spark.SparkContext
  import org.apache.spark.rdd.RDD
  //import sparkrddpackage.RddExercise.{args, helpdeskRows}

  object RddExercise {


    def getPivotData(helpdeskRows: RDD[HelpDesk], arg: String): Unit ={
      for(i <- helpdeskRows){

      }
      helpdeskRows.filter(x => x.equals())
      arg match{
        case "priority" => {
          helpdeskRows.map(x=>(x.priority,1)).reduceByKey(_+_).collect().foreach(println(_))
          //helpdeskRows.filter(x => x.priority=="0 - Unknown").count()
        }
        case "satisfaction" => {
          //helpdeskRows.map(x=>(x.satisfaction,1)).reduceByKey(_+_).collect().foreach(println(_))
          val count1 = helpdeskRows.filter(x => x.satisfaction=="0 - Unknown").count()
          println("0 - Unknown,"+count1)
          val count2 = helpdeskRows.filter(x => x.satisfaction=="1 - Unsatisfied").count()
          println("1 - Unsatisfied,"+count2)
          val count3 = helpdeskRows.filter(x => x.satisfaction=="2 - Satisfied").count()
          println("2 - Satisfied,"+count3)
          val count4 = helpdeskRows.filter(x => x.satisfaction=="3 - Highly satisfied").count()
          println("3 - Highly satisfied,"+count4)
          val finalcount = helpdeskRows.count()
          println("Total,"+finalcount)
        }
        case "ticketType" => helpdeskRows.map(x=>(x.ticketType,1)).reduceByKey(_+_).collect().foreach(println(_)) } }


    case class HelpDesk(ticket:Int,
                        requestor:Int,
                        requestorSeniority:String,
                        itOwner:Int,
                        filedAgainst:String,
                        ticketType:String,
                        severity:String,
                        priority:String,
                        daysOpen:Int,
                        satisfaction:String)
    def getRows(rows:String): HelpDesk={

      val cols = rows.split(',')
      val helpDesk:HelpDesk = new HelpDesk(cols(0).toInt,cols(1).toInt,cols(2),cols(3).toInt,cols(4),cols(5),cols(6),cols(7),cols(8).toInt,cols(9))
      helpDesk
    }


    // def getMaxDays(arg1: Any,)

    def main(args: Array[String]): Unit ={
      Logger.getLogger("org").setLevel(Level.ERROR)
      val conf = new SparkConf().setMaster("local[*]").setAppName("Transformation Demo")
      val sc = new SparkContext(conf)
      val inputPath = "E:/Bhanu_Spark_and_Scala/IT-HELP-DESK.csv" //args(0)
      val outputPath = "E:/Bhanu_Spark_and_Scala/helpdesk-output2" //args(1)
      val helpdeskFile = sc.textFile(inputPath)
      val helpDeskHeader = helpdeskFile.first()
      val helpdeskData = helpdeskFile.filter(row => row != helpDeskHeader)
      val helpdeskRows = helpdeskData.map(getRows)
      println("Count: "+helpdeskRows.filter(row => row.severity=="4 - Critical" & row.priority=="3 - High").count())
      val criticalPriorityRows = helpdeskRows.filter(row => row.severity=="4 - Critical" & row.priority=="3 - High")
      val criticalrdd = criticalPriorityRows.map(x => (x.ticket,x.requestor,x.requestorSeniority,x.itOwner,x.filedAgainst,x.ticketType,x.severity,x.priority,x.daysOpen,x.satisfaction))
      criticalrdd.saveAsTextFile(outputPath)
      //criticalPriorityRows.saveAsTextFile(outputPath)
      val highSatisfiedTickets = helpdeskRows.filter(.satisfaction=="3 - Highly satisfied").count()
      println("Number of highly satisfied tickets:" + highSatisfiedTickets)
      // max number of days opened ticket in highly satisfied tickets
      val maxDaysHighSatisfied = helpdeskRows.filter(.satisfaction=="3 - Highly satisfied").map(.daysOpen).max()
      println("Maximum number of days ticket in highly satisfied tickets:" + maxDaysHighSatisfied)

      / helpdeskRows.map(getMaxDays(arg1))
      Pivot table logic*/
      val pivotData = helpdeskRows.map(x=>(x.priority,1)).reduceByKey(_+_)
      pivotData.collect().foreach(println)
      / val arg = "satisfaction"
      this.getPivotData(helpdeskRows,arg)*/

      /* val args = "RequesterSeniority"
       this.seniorityOfDaysOpenavg(helpdeskRows,args)*/

      //Average of daysopen Per Seniority wise
      /*val seniorityDaysopenRdd = helpdeskData.map(x => (x.split(",")(2),x.split(",")(8))).filter(x => x.1 == "1 - Junior") //.collect().take(5).foreach(println)//map(x => (x,(x.2.sum,x.2.count)).//reduceByKey(_+_).collect().foreach(println)
      val count4 = seniorityDaysopenRdd.count().println()*/
      val seniorityDaysOpenCount = helpdeskRows.filter(.requestorSeniority=="1 - Junior").map(.daysOpen).count()
      println("The count: "+seniorityDaysOpenCount)
      val seniorityDaysOpenSum = helpdeskRows.filter(.requestorSeniority=="1 - Junior").map(.daysOpen).sum()
      println("The sum: "+seniorityDaysOpenSum)
      val seniorityDaysOpenAvg = (seniorityDaysOpenSum/seniorityDaysOpenCount).toFloat
      println("The Avg: "+seniorityDaysOpenAvg)
      //val a = helpdeskRows.filter(.requestorSeniority=="1 - Junior").map(x => (x.requestorSeniority,x.daysOpen)).mapValues(x => (x,1)).reduce.collect().take(5).foreach(println)
      //using combineByKey
      val seniorityDaysOpenTuple = helpdeskRows.map(x => (x.requestorSeniority,x.daysOpen))
      val senirityDaysOpenAverage = seniorityDaysOpenTuple.combineByKey((v) => (v, 1),
        ((acc: (Int, Int), v) => (acc.1 + v, acc.2 + 1)),
      ((acc1: (Int, Int), acc2: (Int, Int)) => (acc1.1+acc2.1, acc1.2+acc2.2)))//.collect().take(5).foreach(println)
      val result = senirityDaysOpenAverage.map{ case (key, value) => (key,value.1 / value._2.toFloat) }.sortByKey().take(5).foreach(println)//.collect().foreach(println)
      //println("The average of days open per seniory Level: "+result)
      //val a = helpdeskData.map(x => x.split(",")(9)).map(x=>(x,1)).reduceByKey(_+_).collect().foreach(println(_))
      //val testing = helpdeskData.map(x => x.split(",")



    }



  }

}

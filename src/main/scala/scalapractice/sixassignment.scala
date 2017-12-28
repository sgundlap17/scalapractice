package scalapractice


object sixassignment {
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


    def getPivotData(helpdeskRows: RDD[HelpDesk], arg: String): Unit ={
      arg match{
        case "priority" => helpdeskRows.map(x=>(x.priority,1)).reduceByKey(_+_).collect().foreach(println(_))
        case "satisfaction" => helpdeskRows.map(x=>(x.satisfaction,1)).reduceByKey(_+_).collect().foreach(println(_))
        case "ticketType" => helpdeskRows.map(x=>(x.ticketType,1)).reduceByKey(_+_).collect().foreach(println(_))
      }
    }

    def main(args:Array[String]): Unit ={
      //    Set log information to error
      Logger.getLogger("org").setLevel(Level.ERROR)
      //    Create SparkConf object
      val conf = new SparkConf().setMaster("local[*]").setAppName("Transformation Demo")
      //    Create SparkContext object
      val sc = new SparkContext(conf)
      //    Input and Output path
      val inputPath = "E:/Hadoop/IT-HELP-DESK.csv" //args(0)
      val outputPath = "E:/Hadoop/helpdesk-output1" //args(1)
      //    Read input file
      val helpdeskFile = sc.textFile(inputPath)
      //    Remove header
      val helpDeskHeader = helpdeskFile.first()
      val helpdeskData = helpdeskFile.filter(row => row != helpDeskHeader)
      //    Assign schema to rows
      val helpdeskRows = helpdeskData.map(getRows)

      //     Generate critical priority tickets
      //    println(helpdeskRows.filter(row => row.severity=="4 - Critical" & row.priority=="3 - High").count())
      val criticalPriorityRows = helpdeskRows.filter(row => row.severity=="4 - Critical" & row.priority=="3 - High")
      criticalPriorityRows.saveAsTextFile(outputPath)

      //     Count of highly satisfied tickets
      val highSatisfiedTickets = helpdeskRows.filter(_.satisfaction=="3 - Highly satisfied").count()
      println("Number of highly satisfied tickets:" + highSatisfiedTickets)

      //     max number of days opened ticket in highly satisfied tickets
      val maxDaysHighSatisfied = helpdeskRows.filter(_.satisfaction=="3 - Highly satisfied").map(_.daysOpen).max()
      println("Maximum number of days ticket in highly satisfied tickets:" + maxDaysHighSatisfied)

      //     Pivot table logic
      //     val pivotData = helpdeskRows.map(x=>(x.priority,1)).reduceByKey(_+_)
      //     pivotData.collect().foreach(println)
      val arg = "ticketType"
      this.getPivotData(helpdeskRows,arg)
    }


}

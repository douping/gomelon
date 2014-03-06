import play.api._

import models._
import anorm._

import com.mongodb.casbah.commons.Imports._
import org.bson.types.ObjectId

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {
    InitialData.insert()
  }
  
}

/**
 * Initial set of data to be imported 
 * in the sample application.
 */
object InitialData {
  
  def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)
  
  def insert() = {
    
    if(Salon.findAll == Nil) { 
      Seq(
        Salon(new ObjectId("530d7288d7f2861457771bdd"), "火影忍者发型"),
        Salon(new ObjectId("530d7292d7f2861457771bde"), "海贼王发型")
      ).foreach(Salon.save)
      
      Seq(
        Stylist(new ObjectId("530d8010d7f2861457771bf8"), "漩涡鸣人", new ObjectId("530d7288d7f2861457771bdd")),
        Stylist(new ObjectId("530d8010d7f2861457771bf9"), "宇智波佐助", new  ObjectId("530d7288d7f2861457771bdd")),
        Stylist(new ObjectId("530d8010d7f2861457771bfa"), "宇智波带土", new ObjectId("530d7288d7f2861457771bdd")),
        Stylist(new ObjectId("530d8010d7f2861457771bfb"), "宇智波斑", new ObjectId("530d7288d7f2861457771bdd")),
        Stylist(new ObjectId("530d8010d7f2861457771bfc"), "千手柱间", new ObjectId("530d7288d7f2861457771bdd")),

        Stylist(new ObjectId("530d8010d7f2861457771bfd"), "路飞", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771bfe"), "索隆", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771bff"), "香吉士", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c00"), "娜美", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c01"), "乔巴", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c02"), "罗宾", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c03"), "乌索普", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c04"), "弗兰奇", new ObjectId("530d7292d7f2861457771bde")),
        Stylist(new ObjectId("530d8010d7f2861457771c05"), "布鲁克", new ObjectId("530d7292d7f2861457771bde"))
      ).foreach(Stylist.save)
      
      Seq(
        Style(new ObjectId("530d828cd7f2861457771c0b"), "四代火影发型式", new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("530d8010d7f2861457771bf8")),
        Style(new ObjectId("530d828cd7f2861457771c0c"), "六道仙人发型", new ObjectId("530d7288d7f2861457771bdd"), new ObjectId("530d8010d7f2861457771bf8")),
        Style(new ObjectId("530d828cd7f2861457771c0d"), "海贼王发型", new ObjectId("530d7288d7f2861457771bde"), new ObjectId("530d8010d7f2861457771bfd"))
      ).foreach(Style.save)

    }
  }
  
}

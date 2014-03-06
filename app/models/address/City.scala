package models

import util.MongodbColl
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.MongoConnection

object City {

  def main(args: Array[String]): Unit = {
    val db = MongodbColl.getDB()
	val coll = db("city")
	 coll.insert(MongoDBObject("provice" -> "江苏", "citys" -> "南京"))
	 val citys=coll.find(MongoDBObject("provice" -> "江苏"))
	 if(citys.hasNext)
	   println(citys.next)
  }
  	
}
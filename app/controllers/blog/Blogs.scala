package controllers

import play.api._
import java.util.Date
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.templates._
import models._
import com.mongodb.casbah.Imports.ObjectId


object Blogs extends Controller {
  
    
  val formAddComment = Form((
    "content" -> nonEmptyText
  ))
  
  val formHuifuComment = Form((
    "content" -> text
  ))
  

    
  val formBlog = Form(tuple(
    "title" -> nonEmptyText,   
    "content" -> nonEmptyText,
    "blogTyp" -> nonEmptyText,
    "tags" -> nonEmptyText
  ))

  
  def newBlog(userId : ObjectId) = Action {
    // list是一个blog分类的list
//    val list = Blog.getCatagories(userId)
    val list = BlogCatagory.getCatagory(userId)
    Ok(views.html.blog.blog(userId, formBlog, list))
  }
  
  def deleteBlog(id : ObjectId) = Action {
     implicit request =>
      val user_id = request.session.get("user_id").get
      val userId = new ObjectId(user_id)
    Blog.delete(id)
    Redirect(routes.Blogs.showBlog(userId))
  }
  
  // 这是数据库中用户的ObjectId
  def test = Action {
    val userId = new ObjectId("5315b707a89edbf29369926c")
    val list = Blog.findByUserId(userId)
    // 目前传递的是username，可能需要传真实姓名或者是昵称，待完善
    val name = User.getUsername(userId)
//    println("---------"+list(0).createdTime.toLocaleString.formatted("YY/MM/DD"))
//    println("---------"+list(0).createdTime.toLocaleString())
    // 这边时间的format需要调整，目前的格式是2014/03/05 9:04:08，，，，可能需要调整
    Ok(views.html.blog.blogTest(name, list))
  }
  
  
  /**
   * 这个参数是userid的意思 showBlogByUserId
   */
  def showBlog(id : ObjectId) = Action {
    val list = Blog.showBlog(id)
    Ok(views.html.blog.findBlogs(id, list))
  }
  
  /**
   * 通过blog的id找到blog
   */
  def showBlogById(id : ObjectId) = Action {
    val list = Blog.showBlogById(id)
    Ok(views.html.blog.findBlogs(id, list))
  }
  
  def writeBlog(id : ObjectId) = Action {
    
      implicit request =>
      formBlog.bindFromRequest.fold(
        //处理错误
        errors => BadRequest(views.html.blog.blog(id, errors, BlogCatagory.getCatagory(id))),
        {
          case (title,content,blogTyp,tags) => 
	        Blog.newBlog(id, title,content,blogTyp,tags)
	        Ok(views.html.blog.showBlog(id))
        }
                
          
            
        )
  }
  
}
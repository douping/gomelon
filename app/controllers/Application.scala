package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.Form

object Application extends Controller {
	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

}

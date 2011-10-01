package $package$
package snippet

import net.liftweb._
import common._
import http.{LiftRules, S}
import sitemap.SiteMap
import util.Helpers._

object GroupMenu {
  def render = {
    (for {
      group <- S.attr("group") ?~ "Group not specified"
      sitemap <- LiftRules.siteMap ?~ "Sitemap is empty"
      request <- S.request ?~ "Request is empty"
      curLoc <- request.location ?~ "Current location is empty"
    } yield {
      val currentClass = S.attr("current_class").openOr("current")
      sitemap.locForGroup(group) flatMap { loc =>
        if (curLoc.name == loc.name)
          <li class={currentClass}>{SiteMap.buildLink(loc.name)}</li>
        else
          <li>{SiteMap.buildLink(loc.name)}</li>
      }
    }) match {
      case Full(html) => html
      case Failure(msg, _, _) => "*" #> "ERROR: %s".format(msg)
      case Empty => "*" #> "Unknown Error"
    }
  }
}

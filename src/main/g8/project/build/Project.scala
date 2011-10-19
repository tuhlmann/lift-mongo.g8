import sbt._

import untyped.{ClosureCompilerPlugin, LessCssPlugin}

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) with ClosureCompilerPlugin with LessCssPlugin {
  lazy val isAutoScan = systemOptional[Boolean]("autoscan", false).value
  val liftVersion = "$lift_version$"

  lazy val scalatestVersion = Map("2.8.0" -> "1.3", "2.8.1" -> "1.5.1", "2.8.2" -> "1.5.1").getOrElse(buildScalaVersion, "1.6.1")
  lazy val specsVersion = Map("2.8.0" -> "1.6.5", "2.9.1" -> "1.6.9").getOrElse(buildScalaVersion, "1.6.8")

  // uncomment the following if you want to use the snapshot repo
  //val scalatoolsSnapshot = ScalaToolsSnapshots

  override def classpathFilter = super.classpathFilter -- "*-sources.jar"
  override def scanDirectories = if (isAutoScan) super.scanDirectories else Nil

  // Lift
  lazy val lift_mongodb = "net.liftweb" %% "lift-mongodb-record" % liftVersion

  // misc
  lazy val lift_auth_mongo = "com.eltimn" %% "lift-auth-mongo" % "0.1-SNAPSHOT"
  lazy val logback = "ch.qos.logback" % "logback-classic" % "0.9.26"

  // test-scope
	lazy val scalatest = "org.scalatest" %% "scalatest" % scalatestVersion % "test"
  lazy val specs = "org.scala-tools.testing" %% "specs" % specsVersion % "test->default"
	lazy val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.22" % "test"

  // provided-scope
  lazy val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"

	// google-closure plugin
	override def closureSourcePath: Path = "src" / "main" / "javascript"

	// less.css plugin
	override def lessSourceFilter: NameFilter = filter("styles.less") // only compile the main bootstrap file
	override def lessSourcePath: Path = "src" / "main" / "less"

	// Initialize Boot by default
  override def consoleInit =
    """
      |import bootstrap.liftweb.Boot
      |
      |val b = new Boot
      |b.boot
      |
    """.stripMargin
}

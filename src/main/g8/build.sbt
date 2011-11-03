name := "$name$"

organization := "$sbt_organization$"

version := "$project_version$"

scalaVersion := "$scala_version$"

resolvers += ScalaToolsSnapshots

resolvers += "Liftmodules repo" at "https://repository-liftmodules.forge.cloudbees.com/release"

{
  val liftVersion = "$lift_version$"

  libraryDependencies ++= Seq(
    "net.liftweb" %% "lift-mongodb-record" % liftVersion,
    "net.liftmodules" %% "mongoauth" % liftVersion+"-0.1",
    "ch.qos.logback" % "logback-classic" % "1.0.0",
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
  )
}

scalacOptions += "-deprecation"

seq(lessSettings:_*)

(LessKeys.filter in (Compile, LessKeys.less)) := "*styles.less"

(sourceDirectory in (Compile, LessKeys.less)) <<= (sourceDirectory in Compile)(_ / "less")

seq(jsSettings:_*)

(JsKeys.filter in (Compile, JsKeys.js)) := "*.jsm"

(sourceDirectory in (Compile, JsKeys.js)) <<= (sourceDirectory in Compile)(_ / "javascript")

seq(webSettings :_*)

// add managed resources, where less and closure publish to, to the webapp
(webappResources in Compile) <+= (resourceManaged in Compile)

// make compile depend on less and closure
(compile in Compile) <<= compile in Compile dependsOn (JsKeys.js in Compile, LessKeys.less in Compile)

checksums := Nil  // Lift snapshot checksums are bad
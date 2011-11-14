resolvers += "Untyped Public Repo" at "http://repo.untyped.com"

addSbtPlugin("untyped" % "sbt-closure" % "0.6-SNAPSHOT")

resolvers += "less is" at "http://repo.lessis.me"

addSbtPlugin("me.lessis" % "less-sbt" % "0.1.2")

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.8"))

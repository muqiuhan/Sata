val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "sata",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    unmanagedBase := baseDirectory.value / "lib",
    scalacOptions ++= Seq("-deprecation", "-feature"),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "com.microsoft.onnxruntime" % "onnxruntime" % "1.12.1"
  )

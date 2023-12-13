package com.muqiuhan.sata.image.object_detection.input

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.highgui.HighGui
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

import java.io.File
import java.nio.file.InvalidPathException

class Transform(image: Mat) extends sata.Transform[Mat, Mat](image):
  val letterbox: LetterBox = LetterBox(image)

  override def trans(): Mat =
    val image = letterbox.trans()
    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB)
    image.clone()

case class Input(source: String) extends sata.Input[String, Mat](source):

  val image: Mat =
    if File(source).exists() then
      Imgcodecs.imread(source)
    else
      throw InvalidPathException(source, "Cannot find it")

  val transform: Transform = Transform(image)

  override def input(): Mat = transform.trans()

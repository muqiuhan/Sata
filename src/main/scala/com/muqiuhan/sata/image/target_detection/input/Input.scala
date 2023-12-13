package com.muqiuhan.sata.image.target_detection.input

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

import java.io.File
import java.nio.file.InvalidPathException

class Transform(source: Mat) extends sata.Transform[Mat, Mat](source):
  override def trans(): Mat =
    val image = LetterBox(source).trans()
    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB)
    image.clone()

class Input(transform: Transform) extends sata.Input[String, Mat](transform):
  override def input(source: String): Mat =
    if File(source).exists() then
      Imgcodecs.imread(source)
    else
      throw InvalidPathException(source, "Cannot find it")

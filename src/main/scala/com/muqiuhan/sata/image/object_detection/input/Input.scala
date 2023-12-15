/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.input

import com.muqiuhan.sata
import com.muqiuhan.sata.Transform
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

import java.io.File
import java.nio.file.InvalidPathException

case class Input(source: String) extends sata.Input[String, Mat](source):

  val image: Mat =
    if File(source).exists() then
      Imgcodecs.imread(source)
    else
      throw InvalidPathException(source, "Cannot find it")

  val letterbox: LetterBox = LetterBox(image)

  override def input(transform: Transform[Mat, Mat]): Mat =
    val image = letterbox.trans()
    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB)
    image.clone()

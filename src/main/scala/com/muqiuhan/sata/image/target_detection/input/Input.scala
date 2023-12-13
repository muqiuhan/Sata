package com.muqiuhan.sata.image.target_detection.input

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs

import java.io.File
import java.nio.file.InvalidPathException

class Input(transform: LetterBox) extends sata.Input[String, Mat](transform):
  override def input(source: String): Mat =
    if File(source).exists() then Imgcodecs.imread(source)
    else throw InvalidPathException(source, "Cannot find it")

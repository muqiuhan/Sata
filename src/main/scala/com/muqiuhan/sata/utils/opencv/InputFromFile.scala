package com.muqiuhan.sata.utils.opencv

import com.muqiuhan.sata
import com.muqiuhan.sata.Transform
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs

import java.io.File
import java.nio.file.InvalidPathException

trait InputFromFile(source: String) {
  val image: Mat = {
    if (File(source).exists()) {
      Imgcodecs.imread(source)
    } else {
      throw InvalidPathException(source, "Cannot find it")
    }
  }
}

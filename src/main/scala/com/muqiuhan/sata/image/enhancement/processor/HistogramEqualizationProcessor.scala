package com.muqiuhan.sata.image.enhancement.processor

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

import java.util

class HistogramEqualizationProcessor(image: Mat) extends sata.Processor[Mat, Mat](image) {
  override def process(): Mat = {
    val imageRGB = new util.ArrayList[Mat]()
    Core.split(image, imageRGB)

    for (i <- 0 until 3) {
      Imgproc.equalizeHist(imageRGB.get(i), imageRGB.get(i))
    }

    Core.merge(imageRGB, image)
    image
  }
}

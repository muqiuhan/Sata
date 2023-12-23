package com.muqiuhan.sata.utils.opencv

import org.opencv.core.*
import org.opencv.highgui.HighGui

trait OutputWithShow(image: Mat, title: String = "Display Image") {
  def output(): Unit = {
    HighGui.imshow(title, image)
    HighGui.waitKey()
  }
}

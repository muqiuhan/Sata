package com.muqiuhan.sata.utils.opencv

import org.opencv.core.*
import org.opencv.highgui.HighGui

trait OutputWithShow(image: Mat) {
  def output(): Unit = {
    HighGui.imshow("Display image", image)
    HighGui.waitKey()
  }
}

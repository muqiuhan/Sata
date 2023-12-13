package com.muqiuhan.sata.image.object_detection.output

import org.opencv.core.*
import com.muqiuhan.sata
import org.opencv.highgui.HighGui

class ShowImage(input: Mat) extends sata.Output[Mat, Unit](input):
  override def output(): Unit = HighGui.imshow("Display image", input)

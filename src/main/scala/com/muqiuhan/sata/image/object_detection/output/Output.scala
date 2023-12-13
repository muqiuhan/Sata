package com.muqiuhan.sata.image.object_detection.output

import org.opencv.core.*
import com.muqiuhan.sata

class Output(input: Mat) extends sata.Output[Mat, Unit](input):
  override def output(): Unit = ShowImage(input).output()

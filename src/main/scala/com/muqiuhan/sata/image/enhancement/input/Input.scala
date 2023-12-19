package com.muqiuhan.sata.image.enhancement.input

import com.muqiuhan.sata
import com.muqiuhan.sata.utils
import com.muqiuhan.sata.Transform
import org.opencv.core.*

case class Input(source: String) extends sata.Input[String, Mat](source), utils.opencv.InputFromFile(source) {

  override def input(transform: Transform[Mat, Mat]): Mat = {
    image
  }
}

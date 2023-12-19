package com.muqiuhan.sata.image.enhancement.output

import com.muqiuhan.sata
import com.muqiuhan.sata.utils
import org.opencv.core.*

class Output(image: Mat) extends sata.Output[Mat, Unit](image), utils.opencv.OutputWithShow(image) {}

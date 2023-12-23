package com.muqiuhan.sata.image.enhancement.processor

import org.opencv.core.*
import com.muqiuhan.sata

class GammaTransformProcessor(image: Mat) extends sata.Processor[Mat, Mat](image) {
  override def process(): Mat = {
    image.convertTo(image, CvType.CV_32F)
    Core.pow(image, 4, image)
    Core.normalize(image, image, 0, 255, Core.NORM_MINMAX)
    Core.convertScaleAbs(image, image)
    image
  }
}

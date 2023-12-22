package com.muqiuhan.sata.image.enhancement.processor

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

/// TODO: BUG HERE!!! All black
class LogTransformationProcessor(image: Mat) extends sata.Processor[Mat, Mat](image) {
  override def process(): Mat = {
    val imageLog = Mat(image.size, CvType.CV_32FC3)
    val vals = new Array[Float](3)
    val byteVals = new Array[Byte](3)

    for (i <- 0 until image.rows) {
      for (j <- 0 until image.cols) {
        image.get(i, j, byteVals)
        vals(0) = Math.log(1 + byteVals(0)).toFloat
        vals(1) = Math.log(1 + byteVals(1)).toFloat
        vals(2) = Math.log(1 + byteVals(2)).toFloat
        imageLog.put(i, j, vals)
      }
    }

    // Normalize to 0~255
    Core.normalize(imageLog, imageLog, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC3)
    // Convert to 8-bit image
    imageLog.convertTo(imageLog, CvType.CV_8UC3)

    imageLog
  }
}

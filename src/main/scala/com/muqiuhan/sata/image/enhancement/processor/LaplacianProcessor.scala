package com.muqiuhan.sata.image.enhancement.processor

import com.muqiuhan.sata
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class LaplacianProcessor(image: Mat) extends sata.Processor[Mat, Mat](image) {
  override def process(): Mat = {
    val imageEnhanced = new Mat();
    val kernel = Mat.eye(3, 3, CvType.CV_32F);
    kernel.put(0, 1, -1);
    kernel.put(1, 1, 5);
    kernel.put(2, 1, -1);

    Imgproc.filter2D(image, imageEnhanced, -1, kernel)
    imageEnhanced
  }
}

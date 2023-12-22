package com.muqiuhan.sata.image.enhancement

import com.muqiuhan.sata
import com.muqiuhan.sata.image.enhancement.input.Input
import com.muqiuhan.sata.image.enhancement.output.Output
import com.muqiuhan.sata.image.enhancement.processor._
import org.opencv.core.*

class Test extends munit.FunSuite {

  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  object HistogramEqualization extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/enhancement/test1.jpg")
    val processor: HistogramEqualizationProcessor = HistogramEqualizationProcessor(input.image)
    val output: Output = Output(processor.process())
  }

  object Laplacian extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/enhancement/test1.jpg")
    val processor: LaplacianProcessor = LaplacianProcessor(input.image)
    val output: Output = Output(processor.process())
  }

  object LogTransformation extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/enhancement/test1.jpg")
    val processor: LogTransformationProcessor = LogTransformationProcessor(input.image)
    val output: Output = Output(processor.process())
  }

  test("Image enhancement with Histogram Equalization") {
    HistogramEqualization.run()
  }

  test("Image enhancement with Laplacian") {
    Laplacian.run()
  }

  test("Image enhancement with Log Transformation") {
    LogTransformation.run()
  }
}

package com.muqiuhan.sata.image.enhancement

import com.muqiuhan.sata
import com.muqiuhan.sata.Processor
import com.muqiuhan.sata.image.enhancement.input.Input
import com.muqiuhan.sata.image.enhancement.output.Output
import com.muqiuhan.sata.image.enhancement.processor.*
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
    val processor: LogTransformProcessor = LogTransformProcessor(input.image)
    val output: Output = Output(processor.process())
  }

  object GammaTransform extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/enhancement/test1.jpg")
    val processor: GammaTransformProcessor = GammaTransformProcessor(input.image)
    val output: Output = Output(processor.process())
  }

  object HistogramEqualizationWithGammaTransform extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/enhancement/test1.jpg")
    val processor1: LaplacianProcessor = LaplacianProcessor(input.image)
    val processor: GammaTransformProcessor = GammaTransformProcessor(processor1.process())
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

  test("Image enhancement with Gamma Transformation") {
    GammaTransform.run()
  }

  test("Image enhancement with Histogram Equalization then Gamma Transformation") {
    HistogramEqualizationWithGammaTransform.run()
  }
}

/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection

import com.muqiuhan.sata
import com.muqiuhan.sata.image.object_detection.input.Input
import com.muqiuhan.sata.image.object_detection.output.Output
import com.muqiuhan.sata.image.object_detection.processor.Processor
import org.opencv.core.*

class Test extends munit.FunSuite {

  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  object ObjectDetection extends sata.Sata[String, Mat, Mat, Unit] {
    val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/object_detection/test.jpg")
    val processor: Processor = Processor(
      input.input(input.letterbox),
      "./src/main/scala/com/muqiuhan/sata/image/object_detection/yolov7-d6.onnx",
      input.letterbox
    )

    val output: Output = Output(processor.process())
  }

  test("Image object detection") {
    ObjectDetection.run()
  }
}

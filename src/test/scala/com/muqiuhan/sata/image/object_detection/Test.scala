/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection

import com.muqiuhan.sata
import com.muqiuhan.sata.image.object_detection
import org.opencv.core.*

class Test extends munit.FunSuite:

  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  test("Image object detection") {
    val input = object_detection.input.Input("./src/test/scala/com/muqiuhan/sata/image/object_detection/test.jpg")
    val processor = object_detection
      .processor
      .Processor(
        input.input(),
        "./src/main/scala/com/muqiuhan/sata/image/object_detection/yolov7-d6.onnx",
        input.transform.letterbox
      )
    val output = object_detection.output.Output(processor.process())

    class Run extends sata.Sata[String, Mat, Mat, Unit](input, processor, output):
      override def run(): Unit = output.output()
    Run().run()
  }

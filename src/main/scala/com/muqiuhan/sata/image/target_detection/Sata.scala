package com.muqiuhan.sata.image.target_detection

import com.muqiuhan.sata
import com.muqiuhan.sata.image.target_detection
import org.opencv.core.*

class Sata(imagePath: String):
  private val input = target_detection.input.Input(imagePath)

  private val processor = target_detection
    .processor
    .Processor(input.image, "./yolov7-d6.onnx", input.transform.letterbox)

  private val output = target_detection.output.Output(processor.process())

  private class Run extends sata.Sata[String, Mat, Mat, Unit](input, processor, output):
    override def run(input: String): Unit = output.output()

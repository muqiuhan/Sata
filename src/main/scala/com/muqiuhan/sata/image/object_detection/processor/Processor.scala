/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.processor

import ai.onnxruntime.{OnnxTensor, OrtEnvironment, OrtSession}
import org.opencv.core.*
import com.muqiuhan.sata
import com.muqiuhan.sata.image.object_detection.input.LetterBox
import org.opencv.imgproc.Imgproc

import java.nio.FloatBuffer
import java.util

class Processor(input: Mat, onnxPath: String, letterbox: LetterBox) extends sata.Processor[Mat, Mat](input) {

  override def process(): Mat = {
    val environment = OrtEnvironment.getEnvironment

    val sessionOptions = OrtSession.SessionOptions()
    val session = environment.createSession(onnxPath, sessionOptions)

    val minDwDh = Math.min(input.width(), input.height())
    val thickness = minDwDh / 333
    val fontSize = minDwDh / 1145.14
    val fontFace = Imgproc.FONT_HERSHEY_SIMPLEX
    val fontColor = Scalar(255, 255, 255)

    val ratio = letterbox.ratio
    val dw = letterbox.dw
    val dh = letterbox.dh
    val rows = letterbox.height()
    val cols = letterbox.width()
    val channels = input.channels()
    val pixels = new Array[Float](channels * rows * cols)

    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        val pixel = input.get(j, i)
        for (k <- 0 until channels) {
          pixels(rows * cols * k + j * cols + i) = pixel(k).toFloat
        }
      }
    }

    val shape = Array(1L, channels, rows, cols)
    val tensor = OnnxTensor.createTensor(environment, FloatBuffer.wrap(pixels), shape)
    val stringOnnxTensorHashMap = util.HashMap[String, OnnxTensor]()

    stringOnnxTensorHashMap.put(session.getInputInfo.keySet().iterator().next(), tensor)

    val output = session.run(stringOnnxTensorHashMap)
    val outputData = output.get(0).getValue.asInstanceOf[Array[Array[Float]]]

    outputData.foreach(x => {
      val modelResult = ModelResult(x)
      val topLeft = Point((modelResult.x0 - dw) / ratio, (modelResult.y0 - dh) / ratio)
      val bottomRight = Point((modelResult.x1 - dw) / ratio, (modelResult.y1 - dh) / ratio)
      val color = Scalar(Label.colors.get(Label.names.get(modelResult.clsId)))
      val boxName = s"${Label.names.get(modelResult.clsId)}: ${modelResult.score}"
      val boxNameLoc = Point((modelResult.x0 - dw) / ratio, (modelResult.y0 - dh) / ratio - 3)

      println(modelResult)
      Imgproc.rectangle(input, topLeft, bottomRight, color, thickness)
      Imgproc.putText(input, boxName, boxNameLoc, fontFace, fontSize, fontColor, thickness);
    })

    input
  }
}

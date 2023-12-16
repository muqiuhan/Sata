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
    val environment: OrtEnvironment = OrtEnvironment.getEnvironment

    val sessionOptions: OrtSession.SessionOptions = OrtSession.SessionOptions()
    val session: OrtSession = environment.createSession(onnxPath, sessionOptions)

    val minDwDh: Int = Math.min(input.width(), input.height())
    val thickness: Int = minDwDh / 333
    val fontSize: Double = minDwDh / 1145.14
    val fontFace: Int = Imgproc.FONT_HERSHEY_SIMPLEX
    val fontColor: Scalar = Scalar(255, 255, 255)

    val ratio: Double = letterbox.ratio
    val dw: Double = letterbox.dw
    val dh: Double = letterbox.dh
    val rows: Int = letterbox.height()
    val cols: Int = letterbox.width()
    val channels: Int = input.channels()
    val pixels: Array[Float] = new Array[Float](channels * rows * cols)

    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        val pixel = input.get(j, i)
        for (k <- 0 until channels) {
          pixels(rows * cols * k + j * cols + i) = pixel(k).asInstanceOf[Float]
        }
      }
    }

    val shape: Array[Long] = Array(1L, channels, rows, cols)
    val tensor: OnnxTensor = OnnxTensor.createTensor(environment, FloatBuffer.wrap(pixels), shape)
    val stringOnnxTensorHashMap: util.HashMap[String, OnnxTensor] = util.HashMap[String, OnnxTensor]()

    stringOnnxTensorHashMap.put(session.getInputInfo.keySet().iterator().next(), tensor)

    val output: OrtSession.Result = session.run(stringOnnxTensorHashMap)
    val outputData: Array[Array[Float]] = output.get(0).getValue.asInstanceOf[Array[Array[Float]]]

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

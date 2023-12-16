/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.input

import com.muqiuhan.sata
import com.muqiuhan.sata.Transform
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

/** Resize and pad the image so that it satisfies the stride constraints and record the parameters
  */
case class LetterBox(source: Mat) extends Transform[Mat, Mat](source) {
  private val NEW_SHAPE: Size = Size(1280, 1280)
  private val COLOR: Array[Double] = Array(144, 144, 144)
  private val AUTO: Boolean = false
  private val SCALEUP: Boolean = true
  private val STRIDE: Int = 32

  var ratio: Double = .0
  var dw: Double = .0
  var dh: Double = .0

  def height(): Int = NEW_SHAPE.height.toInt
  def width(): Int = NEW_SHAPE.width.toInt

  private def computeRatio(shape: Array[Int]): Double = {
    val ratio = Math.min(NEW_SHAPE.height / shape(0), NEW_SHAPE.width / shape(1))

    if (!SCALEUP) {
      Math.min(ratio, 1.0)
    } else {
      ratio
    }
  }

  private def autoSmallestRectangle(dw: Double, dh: Double): (Double, Double) = {
    // When filling, fill half of the sides, so that the image is in the center
    if (AUTO) {
      ((dw % STRIDE) / 2, (dh % STRIDE) / 2)
    } else {
      (dw / 2, dh / 2)
    }
  }

  private def resize(shape: Array[Int], newUnpad: Size): Unit = {
    if (shape(1) != newUnpad.width || shape(0) != newUnpad.height) {
      Imgproc.resize(source, source, newUnpad, 0, 0, Imgproc.INTER_LINEAR)
    }
  }

  // Fill the image into a square
  private def fill(top: Int, bottom: Int, left: Int, right: Int): Unit = {
    Core.copyMakeBorder(source, source, top, bottom, left, right, Core.BORDER_CONSTANT, new Scalar(COLOR))

    this.ratio = ratio
    this.dh = dh
    this.dw = dw
  }

  override def trans(): Mat = {
    val shape = Array(source.rows, source.cols)
    val ratio = computeRatio(shape)
    val newUnpad = Size(Math.round(shape(1) * ratio).toDouble, Math.round(shape(0) * ratio).toDouble)
    val (dw, dh) = autoSmallestRectangle(NEW_SHAPE.width - newUnpad.width, NEW_SHAPE.height - newUnpad.height)

    resize(shape, newUnpad)
    fill(Math.round(dh - 0.1).toInt, Math.round(dh + 0.1).toInt, Math.round(dw - 0.1).toInt, Math.round(dw + 0.1).toInt)
    source
  }
}

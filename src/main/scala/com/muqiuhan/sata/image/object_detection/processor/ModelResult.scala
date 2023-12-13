/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.processor

case class ModelResult(x: Array[Float]):
  val batchId: Int = x(0).asInstanceOf[Int]
  val x0: Float = x(1)
  val y0: Float = x(2)
  val x1: Float = x(3)
  val y1: Float = x(4)
  val clsId: Int = x(5).asInstanceOf[Int]
  val score: Float = x(6)

  override def toString: String = s"| $batchId\t | $x0\t | $y0\t | $x1\t | $y1\t | $clsId\t | $score\t |"

package com.muqiuhan.sata.image.object_detection.processor

case class ModelResult(x: Array[Float]):
  val batchId: Int = x(0).asInstanceOf[Int]
  val x0: Float = x(1)
  val y0: Float = x(2)
  val x1: Float = x(3)
  val y1: Float = x(4)
  val clsId: Int = x(5).asInstanceOf[Int]
  val score: Float = x(6)

  override def toString: String = s"o- $batchId | $x0 | $y0 | $x1 | $y1 | $clsId | $score"

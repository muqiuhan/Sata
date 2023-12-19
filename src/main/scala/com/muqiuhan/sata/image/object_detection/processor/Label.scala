/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.processor

import java.util
import java.util.Random

case object Label {
  val names: util.List[String] =
    new util.ArrayList[String](
      util
        .Arrays
        .asList(
          "person",
          "bicycle",
          "car",
          "motorcycle",
          "airplane",
          "bus",
          "train",
          "truck",
          "boat",
          "traffic light",
          "fire hydrant",
          "stop sign",
          "parking meter",
          "bench",
          "bird",
          "cat",
          "dog",
          "horse",
          "sheep",
          "cow",
          "elephant",
          "bear",
          "zebra",
          "giraffe",
          "backpack",
          "umbrella",
          "handbag",
          "tie",
          "suitcase",
          "frisbee",
          "skis",
          "snowboard",
          "sports ball",
          "kite",
          "baseball bat",
          "baseball glove",
          "skateboard",
          "surfboard",
          "tennis racket",
          "bottle",
          "wine glass",
          "cup",
          "fork",
          "knife",
          "spoon",
          "bowl",
          "banana",
          "apple",
          "sandwich",
          "orange",
          "broccoli",
          "carrot",
          "hot dog",
          "pizza",
          "donut",
          "cake",
          "chair",
          "couch",
          "potted plant",
          "bed",
          "dining table",
          "toilet",
          "tv",
          "laptop",
          "mouse",
          "remote",
          "keyboard",
          "cell phone",
          "microwave",
          "oven",
          "toaster",
          "sink",
          "refrigerator",
          "book",
          "clock",
          "vase",
          "scissors",
          "teddy bear",
          "hair drier",
          "toothbrush"
        )
    )

  val colors: util.Map[String, Array[Double]] = new util.HashMap[String, Array[Double]]()

  names.forEach(name => {
    val random = Random()
    colors.put(name, Array(random.nextDouble() * 256, random.nextDouble() * 256, random.nextDouble() * 256))
  })
}

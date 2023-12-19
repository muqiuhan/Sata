/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata.image.object_detection.output

import org.opencv.core.*
import com.muqiuhan.sata
import com.muqiuhan.sata.utils

class Output(input: Mat) extends sata.Output[Mat, Unit](input), utils.opencv.OutputWithShow(input) {}

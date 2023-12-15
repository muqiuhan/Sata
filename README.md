<div align="center">

# Sata

*Simple cross-platform image processing toolkit*

![Build](https://github.com/muqiuhan/sata/actions/workflows/ci.yaml/badge.svg)

</div>

## Introduction

This is an image processing toolkit written in Scala, which will contain many image processing programs based on opencv and yolo, etc.

## List
- Detect target objects in images

## Usage

Sata uses opencv 4.8.1, So you need to make sure that `opencv-481.jar` is in your system's classpath, Or you can specify its path, for example:
```shell
-Djava.library.path=/usr/local/share/java/opencv4/
```

For developers, you need to create a new directory `lib` in the project root, Then put `opencv-481.jar` into it. This is for integration with IDE.

__E.g: Detect target objects in images__:
```scala
object ObjectDetection extends sata.Sata[String, Mat, Mat, Unit]:
  val input: Input = Input("./src/test/scala/com/muqiuhan/sata/image/object_detection/test.jpg")
  val processor: Processor = Processor(
    input.input(input.letterbox),
    "./src/main/scala/com/muqiuhan/sata/image/object_detection/yolov7-d6.onnx",
    input.letterbox
  )
  
  val output: Output = Output(processor.process())
```

then
```scala
ObjectDetection.run()
```

## [LICENSE](./LICENSE)
Copyright 2023 Muqiu Han <muqiu-han@outlook.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
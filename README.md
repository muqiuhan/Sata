<div align="center">

# Sata

*Simple cross-platform image processing toolkit*

</div>

## Introduction

This is an image processing toolkit written in Scala, which will contain many image processing programs based on opencv and yolo, etc.

## List
- Detect target objects in images

## Usage

This project uses opencv 4.8.1, So you need to make sure that `opencv-481.jar` is in your system's classpath, Or you can specify its path, for example:
```shell
-Djava.library.path=/usr/local/share/java/opencv4/
```

For developers, you need to create a new directory `lib` in the project root, Then put `opencv-481.jar` into it. This is for integration with IDE.

### Detect target objects in images
```scala
val input = object_detection.input.Input("Image Path")
val processor = object_detection
  .processor
  .Processor(
    input.input(),
    "Your ONNX model path",
    input.transform.letterbox
  )

val output = object_detection.output.Output(processor.process())

class Run extends sata.Sata[String, Mat, Mat, Unit](input, processor, output):
  override def run(): Unit = output.output()

Run().run()
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
/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata

import com.muqiuhan.sata

trait Sata[Input, ProcessorInput, ProcessorOutput, Output] {
  val input: sata.Input[Input, ProcessorInput]
  val processor: sata.Processor[ProcessorInput, ProcessorOutput]
  val output: sata.Output[ProcessorOutput, Output]

  def run(): Output = output.output()
}

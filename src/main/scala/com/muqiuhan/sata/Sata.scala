package com.muqiuhan.sata

import com.muqiuhan.sata

trait Sata[Input, ProcessorInput, ProcessorOutput, Output](
    input: sata.Input[Input, ProcessorInput],
    processor: sata.Processor[ProcessorInput, ProcessorOutput],
    output: sata.Output[ProcessorOutput, Output]
):
  def run(): Output

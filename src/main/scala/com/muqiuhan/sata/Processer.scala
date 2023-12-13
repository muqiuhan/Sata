package com.muqiuhan.sata

/** How to process data. Input is of type `Output` from `Input` class, `Output`
  * is of type `Input` from `Output` class
  */
trait Processer[Input, Output](input: Input):
  def process(): Output

package com.muqiuhan.sata

/** Output the processed data, such as storing it in a file system or database. `Input` is `Output` of `Processer` class
  */
trait Output[Input, Output](input: Input):
  def output(): Output

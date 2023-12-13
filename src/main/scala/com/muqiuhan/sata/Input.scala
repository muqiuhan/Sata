package com.muqiuhan.sata

/** Read the data that needs to be operated and convert it to `Output` type data
  * through `transform`
  */
trait Input[Input, Output](transform: Transform[Output, _]):

  /** This function needs to verify whether the read data is legal, and the
    * remaining work is left to `transform`
    */
  def input(source: Input): Output

/** Convert data to `Output` type for `Processer` to process */
trait Transform[Input, Output](source: Input):
  def trans(): Output

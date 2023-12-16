/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata

/** Convert data to `Output` type for `Processer` to process */
trait Transform[Input, Output](source: Input) {
  def trans(): Output
}

/** Read the data that needs to be operated and convert it to `Output` type data through `transform`
  */
trait Input[Input, Output](source: Input) {

  /** This function needs to verify whether the read data is legal, and the remaining work is left to `transform`
    */
  def input(transform: Transform[Output, Output]): Output = transform.trans()
}

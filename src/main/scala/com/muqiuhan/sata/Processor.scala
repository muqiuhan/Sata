/* Copyright 2023 Muqiu Han <muqiu-han@outlook.com> */

package com.muqiuhan.sata

/** How to process data. Input is of type `Output` from `Input` class, `Output` is of type `Input` from `Output` class
  */
trait Processor[Input, Output](input: Input) {
  input.asInstanceOf[Input | Null] match
    case null => throw NullPointerException("The input is null")
    case _ => ()
    
  def process(): Output
}

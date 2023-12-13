package com.muqiuhan.sata

trait Sata:
  trait Input[Input, Output]:
    def read(input: Input): Output

  trait Processer[Input <: Input]
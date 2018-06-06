package com.example.kotlintest.kotlintest

/**
 * Created by 60959 on 2018/6/5.
 */
class Module {
    var name: String = ""
    var age: Int = 0

    constructor(name: String, age: Int) {
        this.age = age
        this.name = name
    }

    constructor()
}
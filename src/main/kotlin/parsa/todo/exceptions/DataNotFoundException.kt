package parsa.todo.exceptions

class DataNotFoundException : Exception {

    constructor() : super() {}
    constructor(message : String) : super(message)
}
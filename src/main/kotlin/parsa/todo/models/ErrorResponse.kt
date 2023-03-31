package parsa.todo.models

import org.springframework.http.HttpStatus
import java.util.Date

class ErrorResponse {

    private var timestamp: Date? = null
    private var code = 0
    private var status : String = ""
    private var message : String? = ""

    constructor(){
        timestamp = Date()
    }

    constructor(httpStatus: HttpStatus, message: String?){
        timestamp = Date()
        code = httpStatus.value()
        status = httpStatus.name
        this.message = message
    }

}
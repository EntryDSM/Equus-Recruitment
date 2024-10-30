package entry.dsm.equusrecruitment.domain.global.error

import entry.dsm.equusrecruitment.domain.global.error.exception.EntryBlogException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EntryBlogException::class)
    fun customExceptionHandler(e: EntryBlogException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e), HttpStatus.valueOf(e.statusCode))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage ?: "Unknown error",
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        val httpStatus = when(e) {
            is IllegalArgumentException -> HttpStatus.BAD_REQUEST
            is IllegalStateException -> HttpStatus.BAD_REQUEST
            is AccessDeniedException -> HttpStatus.FORBIDDEN
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        val errorResponse = ErrorResponse(
            statusCode = httpStatus.value(),
            message = e.localizedMessage ?: "Unknown error",
        )

        return ResponseEntity(errorResponse, httpStatus)
    }
}
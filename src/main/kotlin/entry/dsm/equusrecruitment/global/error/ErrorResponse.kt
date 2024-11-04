package entry.dsm.equusrecruitment.global.error

import entry.dsm.equusrecruitment.global.error.exception.RecruitmentException

class ErrorResponse(
    val statusCode: Int,
    val message: String,
) {
    companion object {
        fun of(e: RecruitmentException): ErrorResponse {
            return ErrorResponse(
                statusCode = e.statusCode,
                message = e.message,
            )
        }
    }
}

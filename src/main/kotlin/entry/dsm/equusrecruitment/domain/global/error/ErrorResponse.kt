package entry.dsm.equusrecruitment.domain.global.error

import entry.dsm.equusrecruitment.domain.global.error.exception.EntryBlogException

class ErrorResponse(
    val statusCode: Int,
    val message: String
) {
    companion object {
        fun of(e: EntryBlogException): ErrorResponse {
            return ErrorResponse(
                statusCode = e.statusCode,
                message = e.message
            )
        }
    }
}
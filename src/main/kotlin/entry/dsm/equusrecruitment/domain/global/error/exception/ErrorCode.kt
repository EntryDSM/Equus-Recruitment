package entry.dsm.equusrecruitment.domain.global.error.exception

enum class ErrorCode(
    val statusCode: Int,
    val message: String,
) {
    APPLICATION_ALREADY_EXIST(409, "Application already exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
}

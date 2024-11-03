package entry.dsm.equusrecruitment.global.error.exception

abstract class RecruitmentException(
    val errorCode: ErrorCode,
) : RuntimeException() {
    val statusCode: Int
        get() = errorCode.statusCode

    override val message: String
        get() = errorCode.message
}

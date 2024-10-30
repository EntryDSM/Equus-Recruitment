package entry.dsm.equusrecruitment.domain.global.error.exception

abstract class EntryBlogException(
    val errorCode: ErrorCode,
) : RuntimeException() {
    val statusCode: Int
        get() = errorCode.statusCode

    override val message: String
        get() = errorCode.message
}

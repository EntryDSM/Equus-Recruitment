package entry.dsm.equusrecruitment.domain.global.exception

import entry.dsm.equusrecruitment.domain.global.error.exception.EntryBlogException
import entry.dsm.equusrecruitment.domain.global.error.exception.ErrorCode.INTERNAL_SERVER_ERROR

object InternalServerError : EntryBlogException(INTERNAL_SERVER_ERROR)
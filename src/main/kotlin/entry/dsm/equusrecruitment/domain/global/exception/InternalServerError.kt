package entry.dsm.equusrecruitment.domain.global.exception

import entry.dsm.equusrecruitment.domain.global.error.exception.ErrorCode.INTERNAL_SERVER_ERROR
import entry.dsm.equusrecruitment.domain.global.error.exception.RecruitmentException

object InternalServerError : RecruitmentException(INTERNAL_SERVER_ERROR)

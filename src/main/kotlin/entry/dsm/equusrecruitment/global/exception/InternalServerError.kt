package entry.dsm.equusrecruitment.global.exception

import entry.dsm.equusrecruitment.global.error.exception.ErrorCode.INTERNAL_SERVER_ERROR
import entry.dsm.equusrecruitment.global.error.exception.RecruitmentException

object InternalServerError : RecruitmentException(INTERNAL_SERVER_ERROR)

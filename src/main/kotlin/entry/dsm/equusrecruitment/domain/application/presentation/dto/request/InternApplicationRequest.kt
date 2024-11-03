package entry.dsm.equusrecruitment.domain.application.presentation.dto.request

import entry.dsm.equusrecruitment.domain.application.domain.enum.Experience
import entry.dsm.equusrecruitment.domain.application.domain.enum.Major
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class InternApplicationRequest(
    @field:NotBlank
    @Size(min = 1, max = 30)
    val name: String,
    @field:NotNull
    val schoolNumber: Int,
    @field:NotNull(message = "전화번호는 필수 입력 항목입니다")
    @field:Pattern(
        regexp = "^010-\\d{4}-\\d{4}$",
        message = "올바른 전화번호 형식이 아닙니다. ex): 010-1234-5678",
    )
    val phoneNumber: String,
    @field:NotBlank
    @Size(min = 1, max = 1000)
    val introduce: String,
    @field:NotNull
    val interestingMajor: Major,
    @field:NotBlank
    @Size(min = 1, max = 1000)
    val motivation: String,
    @field:NotNull
    val experience: Experience,
)

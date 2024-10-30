package entry.dsm.equusrecruitment.domain.application.presentation.dto.request

import entry.dsm.equusrecruitment.domain.application.domain.enum.Experience
import entry.dsm.equusrecruitment.domain.application.domain.enum.Major
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class InternApplicationRequest(
    @field:NotBlank
    @Size(min = 1, max = 30)
    val name: String,
    @field:NotNull
    val schoolNumber: Int,
    @field:NotBlank
    @Size(min = 13, max = 13)
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

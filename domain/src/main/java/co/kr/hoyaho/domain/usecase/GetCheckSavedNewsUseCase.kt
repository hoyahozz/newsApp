package co.kr.hoyaho.domain.usecase

import co.kr.hoyaho.domain.repository.SaveRepository
import javax.inject.Inject

class GetCheckSavedNewsUseCase @Inject constructor(
    private val repository: SaveRepository
) {
    suspend operator fun invoke(title: String): Boolean {
        return repository.getCheckSavedNews(title)
    }
}

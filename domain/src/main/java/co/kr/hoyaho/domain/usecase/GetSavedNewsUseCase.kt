package co.kr.hoyaho.domain.usecase

import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.repository.SaveRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    private val repository: SaveRepository
) {
    suspend operator fun invoke(): List<News> {
        return repository.getSavedNews()
    }
}

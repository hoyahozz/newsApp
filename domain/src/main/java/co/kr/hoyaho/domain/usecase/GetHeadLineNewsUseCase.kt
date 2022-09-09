package co.kr.hoyaho.domain.usecase

import co.kr.hoyaho.domain.NetworkResult
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.repository.NewsRepository
import javax.inject.Inject

class GetHeadLineNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): NetworkResult<List<News>> {
        return repository.getHeadLineNews()
    }
}

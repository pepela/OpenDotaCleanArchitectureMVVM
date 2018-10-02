package com.pepela.data.match.interactor

import com.nhaarman.mockito_kotlin.*
import com.pepela.data.MatchDataRepository
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.test.factory.match.MatchFactory.Factory.makeMatches
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetRecentMatchesUseCaseTest {

    companion object {
        private const val ACCOUNT_ID = 1L
    }

    private val matchDataRepository = mock<MatchDataRepository>()
    private val threadExecutor = mock<ThreadExecutor>()
    private val postExecutionThread = mock<PostExecutionThread>()

    private val getRecentMatchesUseCase = GetRecentMatchesUseCase(matchDataRepository,
            threadExecutor, postExecutionThread)

    @Test
    fun getRecentMatches_calls_repository() {
        getRecentMatchesUseCase.buildUseCase(ACCOUNT_ID)

        verify(matchDataRepository).getRecentMatches(ACCOUNT_ID)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getRecentMatches_null_parameter_throws_exception() {
        getRecentMatchesUseCase.buildUseCase(null)

        verify(matchDataRepository, never()).getRecentMatches(any())
    }

    @Test
    fun getRecentMatches_returns_data() {
        val matches = makeMatches(2)
        whenever(matchDataRepository.getRecentMatches(any())).thenReturn(Flowable.just(matches))
        val testObserver = getRecentMatchesUseCase.buildUseCase(ACCOUNT_ID).test()

        testObserver.assertValue(matches)
    }
}

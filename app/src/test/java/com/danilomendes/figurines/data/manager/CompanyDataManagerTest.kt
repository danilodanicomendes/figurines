package com.danilomendes.figurines.data.manager

import com.danilomendes.figurines.BaseTest
import com.danilomendes.figurines.data.entity.Company
import com.danilomendes.figurines.data.local.CompanyLocalDataSource
import com.danilomendes.figurines.data.remote.CompanyRemoteDataSource
import com.danilomendes.figurines.data.remote.api.CompanyService
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import java.util.*
import org.hamcrest.CoreMatchers.`is` as _is
import org.mockito.Mockito.`when` as _when

/**
 * Created by danilo on 09-11-2017.
 */
class CompanyDataManagerTest: BaseTest() {

    /**
     * The Kotlin lazy and arrayListOf for some reason in unit testing are not being
     * recognized by android studio. But it compiles and runs as expected.
     * I've left this here like this just to test the kotlin feature.
     */
    private val singleList: List<Company> = arrayListOf(Company())

    private val emptyList: List<Company> = ArrayList()

    private lateinit var mCompanyDataManager: CompanyDataManager

    @Mock
    private lateinit var mLocalDataSource: CompanyLocalDataSource

    @Mock
    private lateinit var mRemoteDataSource: CompanyRemoteDataSource

    override fun beforeEachTest() {
        mCompanyDataManager = CompanyDataManager(mLocalDataSource, mRemoteDataSource)
    }

    @Test
    fun testGetAllCompaniesWithoutForceAndWithNetworkAvailable() {
        setNetworkAvailability(true)
        _when(mRemoteDataSource.companiesService)
                .thenReturn(CompanyService {Single.just(singleList)})

        val allCompanies = mCompanyDataManager.getAllCompanies(false)

        // Result should not be null.
        Assert.assertNotNull(allCompanies)

        // Blocks until getting success or error.
        Assert.assertFalse(allCompanies.blockingGet().isEmpty())

        // Verify if the network was validated once.
        verify(mRemoteDataSource).isNetworkAvailable

        // Verify if the queryCompanies request was done.
        verify(mRemoteDataSource).companiesService

        // Confirm that the local data source was not called.
        verify(mLocalDataSource, never())
    }

    @Test
    fun testGetAllCompaniesFromDatabaseWithoutNetworkAvailable() {
        setNetworkAvailability(false)
        _when(mLocalDataSource.all).thenReturn(Flowable.just(singleList))

        val allCompanies = mCompanyDataManager.getAllCompanies(false)

        // Result should not be null.
        Assert.assertNotNull(allCompanies)

        // Blocks until getting success or error.
        Assert.assertFalse(allCompanies.blockingGet().isEmpty())

        // Verify if the network was validated once.
        verify(mRemoteDataSource).isNetworkAvailable

        // Confirm that the local data source was called
        verify(mLocalDataSource)
    }

    /**
     * Mocks the return of [isNetworkAvailable][mRemoteDataSource] to return [isAvailable].
     * Note that this method should be before actually starting a method test, otherwise
     * it will clean up the number of invocations to [mRemoteDataSource].
     *
     * @param isAvailable
     */
    private fun setNetworkAvailability(isAvailable: Boolean) {
        _when(mRemoteDataSource.isNetworkAvailable).thenReturn(isAvailable)
        Assert.assertThat(mRemoteDataSource.isNetworkAvailable, _is(isAvailable))
        Mockito.clearInvocations(mRemoteDataSource)
    }
}

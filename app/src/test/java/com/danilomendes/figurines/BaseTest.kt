package com.danilomendes.figurines

import org.junit.Before
import org.mockito.MockitoAnnotations

/**
 * Created by danilo on 09-11-2017.
 */
abstract class BaseTest {

    @Before
    fun setup() {
        if (BuildConfig.FLAVOR != "mock") {
            throw RuntimeException("You should use 'mock' build variant to run unit tests.")
        }

        MockitoAnnotations.initMocks(this)
        beforeEachTest()
    }

    abstract fun beforeEachTest()
}
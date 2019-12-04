package dev.sudnik.github.data.exp

import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

class ExpDataSourceTest {

/*
    val mockServer = MockWebServer()
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @org.junit.Test
    fun getExp() {

        val expectedForkCount = 0
        val expectedExpDTO = ExpDTO(expectedForkCount)
        val expectedJson = "{\n" +
                "    \"data\": {\n" +
                "        \"repository\": {\n" +
                "            \"forkCount\": 0,\n" +
                "           \"__typename\":\"Repository\"" +
                "        }\n" +
                "    }\n" +
                "}"

//        val testQuery = TestQuery.builder().name("").owner("").build()
//        val expectedResponse: Response<TestQuery.Data> =
//            OperationResponseParser<TestQuery.Data, TestQuery.Data>(
//                testQuery,
//                testQuery.responseFieldMapper(),
//                ScalarTypeAdapters(emptyMap()),
//                ResponseNormalizer.NO_OP_NORMALIZER as ResponseNormalizer<MutableMap<String, Any>>?
//            ).parse(
//                Buffer().writeUtf8(expectedJson)
//            )
//        val realApolloCall = ApolloClient.builder().build().query(testQuery) as RealApolloCall

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(expectedJson)
        mockServer.enqueue(mockResponse)

        val mockCallback = mockk<ExpDataSource.OnExpReadyCallback> {
            every { onExpReady(expectedExpDTO) } just runs
            every { onError(any()) } just runs
        }

        val url = mockServer.url("/")
        val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
        val expDataSource = ExpDataSource(expectedBaseUrl)

        // Act
        expDataSource.getExp(mockCallback)

        Thread.sleep(500)
        // Assert
        verify {
            mockCallback.onExpReady(expectedExpDTO)
        }
    }
*/

}
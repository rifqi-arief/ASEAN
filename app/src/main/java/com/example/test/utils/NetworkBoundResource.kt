package com.example.test.utils

import com.example.test.utils.Resource
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()

    val flow = if (shouldFetch(data)){
        emit(Resource.Loading(data))
        try{
            val response = fetch()
            saveFetchResult(response)
            query().map { Resource.Success(it) }
//            if (response.code == 0) {
//                response.data?.let { saveFetchResult(it) }
//                query().map { Resource.Success(it) }
//            }else {
//                query().map { response.message?.let { it1 -> Resource.Error(it1,it) } }
//            }
        } catch (throwable : Throwable){
            query().map { Resource.Error(throwable.localizedMessage,it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
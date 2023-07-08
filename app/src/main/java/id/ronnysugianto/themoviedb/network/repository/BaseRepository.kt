package id.ronnysugianto.themoviedb.network.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import id.ronnysugianto.themoviedb.data.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart

abstract class BaseRepository {
    protected fun <T> flowData(task: suspend () -> T): Flow<Result<T>> =
        flow {
            val data = task()
            if (data is Response<*>) {
                if (data.isSuccessful)
                    emit(Result.Success(data))
                else
                    emit(Result.Error(HttpException(data)))
            } else {
                emit(Result.Success(data))
            }
        }.onStart {
            emit(Result.Loading())
        }.catch { exception ->
            emit(Result.Error(exception))
        }.distinctUntilChanged()
}
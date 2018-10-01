package com.pepela.opendota.model

class Resource<out T> constructor(val status: ResourceState, val data: T? = null,
                                  val message: String? = null) {

    fun <T> success(data: T): Resource<T> =
            Resource(ResourceState.SUCCESS, data)

    fun <T> error(message: String, data: T?): Resource<T> =
            Resource(ResourceState.ERROR, data, message)

    fun <T> loading(): Resource<T> =
            Resource(ResourceState.LOADING)

}

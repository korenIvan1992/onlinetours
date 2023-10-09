package com.onlinetours.domain.mapper

interface BaseModelMapper<E, M> {
    fun transform(from: M): E
}
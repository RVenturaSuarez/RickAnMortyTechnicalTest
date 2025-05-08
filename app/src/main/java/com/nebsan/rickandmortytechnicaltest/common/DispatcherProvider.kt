package com.nebsan.rickandmortytechnicaltest.common

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
}
package com.nebsan.rickandmortytechnicaltest.common

import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcherProvider(override val io: CoroutineDispatcher) : DispatcherProvider
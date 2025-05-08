package com.nebsan.rickandmortytechnicaltest.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDispatcherProvider(override val io: CoroutineDispatcher = Dispatchers.IO) :
    DispatcherProvider
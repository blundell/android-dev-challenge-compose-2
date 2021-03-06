/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    private val _timer = MutableLiveData("")
    val timer: LiveData<String> = _timer

    private val _timeSelected = MutableLiveData("")
    val timeSelected: LiveData<String> = _timeSelected

    private var millis: Long = 0
    private var currentTimer: CountDownTimer? = null

    fun setTimerInMillis(millis: Long) {
        this.millis = millis
        _timeSelected.value = (millis / 1000).toString()
    }

    fun startTimer() {
        currentTimer?.cancel()
        currentTimer = object : CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timer.value = "${(millisUntilFinished) / 1000}"
            }

            override fun onFinish() {
                _timer.value = "It's over."
            }
        }.start()
    }
}

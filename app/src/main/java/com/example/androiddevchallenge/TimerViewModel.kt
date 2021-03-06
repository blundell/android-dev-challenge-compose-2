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

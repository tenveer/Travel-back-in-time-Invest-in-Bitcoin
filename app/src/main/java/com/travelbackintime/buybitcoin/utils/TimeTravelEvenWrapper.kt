/*
 * Copyright 2021  Andrey Tolpeev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.travelbackintime.buybitcoin.utils

import android.os.Parcelable
import com.github.vase4kin.shared.timetravelmachine.TimeTravelMachine
import kotlinx.parcelize.Parcelize

sealed class TimeTravelEvenWrapper : Parcelable {
    @Parcelize
    data class RealWorldEvent(
        val title: String,
        val description: String,
        val isDonate: Boolean
    ) : TimeTravelEvenWrapper()

    @Parcelize
    data class TimeTravelEvent(
        val profitMoney: Double,
        val investedMoney: Double,
        val timeToTravel: Long
    ) : TimeTravelEvenWrapper()

    @Parcelize
    object NoPriceAvailableEvent : TimeTravelEvenWrapper()
}

fun TimeTravelMachine.Event.wrap(): TimeTravelEvenWrapper {
    return when (this) {
        is TimeTravelMachine.Event.RealWorldEvent -> TimeTravelEvenWrapper.RealWorldEvent(
            title = this.title,
            description = this.description,
            isDonate = this.isDonate
        )
        is TimeTravelMachine.Event.TimeTravelEvent -> TimeTravelEvenWrapper.TimeTravelEvent(
            profitMoney = this.profitMoney,
            investedMoney = this.investedMoney,
            timeToTravel = this.timeToTravel
        )
        TimeTravelMachine.Event.NoPriceAvailableEvent -> TimeTravelEvenWrapper.NoPriceAvailableEvent
    }
}

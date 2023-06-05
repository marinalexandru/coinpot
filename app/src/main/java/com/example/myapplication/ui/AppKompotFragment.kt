package com.example.myapplication.ui

import com.example.myapplication.ui.root.RootFlowImpl
import com.revolut.kompot.entry_point.fragment.KompotConfig
import com.revolut.kompot.entry_point.fragment.KompotFragment

class AppKompotFragment : KompotFragment() {

    override fun config(): KompotConfig = KompotConfig(
        rootFlow = RootFlowImpl(),
    )
}

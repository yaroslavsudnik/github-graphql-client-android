package dev.sudnik.template.main

import android.os.Bundle
import android.view.View
import dev.sudnik.basecleanandroid.presentation.BaseActivity
import dev.sudnik.template.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewState, MainViewModel>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bt.setOnClickListener { goClick() }
    }

    override fun instanceViewModel() = MainViewModel(application)

    override fun processDataState(dataState: MainViewState) = when (dataState) {
        is MainViewState.ShowI -> showI(dataState.i)
        else -> super.processDataErrorState(dataState)
    }

    private fun showI(text: String) {
        hideLoading()
        tv.text = text
    }

    private fun goClick() {
        showLoading()
        baseViewModel.getExp()
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
        bt.isEnabled = false
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
        bt.isEnabled = true
    }
}

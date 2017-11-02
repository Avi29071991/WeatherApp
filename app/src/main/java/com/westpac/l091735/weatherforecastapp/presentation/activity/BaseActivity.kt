package com.westpac.l091735.weatherforecastapp.presentation.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.annotation.LayoutRes
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.Toolbar
import com.westpac.l091735.weatherforecastapp.di.Injectable
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    /**
     * @return the layout resource to use for this activity,
     * * or a value <= 0 if no layout should be used
     */
    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    fun getActivityBinding(): T {
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this is Injectable) {
            AndroidInjection.inject(this)
        }

        val layoutResId = getLayoutResource()
        if (layoutResId > 0) {
            setContentView(layoutResId)
            binding = DataBindingUtil.setContentView<T>(this, getLayoutResource())
        }

    }

    override fun setSupportActionBar(@Nullable toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        configureActionBar()
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        val actionBar = supportActionBar
        actionBar?.title = title
    }

    @VisibleForTesting
    protected fun configureActionBar() {
        val actionBar = supportActionBar
        actionBar?.elevation = 0F
    }

    protected fun setNavigationUp() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
/*
 * Copyright (c) 2018. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andremion.theatre.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import com.andremion.theatre.R
import com.andremion.theatre.databinding.ActivityHomeBinding
import com.andremion.theatre.event.type.EventTypeViewModel
import com.andremion.theatre.internal.injection.ViewModelFactory
import com.andremion.theatre.internal.util.lazyThreadSafetyNone
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(EventTypeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binder.toolbar)

        binder.viewModel = viewModel
        binder.fabClick = this

        viewModel.loadEventTypeList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_search -> true
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }

    override fun onClick(v: View?) {
        Snackbar.make(binder.root, "Replace with your own action", Snackbar.LENGTH_LONG).show()
    }
}

package com.easy.kotlin.mytodoapplication

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

//import butterknife.bindView

class MainActivity : AppCompatActivity() {

    //    val fab: FloatingActionButton by bindView(R.id.fab)
    var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fab = findViewById(R.id.fab) as FloatingActionButton


        // 添加日程事件
        fab?.setOnClickListener { _ ->
            // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val todoEditFragment = TodoEditFragment()
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, todoEditFragment, todoEditFragment.javaClass.getSimpleName())
                    .addToBackStack(todoEditFragment.javaClass.getSimpleName())
                    .commit()

            hideFab()
        }

        // 待办事项 List
        val fragment = TodosFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment, fragment::class.java.getSimpleName())
                .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackCount = supportFragmentManager.backStackEntryCount
            if (backStackCount == 0) {
                showFab()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle actio1n bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun hideFab() {
        fab?.visibility = View.GONE
    }

    fun showFab() {
        fab?.visibility = View.VISIBLE
    }

}

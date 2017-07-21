package com.easy.kotlin.mytodoapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import com.easy.kotlin.mytodoapplication.TAG.MY_TAG
import com.easy.kotlin.mytodoapplication.model.Todo
import io.realm.Realm

/**
 * Created by jack on 2017/7/22.
 */


class TodosFragment : Fragment(), TodoAdapter.TodoItemClickListener {

    // protected val realmRecyclerView: RealmRecyclerView by bindView(R.id.todos_recycler_view)
    // var realmRecyclerView: RealmRecyclerView? = null // var 可变 View
    @BindView(R.id.todos_recycler_view)
    lateinit var realmRecyclerView: RealmRecyclerView

    private var realm: Realm? = null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_todos, container, false)
        ButterKnife.bind(this, v)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onResume() {
        super.onResume()
        val todos = realm!!.where(Todo::class.java).findAll()
        Log.i(MY_TAG, "onResume: ${todos}")
        //realmRecyclerView = find(R.id.todos_recycler_view) // 可变 View
        Log.i(MY_TAG, "onResume: realmRecyclerView = ${realmRecyclerView} ")
        val adapter = TodoAdapter(activity, todos, true, true, this)
        realmRecyclerView.setAdapter(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm!!.close()
    }

    // 点击事项，跳转编辑 EditFragment
    override fun onClick(caller: View, todo: Todo) {
        (activity as MainActivity).hideFab()

        val todoEditFragment = TodoEditFragment.newInstance(todo.id)
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, todoEditFragment, todoEditFragment.javaClass.getSimpleName())
                .addToBackStack(todoEditFragment.javaClass.getSimpleName())
                .commit()
    }

    companion object {
        fun newInstance(): TodosFragment {
            return TodosFragment()
        }
    }
}

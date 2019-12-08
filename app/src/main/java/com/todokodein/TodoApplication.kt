package com.todokodein

import android.app.Application
import com.todokodein.data.TodoDatabase
import com.todokodein.data.repository.TodoRepository
import com.todokodein.data.repository.TodoRepositoryImpl
import com.todokodein.ui.TodoViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TodoApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TodoApplication))

        bind() from singleton { TodoDatabase(instance()) }
        bind() from singleton { instance<TodoDatabase>().todoItemDao() }

        bind<TodoRepository>() with singleton { TodoRepositoryImpl(instance()) }
        bind() from provider { TodoViewModelFactory(instance()) }
    }
}
package id.ronnysugianto.themoviedb.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ronnysugianto.themoviedb.di.viewModel.ViewModelFactory
import id.ronnysugianto.themoviedb.util.appComponent
import id.ronnysugianto.themoviedb.viewModel.MovieViewModel
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    inline fun <reified VM : ViewModel> getViewModel()
            = ViewModelProvider(this, viewModelFactory).get(VM::class.java)

    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        movieViewModel = getViewModel()
    }
}
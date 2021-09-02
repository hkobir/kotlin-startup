package com.example.kotlin_startup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_startup.databinding.ActivityMainBinding
import com.example.kotlin_startup.model.User
import com.example.kotlin_startup.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), UserAdapter.Interaction {
    lateinit var viewModel: UserViewModel
    lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.userList.observe(this, Observer { users ->
            userAdapter.swapData(users)
            Log.d("MainActivity", "onCreate: $users")
        })
        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        viewModel.getAllUsers(1);
        initRV()
    }

    private fun initRV() {
        binding.userRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpaceItemDecoration = TopSpaceItemDecoration(30)
            addItemDecoration(topSpaceItemDecoration)
            userAdapter = UserAdapter(this@MainActivity)
            adapter = userAdapter

        }
    }

    public fun showToast(message: String? = "empty toast") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(user: User) {

        Log.d("MainActivity", "itemClicked: $user")
    }


}
package com.ssafy.flowtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.flowtest.R
import com.ssafy.flowtest.data.User
import com.ssafy.flowtest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : UserViewModel by viewModels()
    private lateinit var list : MutableList<User>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = mutableListOf()

        initRecyclerView()
        initButtons()
        initCollect()
    }

    fun initCollect(){
        lifecycleScope.launchWhenStarted {
            viewModel.userListState.collect{ userList->
                Log.d(TAG, "initCollect: ${userList.size}")
                list.clear()
                list.addAll(userList)
                userAdapter.notifyDataSetChanged()
            }
        }
    }

    fun initButtons(){
        binding.userInsertButton.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString()
            val description = binding.etDescription.text.toString()
            viewModel.insert(User(name,age,description,""))
        }
    }

    fun initRecyclerView(){
        userAdapter= UserAdapter(list)
        binding.userRecyclerView.adapter = userAdapter
        binding.userRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}
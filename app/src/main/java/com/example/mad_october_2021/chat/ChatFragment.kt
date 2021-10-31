package com.example.mad_october_2021.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mad_october_2021.common.*
import com.example.mad_october_2021.databinding.FragmentChatBinding
import com.example.mad_october_2021.main_screen.MainScreenActivity
import com.example.mad_october_2021.sign_in.SignInActivity
import com.example.mad_october_2021.splash.MainActivity
import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var chats: List<Chat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chats = mutableListOf()
        fetchChats()
    }

    private fun fetchChats() {
        ApiHelper().chat(object: OnResponseCallback {
            override fun onResponse(response: Response) {
                try {

                    val stringResponse = response.body?.string()
                    Log.d("DEBUG", "response: $stringResponse")
                    chats = Gson().fromJson(stringResponse, chats::class.java)

                    initChatsAdapter()

                }   catch (e: Exception) {
                    e.printStackTrace()
                    showError()
                }
            }

        }, object: OnFailureCallback {
            override fun onFailure(e: IOException) {
                showError()
            }

        })
    }

    private fun showError() {
        (context as MainScreenActivity).runOnUiThread {
            context?.let {
                DialogHelper(it)
                    .showDialog("Oops!", "Something went wrong. Try again later.",
                        object: ButtonCallback {
                            override fun okayCallback() {
                                (context as MainScreenActivity).startActivity(Intent(context, MainActivity::class.java))
                                (context as MainScreenActivity).finish()
                            }

                        })
            }
        }
    }

    private fun initChatsAdapter() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { ChatsAdapter(chats, it) }

        (context as MainScreenActivity).runOnUiThread {
            binding.rvChats.adapter = adapter
            binding.rvChats.layoutManager = layoutManager
        }

    }

}
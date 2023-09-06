package com.andersonsilvapp.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andersonsilvapp.convidados.databinding.FragmentAllGuestsBinding
import com.andersonsilvapp.convidados.view.adapter.GuestsAdapter
import com.andersonsilvapp.convidados.view.listener.OnGuestListener
import com.andersonsilvapp.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

  private var _binding: FragmentAllGuestsBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: AllGuestsViewModel
  private val adapter = GuestsAdapter()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, b: Bundle?
  ): View {
    viewModel =
      ViewModelProvider(this).get(AllGuestsViewModel::class.java)

    _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

    binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

    binding.recyclerAllGuests.adapter = adapter

    val listener = object : OnGuestListener {
      override fun onClick() {
        Toast.makeText(context, "Fui clicado", Toast.LENGTH_LONG).show()
      }

      override fun onDelete() {
        TODO("Not yet implemented")
      }

    }

    adapter.attachListener(listener)

    viewModel.getAll()

    observe()

    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observe() {
    viewModel.guests.observe(viewLifecycleOwner) {
      adapter.updateGuests(it)
    }
  }
}
package com.andersonsilvapp.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andersonsilvapp.convidados.databinding.RowGuestBinding
import com.andersonsilvapp.convidados.model.GuestModel
import com.andersonsilvapp.convidados.view.listener.OnGuestListener
import com.andersonsilvapp.convidados.view.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

  private var guestList: List<GuestModel> = listOf()
  private lateinit var listener: OnGuestListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
    val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return GuestViewHolder(item, listener)
  }

  override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
    holder.bind(guestList[position])
  }

  override fun getItemCount(): Int {
    return guestList.count()
  }

  fun updateGuests(list: List<GuestModel>) {
    guestList = list
    notifyDataSetChanged()
  }

  fun attachListener(guestListener: OnGuestListener) {
    listener = guestListener
  }
}
package com.andersonsilvapp.convidados.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.andersonsilvapp.convidados.databinding.RowGuestBinding
import com.andersonsilvapp.convidados.model.GuestModel
import com.andersonsilvapp.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
  RecyclerView.ViewHolder(bind.root) {

  fun bind(guest: GuestModel) {
    bind.textName.text = guest.name

    bind.textName.setOnClickListener {
      listener.onClick()
    }
  }
}
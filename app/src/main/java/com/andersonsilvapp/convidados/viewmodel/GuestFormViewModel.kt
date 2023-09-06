package com.andersonsilvapp.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.andersonsilvapp.convidados.model.GuestModel
import com.andersonsilvapp.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = GuestRepository.getInstance(application)

  fun insert(guest: GuestModel) {
    repository.insert(guest)
  }



}
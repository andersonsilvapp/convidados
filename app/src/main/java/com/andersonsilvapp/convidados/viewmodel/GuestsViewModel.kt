package com.andersonsilvapp.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andersonsilvapp.convidados.model.GuestModel
import com.andersonsilvapp.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

  private val repository =
    GuestRepository.getInstance(application.applicationContext)

  private val listAllGuests = MutableLiveData<List<GuestModel>>()
  val guests: LiveData<List<GuestModel>> = listAllGuests

  fun getAll() {
    listAllGuests.value = repository.getAll()
  }

  fun getAbsent() {
    listAllGuests.value = repository.getAbsent()
  }

  fun getPresent() {
    listAllGuests.value = repository.getPresent()
  }

  fun delete(id: Int) {
    repository.delete(id)
  }
}
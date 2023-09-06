package com.andersonsilvapp.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.andersonsilvapp.convidados.R
import com.andersonsilvapp.convidados.databinding.ActivityGuestFormBinding
import com.andersonsilvapp.convidados.model.GuestModel
import com.andersonsilvapp.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var binding: ActivityGuestFormBinding
  private lateinit var viewModel: GuestFormViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityGuestFormBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

    binding.buttonSave.setOnClickListener(this)
    binding.radioPresent.isChecked = true
  }

  override fun onClick(v: View) {
    if (v.id == R.id.button_save) {
      val name = binding.editName.text.toString()
      val presence = binding.radioPresent.isChecked

      val model = GuestModel(0, name, presence)

      viewModel.insert(model)
    }
  }
}
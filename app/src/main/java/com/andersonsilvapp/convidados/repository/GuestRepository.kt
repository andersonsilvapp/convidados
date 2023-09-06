package com.andersonsilvapp.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.andersonsilvapp.convidados.constants.DataBaseConstants
import com.andersonsilvapp.convidados.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

  private val guestDatabase = GuestDatabase(context)

  companion object {
    private lateinit var repository: GuestRepository

    fun getInstance(context: Context): GuestRepository {
      if (!Companion::repository.isInitialized) {
        repository = GuestRepository(context)
      }

      return repository
    }
  }

  fun insert(guest: GuestModel): Boolean {
    return try {
      val db = guestDatabase.writableDatabase
      val presence = if (guest.presence) 1 else 0

      val values = ContentValues()
      values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
      values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

      db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
      true
    } catch (e: Exception) {
      false
    }
  }

  fun update(guest: GuestModel): Boolean {
    return try {
      val db = guestDatabase.writableDatabase

      val presence = if (guest.presence) 1 else 0

      val values = ContentValues()
      values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
      values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

      val selection = DataBaseConstants.GUEST.TABLE_NAME + " = ?"
      val args = arrayOf(guest.id.toString())

      db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
      true
    } catch (e: Exception) {
      false
    }
  }

  fun delete(id: Int): Boolean {
    return try {
      val db = guestDatabase.writableDatabase

      val selection = DataBaseConstants.GUEST.TABLE_NAME + " = ?"
      val args = arrayOf(id.toString())

      db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
      true
    } catch (e: Exception) {
      false
    }
  }

  fun getAll(): List<GuestModel> {

    val list = mutableListOf<GuestModel>()

    try {
      val db = guestDatabase.readableDatabase

      val columnId = DataBaseConstants.GUEST.COLUMNS.ID
      val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
      val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

      val selection = arrayOf(
        columnId,
        columnName,
        columnPresence
      )

      val cursor =
        db.query(DataBaseConstants.GUEST.TABLE_NAME, selection, null, null, null, null, null)

      if (cursor != null && cursor.count > 0) {
        while (cursor.moveToNext()) {
          val id = cursor.getInt(cursor.getColumnIndex(columnId))
          val name = cursor.getString(cursor.getColumnIndex(columnName))
          val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

          list.add(GuestModel(id, name, presence == 1))
        }
      }

      cursor.close()

    } catch (e: Exception) {
      return list
    }

    return list

  }

  fun getPresent(): List<GuestModel> {

    val list = mutableListOf<GuestModel>()

    try {
      val db = guestDatabase.readableDatabase

      val columnId = DataBaseConstants.GUEST.COLUMNS.ID
      val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
      val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

      val projection = arrayOf(
        columnId,
        columnName,
        columnPresence
      )

      val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
      val args = arrayOf("1")

      val cursor =
        db.query(
          DataBaseConstants.GUEST.TABLE_NAME,
          projection,
          selection,
          args,
          null,
          null, null
        )

      if (cursor != null && cursor.count > 0) {
        while (cursor.moveToNext()) {
          val id = cursor.getInt(cursor.getColumnIndex(columnId))
          val name = cursor.getString(cursor.getColumnIndex(columnName))
          val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

          list.add(GuestModel(id, name, presence == 1))
        }
      }

      cursor.close()

    } catch (e: Exception) {
      return list
    }

    return list

  }

  fun getAbsent(): List<GuestModel> {

    val list = mutableListOf<GuestModel>()

    try {
      val db = guestDatabase.readableDatabase

      val columnId = DataBaseConstants.GUEST.COLUMNS.ID
      val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
      val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

      val projection = arrayOf(
        columnId,
        columnName,
        columnPresence
      )

      val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
      val args = arrayOf("0")

      val cursor =
        db.query(
          DataBaseConstants.GUEST.TABLE_NAME,
          projection,
          selection,
          args,
          null,
          null, null
        )

      if (cursor != null && cursor.count > 0) {
        while (cursor.moveToNext()) {
          val id = cursor.getInt(cursor.getColumnIndex(columnId))
          val name = cursor.getString(cursor.getColumnIndex(columnName))
          val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

          list.add(GuestModel(id, name, presence == 1))
        }
      }

      cursor.close()

    } catch (e: Exception) {
      return list
    }

    return list

  }
}
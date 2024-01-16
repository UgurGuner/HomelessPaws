package com.eugurguner.homelesspaws.data.dataSource

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.eugurguner.homelesspaws.data.repository.LocalStorageRepository

class LocalStorageDataSource(context: Context) : LocalStorageRepository {
    companion object {
        const val isAdminKey = "IS_ADMIN"
    }

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences?.edit()
    }

    override fun isAdmin(): Boolean {
        return sharedPreferences?.getBoolean(isAdminKey, false) ?: false
    }

    override fun setIsAdmin(isAdmin: Boolean) {
        editor?.putBoolean(isAdminKey, isAdmin)
        editor?.commit()
    }
}
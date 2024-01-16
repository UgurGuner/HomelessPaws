package com.eugurguner.homelesspaws.data.repository

interface LocalStorageRepository {
    fun isAdmin(): Boolean

    fun setIsAdmin(isAdmin: Boolean)
}
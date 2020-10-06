package com.valartech.base


import com.valartech.data.db.Database
import com.valartech.data.retrofit.APIClient
import com.valartech.global.helper.SharedPreferences

/**
 * this is the base repository class, all project repositories should extends this class.
 */
abstract class BaseRepository(protected val apiClient: APIClient, protected val sharedPreferences: SharedPreferences, protected val database: Database)

package com.groktor.kings

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.groktor.kings.api.KingsService
import com.groktor.kings.data.KingsRepository
import com.groktor.kings.db.AccountDatabase
import com.groktor.kings.db.BattleDatabase
import com.groktor.kings.db.KingsLocalCache
import com.groktor.kings.ui.games.details.GameDetailsViewModelFactory
import com.groktor.kings.ui.home.HomeViewModelFactory
import com.groktor.kings.ui.profile.UserProfileViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    private fun provideCache(context: Context): KingsLocalCache {
        val battleDatabase = BattleDatabase.getInstance(context)
        val accountDatabase = AccountDatabase.getInstance(context)
        return KingsLocalCache(battleDatabase.battlesDao(),accountDatabase.accountDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideKingsRepository(context: Context): KingsRepository {
        return KingsRepository(KingsService.create(), provideCache(context))
    }

    fun provideHomeViewModelFactory(context: Context): ViewModelProvider.Factory {
        return HomeViewModelFactory(provideKingsRepository(context))
    }

    fun provideUserProfileViewModelFactory(context: Context): ViewModelProvider.Factory {
        return UserProfileViewModelFactory(provideKingsRepository(context))
    }

    fun provideGameDetailsViewModelFactory(context: Context): ViewModelProvider.Factory {
        return GameDetailsViewModelFactory(provideKingsRepository(context))
    }

}
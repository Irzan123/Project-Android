package psti.unram.habittracker.authdi

import android.content.Context
import psti.unram.habittracker.authdata.UserRepository
import psti.unram.habittracker.authdata.pref.UserPreference
import psti.unram.habittracker.authdata.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}
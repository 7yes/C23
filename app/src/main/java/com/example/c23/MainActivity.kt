package com.example.c23

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.c23.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val Context.dataStore by preferencesDataStore("USER_PREFERENCE_NAME")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                saveInDatastore(binding.etData.text.toString(), binding.cbVip.isChecked)
            }
        }
        binding.btnGet.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                readFromDataStore().collect(){
                    withContext(Dispatchers.Main){
                        binding.tvData.text = it.name
                        binding.cbAnswer.isChecked =it.vip
                    }
                }
            }
        }
    }

    private fun readFromDataStore() = dataStore.data.map {
        UserProfile(name = it[stringPreferencesKey("name")].orEmpty(),
        vip = it[booleanPreferencesKey("vip")] ?: true
        )
    }

    private suspend fun saveInDatastore(dato: String, checked: Boolean) {
        dataStore.edit {
            it[stringPreferencesKey("name")] = dato
            it[booleanPreferencesKey("vip")] = checked
        }
    }

    data class UserProfile(val name: String, var vip: Boolean)


}
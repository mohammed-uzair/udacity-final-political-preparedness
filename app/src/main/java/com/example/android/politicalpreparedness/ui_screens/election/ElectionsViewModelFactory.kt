package com.example.android.politicalpreparedness.ui_screens.election

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.data_source.database.ElectionDatabase
import com.example.android.politicalpreparedness.data_source.network.CivicsApi
import com.example.android.politicalpreparedness.data_source.network.CivicsApiService
import com.example.android.politicalpreparedness.repository.ElectionsRepositoryImplementation
import kotlinx.coroutines.Dispatchers

//TODO: Create Factory to generate ElectionViewModel with provided election datasource
class ElectionsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)) {
            val dispatchers = Dispatchers.IO

            val electionDao = ElectionDatabase.getInstance(context).electionDao
            val civicsApiService: CivicsApiService = CivicsApi.retrofitService
            val electionsRepository =
                ElectionsRepositoryImplementation(electionDao, civicsApiService)
            return ElectionsViewModel(dispatchers, electionsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
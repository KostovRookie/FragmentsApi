package com.example.bottom.commitList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bottom.models.CommitModel
import com.example.bottom.models.ErrorResponse
import com.example.bottom.utilities.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CommitViewModel @Inject constructor(
    repository: CommitListRepository
) : ViewModel() {

    private val repos = repository

    fun getCommitList(
        slug: String,
        title: String,
        //status: Int = Const.PER_PAGE // default for now 10
    ): LiveData<Pair<List<CommitModel>?, ErrorResponse?>> {

        return liveData(Dispatchers.Main) {
            val response = repos.getCommitList(slug, title)
            emit(response)
        }


    }

}
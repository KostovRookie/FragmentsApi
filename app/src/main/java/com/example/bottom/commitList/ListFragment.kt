package com.example.bottom.commitList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottom.R
import com.example.bottom.adapter.CommitAdapter
import com.example.bottom.databinding.FragmentCommitListBinding
import com.example.bottom.models.CommitModel
import com.example.bottom.utilities.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_commit_list), CommitAdapter.Interaction {
    private lateinit var binding: FragmentCommitListBinding
    private val viewModel: CommitViewModel by viewModels()

    private lateinit var mAdapter: CommitAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommitListBinding.bind(view)
        mAdapter = CommitAdapter(this)
        setupView()
        callForData()
    }

    private fun setupView() {
        binding.commitList.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun callForData() {

        viewModel.getCommitList(Const.category, Const.status)
            .observe(viewLifecycleOwner) {
                if (it.first != null) {
                    val list = it.first
                    list?.let { it1 -> mAdapter.submitList(it1) }
                } else {
                    Toast.makeText(context, "Error : ${it.second?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }


    }

    override fun onItemSelected(position: Int, item: CommitModel) {

    }
}
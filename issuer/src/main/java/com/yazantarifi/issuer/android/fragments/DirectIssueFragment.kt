package com.yazantarifi.issuer.android.fragments

import android.Manifest
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.ImagePickerContactResults
import com.yazantarifi.issuer.android.IssuerConsts
import com.yazantarifi.issuer.android.IssuerScreen
import com.yazantarifi.issuer.android.adapters.ImagesAdapter
import com.yazantarifi.issuer.android.impl.DirectIssueFragmentImplementation
import com.yazantarifi.issuer.android.listeners.ImagesAdapterClickListener
import com.yazantarifi.issuer.android.requestPermission
import kotlinx.android.synthetic.main.fragment_direct_issues.*

class DirectIssueFragment : Fragment(R.layout.fragment_direct_issues), DirectIssueFragmentImplementation {

    companion object {
        const val TAG = "DirectIssueFragment"
    }

    private val readStoragePermissionResult: ActivityResultLauncher<String> by requestPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        granted = {
            pickImagesFromGallery()
        }, denied = {
            Log.d(TAG, "Denied")
        })

    private val imagesRequest = registerForActivityResult(ImagePickerContactResults()) { result ->
        imagesRecyclerView?.let {
            (it.adapter as? ImagesAdapter)?.addImage(result.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
    }

    override fun initArguments() {
        arguments?.let {
            it.getString(IssuerConsts.TEXT_INPUT_HINT, "")?.let {
                if (!TextUtils.isEmpty(it)) {
                    textInputField?.hint = it
                }
            }

            it.getBoolean(IssuerConsts.IS_IMAGE_ATTACHMENT_ENABLED, false)?.let {
                when (it) {
                    true -> setupImagesRecyclerView()
                    false -> imagesRecyclerView?.visibility = View.GONE
                }
            }
        }
    }

    override fun setupViewsListeners() {
        button?.setOnClickListener {
            (activity as? IssuerScreen)?.finishScreen()
        }

        textInputField?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) {
                (activity as? IssuerScreen)?.updateTextInput(s?.toString())
            }
        })
    }

    override fun setupImagesRecyclerView() {
        activity?.let {
            imagesRecyclerView?.apply {
                this.visibility = View.VISIBLE
                this.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = ImagesAdapter(object: ImagesAdapterClickListener {
                    override fun onDeleteImageClicked(position: Int) {

                    }

                    override fun onAddImageClicked() {
                        readStoragePermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                })
            }
        }
    }

    override fun pickImagesFromGallery() {
        imagesRequest.launch(null)
    }

}

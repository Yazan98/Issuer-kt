package com.yazantarifi.issuer.android.fragments

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.*
import com.yazantarifi.issuer.android.adapters.ImagesAdapter
import com.yazantarifi.issuer.android.data.IssueInfoType
import com.yazantarifi.issuer.android.data.IssuerEvents
import com.yazantarifi.issuer.android.impl.DirectIssueFragmentImplementation
import com.yazantarifi.issuer.android.listeners.ImagesAdapterClickListener
import kotlinx.android.synthetic.main.fragment_direct_issues.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class DirectIssueFragment : Fragment(R.layout.fragment_direct_issues), DirectIssueFragmentImplementation {

    private val readStoragePermissionResult: ActivityResultLauncher<String> by requestPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        granted = {
            IssuerConfig.sendEventName(IssuerEvents.ON_PERMISSION_GENERATED, arguments)
            pickImagesFromGallery()
        }, denied = {
            IssuerConfig.sendEventName(IssuerEvents.ON_PERMISSION_DENIED, arguments)
        })

    private val imagesRequest = registerForActivityResult(ImagePickerContactResults()) { result ->
        imagesRecyclerView?.let {
            ImagePickerContactResults.getRealPathFromURI(requireContext(), result)?.let { it1 ->
                (it.adapter as? ImagesAdapter)?.addImage(it1)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        setupViewsListeners()
        IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_VIEW, arguments)
    }

    override fun initArguments() {
        arguments?.let {
            it.getString(IssuerConsts.TEXT_INPUT_HINT, "")?.let {
                if (!TextUtils.isEmpty(it)) {
                    textInputField?.hint = it
                }
            }

            it.getString(IssuerConsts.PRIVACY_POLICY_TEXT, "")?.let {
                if (!TextUtils.isEmpty(it)) {
                    privacyPolicyText?.text = it
                }
            }

            it.getString(IssuerConsts.PRIVACY_POLICY_LINK, "")?.let { link ->
                if (!TextUtils.isEmpty(link)) {
                    privacyPolicyText?.setOnClickListener {
                        openWebBrowser(link)
                    }
                }
            }

            it.getBoolean(IssuerConsts.IS_IMAGE_ATTACHMENT_ENABLED, false)?.let {
                when (it) {
                    true -> setupImagesRecyclerView()
                    false -> imagesRecyclerView?.visibility = View.GONE
                }
            }

            try {
                initCollectionInfo(it)
            } catch (ex: Exception) {
                IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
            }
        }
    }

    override fun initCollectionInfo(arguments: Bundle) {
        val isInformationCollectionEnabled = arguments.getBoolean(IssuerConsts.IS_COLLECTED_INFORMATION_ENABLED, false) ?: false
        val fullInfoText = arguments.getString(IssuerConsts.DEVICE_INFORMATION_MODE, "") ?: ""
        if (!isInformationCollectionEnabled) {
            return
        }

        IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_COLLECT_DEVICE_INFO, arguments)
        val deviceInfo = IssuerApplicationInformation(requireActivity().applicationContext)
        infoContainer?.visibility = View.VISIBLE
        infoText?.let {
            val textResults = if (TextUtils.equals(fullInfoText, IssueInfoType.FULL.key)) {
                deviceInfo.getFullTextInfo()
            } else {
                deviceInfo.getDeviceInfo()
            }

            (activity as? IssuerScreen)?.setSystemTextInfo(textResults)
            it.text = textResults
        }
    }

    override fun openWebBrowser(link: String?) {
        link?.let {
            try {
                IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_PRIVACY_LINK_CLICK, arguments)
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            } catch (ex: Exception) {
                IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
            }
        }
    }

    override fun setupViewsListeners() {
        sendButton?.setOnClickListener {
            IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_NEXT_OPTION, arguments)
            (activity as? IssuerScreen)?.let {
                it.setImages((imagesRecyclerView?.adapter as? ImagesAdapter?)?.getAllImages())
                it.finishScreen()
            }
        }

        textInputField?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) {
                (activity as? IssuerScreen)?.updateTextInput(s?.toString())
                GlobalScope.launch {
                    delay(3000)
                    IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_TEXT_INPUT_TYPE, arguments)
                }
            }
        })
    }

    override fun setupImagesRecyclerView() {
        activity?.let {
            imagesRecyclerView?.apply {
                this.visibility = View.VISIBLE
                this.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = ImagesAdapter(object: ImagesAdapterClickListener {
                    override fun onAddImageClicked() {
                        IssuerConfig.sendEventName(IssuerEvents.ISSUE_SCREEN_ADD_IMAGE_CLICK, arguments)
                        readStoragePermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }

                    override fun onImageClicked(path: String?) {

                    }
                })
            }
        }
    }

    override fun pickImagesFromGallery() {
        imagesRequest.launch(null)
    }

}

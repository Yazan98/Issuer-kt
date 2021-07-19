package com.yazantarifi.issuer.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.data.IssuerResultBundle
import com.yazantarifi.issuer.android.data.IssuesScreenMode
import com.yazantarifi.issuer.android.impl.IssuerScreenImplementation
import com.yazantarifi.issuer.android.listeners.EmailSelectionListener
import kotlinx.android.synthetic.main.screen_issuer.*

class IssuerScreen : AppCompatActivity(), IssuerScreenImplementation {

    private var screenResults: IssuerResultBundle? = null

    companion object {
        const val EMAIL_TITLE_KEY = 0
        const val EMAIL_MESSAGE_KEY = 1

        @JvmStatic
        inline fun startScreen(context: FragmentActivity?, intent: () -> Unit) {
            try {
                intent()
                context?.startActivityForResult(
                    IssuerIntent.build(context),
                    IssuerConsts.REQUEST_CODE
                )
            } catch (ex: Exception) {
                IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_issuer)
        intent?.extras?.let {
            initScreenTitle(it)
            setupStartScreen(it)
        }
    }

    override fun setupStartScreen(extras: Bundle?) {
        val screenMode =
            extras?.getString(IssuerConsts.SCREENS_MODE, "") ?: IssuesScreenMode.DIRECT_REPORT.key
        if (TextUtils.isEmpty(screenMode)) {
            return
        }

        moveToStartScreen(getStartFragmentId(screenMode))
    }

    override fun moveToStartScreen(screenName: Int) {
        try {
            findNavController(R.id.nav_host_fragment).let { controller ->
                controller.navInflater.inflate(R.navigation.issuer_graph).let {
                    it.startDestination = screenName
                    controller.graph = it
                }
            }
        } catch (ex: Exception) {
            IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
        }
    }

    override fun getStartFragmentId(screenMode: String): Int {
        return if (TextUtils.equals(screenMode, IssuesScreenMode.DIRECT_REPORT.key)) {
            R.id.directIssueFragment
        } else if (TextUtils.equals(screenMode, IssuesScreenMode.LIST_SELECTION.key)) {
            R.id.categoriesFragment
        } else {
            R.id.categoriesFragment
        }
    }

    @SuppressLint("RestrictedApi")
    override fun initScreenTitle(extras: Bundle?) {
        val screenTitle = extras?.getString(IssuerConsts.SCREEN_TITLE_TEXT, "") ?: ""
        screenToolbar?.let {
            this.setSupportActionBar(it)
            this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        when (TextUtils.isEmpty(screenTitle)) {
            true -> this.supportActionBar?.title = getString(R.string.screen_title)
            false -> this.supportActionBar?.title = screenTitle
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishScreen()
        return super.onOptionsItemSelected(item)
    }

    override fun isEmailDialogEnabled(): Boolean {
        return intent?.extras?.getBoolean(IssuerConsts.IS_EMAIL_SELECTION_ENABLED, false) ?: false
    }

    override fun showEmailDialogInput() {
        showEmailDialog(this, getEmailExtras(EMAIL_TITLE_KEY), getEmailExtras(EMAIL_MESSAGE_KEY), object : EmailSelectionListener {
                override fun onEmailSelected(email: String?) {
                    screenResults?.setEmailResult(email)
                    finishScreen()
                }

                override fun onDialogDismissed() {
                    finishScreen()
                }
            })
    }

    override fun getEmailExtras(key: Int): String {
        return when (key) {
            EMAIL_MESSAGE_KEY -> {
                val attachedText = intent?.extras?.getString(IssuerConsts.EMAIL_MESSAGE, "") ?: ""
                if (TextUtils.isEmpty(attachedText)) {
                    getString(R.string.email_message)
                } else {
                    attachedText
                }
            }

            EMAIL_TITLE_KEY -> {
                val attachedText = intent?.extras?.getString(IssuerConsts.EMAIL_TITLE, "") ?: ""
                if (TextUtils.isEmpty(attachedText)) {
                    getString(R.string.email_title)
                } else {
                    attachedText
                }
            }

            else -> {
                getString(R.string.email_message)
            }
        }
    }

    override fun finishScreen() {
        setResult(IssuerConsts.RESULT_CODE, screenResults)
        super.finish()
    }

    override fun finish() {
        if (isEmailDialogEnabled()) {
            showEmailDialogInput()
        } else {
            finishScreen()
        }
    }

}

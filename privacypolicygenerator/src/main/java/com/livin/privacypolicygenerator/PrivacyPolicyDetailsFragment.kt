package com.livin.privacypolicygenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_privacy_policy_details.*


private const val TYPE = "item_type"


class PrivacyPolicyDetailsFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        socialCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                socialMediaLayout.visibility = View.VISIBLE
            } else {
                socialMediaLayout.visibility = View.GONE
            }
        }

        deviceDataSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                deviceInformationLayout.visibility = View.VISIBLE
            } else {
                deviceInformationLayout.visibility = View.GONE
            }
        }

        userDataSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userInformationLayout.visibility = View.VISIBLE
            } else {
                userInformationLayout.visibility = View.GONE
            }
        }

        emailSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                emailInformationLayout.visibility = View.VISIBLE
            } else {
                emailInformationLayout.visibility = View.GONE
            }
        }

        analyticsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                analyticsInformationLayout.visibility = View.VISIBLE
            } else {
                analyticsInformationLayout.visibility = View.GONE
            }
        }

        adsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                adsInformationLayout.visibility = View.VISIBLE
            } else {
                adsInformationLayout.visibility = View.GONE
            }
        }

        paymentSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                paymentInformationLayout.visibility = View.VISIBLE
            } else {
                paymentInformationLayout.visibility = View.GONE
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
            PrivacyPolicyDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE, type)
                }
            }
    }
}
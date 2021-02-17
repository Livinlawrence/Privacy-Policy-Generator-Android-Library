package com.livin.privacypolicygenerator

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.livin.privacypolicygenerator.interfaces.ActivityCallBack
import kotlinx.android.synthetic.main.fragment_privacy_policy_details.*
import java.text.SimpleDateFormat
import java.util.*


private const val TYPE = "item_type"


class PrivacyPolicyDetailsFragment : Fragment() {
    private var param1: Int = -1
    private var activityCallBack: ActivityCallBack? = null
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivityCallBack) {
            activityCallBack = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TYPE)
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
        MobileAds.initialize(requireContext())
        mInterstitialAd = InterstitialAd(requireContext())
        mInterstitialAd.adUnitId = "ca-app-pub-3910661356070266/5703917892"
        //mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"//Test
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        userDataSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userInformationLayout.visibility = View.VISIBLE
            } else {
                userInformationLayout.visibility = View.GONE
            }
        }

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

        reMarketingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reMarketingLayout.visibility = View.VISIBLE
            } else {
                reMarketingLayout.visibility = View.GONE
            }
        }

        reMarketingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reMarketingLayout.visibility = View.VISIBLE
            } else {
                reMarketingLayout.visibility = View.GONE
            }
        }

        businessCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                businessInformationLayout.visibility = View.VISIBLE
                developerNameTextInputLayout.visibility = View.GONE
            } else {
                businessInformationLayout.visibility = View.GONE
            }
        }

        individualCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                developerNameTextInputLayout.visibility = View.VISIBLE
                businessInformationLayout.visibility = View.GONE
            } else {
                developerNameTextInputLayout.visibility = View.GONE
            }
        }

        webCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                websiteUrlTextInputLayout.visibility = View.VISIBLE
            } else {
                websiteUrlTextInputLayout.visibility = View.GONE
            }
        }




        effectiveDateText.text =
            "Effective Date: " + SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        var cal = Calendar.getInstance()

        effectiveDateText.setOnClickListener {
            showDatePicker(cal)
        }


        generateButton.setOnClickListener {
            validateRequiredFields()
        }
        setupTextWatchers()
    }

    private fun setupTextWatchers() {
        productNameEditText.doOnTextChanged { _, _, _, _ ->
            productNameTextInputLayout.error = null
        }
        websiteEditText.doOnTextChanged { _, _, _, _ ->
            websiteUrlTextInputLayout.error = null
        }
        developerNameEditText.doOnTextChanged { _, _, _, _ ->
            developerNameTextInputLayout.error = null
        }
        businessAddressEditText.doOnTextChanged { _, _, _, _ ->
            businessAddressTextInputLayout.error = null
        }
        businessEmailEditText.doOnTextChanged { _, _, _, _ ->
            businessEmailTextInputLayout.error = null
        }

    }

    private fun validateRequiredFields() {
        val effectiveDate = effectiveDateText.text.toString()
        val productName = productNameEditText.text.toString()
        val productWebsite = websiteEditText.text.toString()
        val businessAddress = businessAddressEditText.text.toString()
        val businessEmail = businessEmailEditText.text.toString()
        val businessPhone = businessContactNumberEditText.text.toString()
        val businessCountry = businessCountryEditText.text.toString()
        val developerName = developerNameEditText.text.toString()


        /* val productName = "Holy Bible"
         val productWebsite = "www.holybible.co.in"
         val businessAddress = "Chalakudy"
         val businessEmail = "livin@holybible.co.in"
         val businessPhone = "9633683734"
         val businessCountry = "India"
         val developerName = "Livin"*/

        if (productName.isEmpty()) {
            productNameTextInputLayout.error = getString(R.string.mandatory_field)
        } else if (!webCheckBox.isChecked && !appCheckBox.isChecked) {
            userWhereText.snack(getString(R.string.where_will_your_privacy_used)) {}
        } else if (webCheckBox.isChecked && productWebsite.isEmpty()) {
            websiteUrlTextInputLayout.error = getString(R.string.mandatory_field)
        } else if (webCheckBox.isChecked && !Patterns.WEB_URL.matcher(productWebsite).matches()) {
            websiteUrlTextInputLayout.error = getString(R.string.invalid_website)
        } else if (!businessCheckBox.isChecked && !individualCheckBox.isChecked) {
            userWhereText.snack(getString(R.string.select_entity_type)) {}
        } else if (individualCheckBox.isChecked && developerName.isEmpty()) {
            developerNameTextInputLayout.error = getString(R.string.mandatory_field)
        } else if (businessCheckBox.isChecked && businessAddress.isEmpty()) {
            businessAddressTextInputLayout.error = getString(R.string.mandatory_field)
        } else if (businessCheckBox.isChecked && businessEmail.isEmpty()) {
            businessEmailTextInputLayout.error = getString(R.string.mandatory_field)
        } else if (businessCheckBox.isChecked && !Patterns.EMAIL_ADDRESS.matcher(businessEmail)
                .matches()
        ) {
            businessEmailTextInputLayout.error = getString(R.string.invalid_email_address)
        } else {

            val business = PrivacyPolicyBuilder.Business(
                productName,
                productWebsite,
                businessAddress,
                businessEmail,
                businessPhone,
                businessCountry
            )

            val userInformation = PrivacyPolicyBuilder.UserInformation(
                nameCheckBox.isChecked,
                emailCheckBox.isChecked,
                phoneCheckBox.isChecked,
                addressCheckBox.isChecked,
                socialCheckBox.isChecked
            )

            val socialMediaInformation = PrivacyPolicyBuilder.SocialMediaProfileInformation(
                fbCheckBox.isChecked,
                twitterCheckBox.isChecked,
                googleCheckBox.isChecked,
                linkedInCheckBox.isChecked
            )

            val deviceInformation = PrivacyPolicyBuilder.DeviceInformation(
                locationCheckBox.isChecked,
                contactCheckBox.isChecked,
                cameraCheckBox.isChecked,
                storageCheckBox.isChecked
            )

            val analyticsInformation = PrivacyPolicyBuilder.AnalyticsInformation(
                firebaseCheckBox.isChecked,
                googleAnalyticsCheckBox.isChecked,
                facebookCheckBox.isChecked,
                appsFlyerCheckBox.isChecked,
                flurryCheckBox.isChecked,
                mixpanelCheckBox.isChecked,
                other = otherCheckBox.isChecked
            )

            val emailInformation = PrivacyPolicyBuilder.EmailInformation(
                mailChimpCheckBox.isChecked,
                constantContactCheckBox.isChecked,
                aweberCheckBox.isChecked,
                getResponseCheckBox.isChecked,
                otherEmailCheckBox.isChecked
            )

            val adsInformation = PrivacyPolicyBuilder.AdsInformation(
                googleAdmob = admobCheckBox.isChecked,
                bing = bingCheckBox.isChecked,
                startApp = startAppCheckBox.isChecked,
                flurry = flurryAdsCheckBox.isChecked,
                moPub = moPubCheckBox.isChecked,
                inMobi = inMobiCheckBox.isChecked,
                adColony = adColonyCheckBox.isChecked,
                appLovin = appLovinCheckBox.isChecked,
                facebook = fbAdsCheckBox.isChecked,
                amazon = amazonCheckBox.isChecked,
                other = otherAdCheckBox.isChecked
            )

            val paymentInformation = PrivacyPolicyBuilder.PaymentInformation(
                bankTransfer = bankCheckBox.isChecked,
                inAppPayment = inAppPaymentCheckBox.isChecked,
                stripe = stripeCheckBox.isChecked,
                upi = upiCheckBox.isChecked,
                paytm = paytmCheckBox.isChecked,
                googlePay = googlePayCheckBox.isChecked,
                wePay = wePayCheckBox.isChecked,
                worldPay = worldPayCheckBox.isChecked,
                paypal = paypalCheckBox.isChecked,
                brainTree = brainTreeCheckBox.isChecked,
                sage = sagePayCheckBox.isChecked,
                razorPay = razorPayCheckBox.isChecked,
                ccAvenue = ccAvenueCheckBox.isChecked,
                other = otherPaymentCheckBox.isChecked
            )

            val reMarketingInformation = PrivacyPolicyBuilder.ReMarketingInformation(
                googleAds = googleAdsCheckBox.isChecked,
                facebook = fbReMarketingCheckBox.isChecked,
                twitter = twitterReMarketingCheckBox.isChecked,
                bing = bingReMarketingCheckBox.isChecked,
                pinterest = pinterestCheckBox.isChecked,
                other = otherReMarketingCheckBox.isChecked
            )

            //TODO  fields in UI to be added
            val captchaProviderInformation = PrivacyPolicyBuilder.CaptchaProviderInformation()


            /* val contactEmail = "livinlawrence@gmail.com"
             val contactWebsite = "www.holybible.co.in"*/
            val contactEmail = contactEmailEditText.text.toString()
            val contactWebsite = contactWebsiteEditText.text.toString()
            val contactPhone = contactPhoneEditText.text.toString()
            val contactAddress = contactAddressEditText.text.toString()

            if (contactEmail.isEmpty()) {
                contactEmailTextInputLayout.parent.requestChildFocus(
                    contactEmailTextInputLayout,
                    contactEmailTextInputLayout
                )
                contactEmailTextInputLayout.error = getString(R.string.invalid_email_address)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(contactEmail).matches()) {
                contactEmailTextInputLayout.error = getString(R.string.invalid_email_address)
            } else if (contactWebsite.isEmpty()) {
                contactWebsiteTextInputLayout.error = getString(R.string.invalid_website)
                contactWebsiteTextInputLayout.parent.requestChildFocus(
                    contactWebsiteTextInputLayout,
                    contactWebsiteTextInputLayout
                )
            } else if (!Patterns.WEB_URL.matcher(contactWebsite).matches()) {
                contactWebsiteTextInputLayout.error = getString(R.string.invalid_website)
            } else {
                val contactInformation = PrivacyPolicyBuilder.ContactInformation(
                    contactEmail,
                    contactWebsite,
                    contactPhone,
                    contactAddress
                )

                val htmlString = PrivacyPolicyBuilder(
                    context,
                    effectiveDate,
                    productName,
                    business,
                    developerName,
                    userInformation,
                    socialMediaInformation,
                    deviceInformation,
                    analyticsInformation,
                    emailInformation,
                    adsInformation,
                    paymentInformation,
                    reMarketingInformation,
                    captchaProviderInformation,
                    contactInformation,
                    ccpaSwitch.isChecked,
                    gdprSwitch.isChecked,
                    caloppaSwitch.isChecked,
                    individualCheckBox.isChecked,
                    businessCheckBox.isChecked,
                    appCheckBox.isChecked,
                    webCheckBox.isChecked,
                    userDataSwitch.isChecked,
                    deviceDataSwitch.isChecked,
                    analyticsSwitch.isChecked,
                    adsSwitch.isChecked,
                    emailSwitch.isChecked,
                    paymentSwitch.isChecked,
                    reMarketingSwitch.isChecked,
                    cookiesDataSwitch.isChecked,
                    childSwitch.isChecked
                ).build()



                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()

                    mInterstitialAd.adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                        }

                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            activityCallBack?.htmlGenerated(htmlString)
                        }

                        override fun onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        override fun onAdClicked() {
                            // Code to be executed when the user clicks on an ad.
                        }

                        override fun onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        override fun onAdClosed() {
                            activityCallBack?.htmlGenerated(htmlString)
                        }
                    }
                } else {
                    activityCallBack?.htmlGenerated(htmlString)
                }
            }
        }
    }


    private fun showDatePicker(cal: Calendar) {
        val dpd = DatePickerDialog(
            requireContext(),
            { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd.MM.yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                effectiveDateText.text = "Effective Date: " + sdf.format(cal.time)
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
            PrivacyPolicyDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE, type)
                }
            }

        const val TAG = "PrivacyPolicyDetailsFragment"
    }
}
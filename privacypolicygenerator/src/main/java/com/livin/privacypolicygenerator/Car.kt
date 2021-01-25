package com.livin.privacypolicygenerator

class Car(
    val productName: String?,
    val businessDetails: Business?,
    val individualName: String?,
    val userInformation: UserInformation?,
    val socialMediaProfileInformation: SocialMediaProfileInformation?,
    val deviceInformation: DeviceInformation?,
    val analyticsInformation: AnalyticsInformation?,
    val emailInformation: EmailInformation?,
    val adsInformation: AdsInformation?,
    val paymentInformation: PaymentInformation?,
    val remarketingInformation: RemarketingInformation?,
    val captchaProviderInformation: CaptchaProviderInformation?,
    val contactInformation: ContactInformation,
    val includeCCPAWording: Boolean = false,
    val includeGDPRWording: Boolean = false,
    val includeCalOPPAWording: Boolean = false
) {

    data class Builder(
        var model: String? = null,
        var color: String? = null,
        var type: String? = null
    ) {

        fun model(model: String) = apply { this.model = model }
        fun color(color: String) = apply { this.color = color }
        fun type(type: String) = apply { this.type = type }
        // fun build() = Car(model, color, type)
    }

    data class Business(
        var businessName: String? = null,
        var businessAddress: String? = null,
        var businessEmail: String? = null,
        var businessContactNumber: String? = null,
        var businessCountry: String? = null
    )

    data class UserInformation(
        var name: String? = null,
        var email: String? = null,
        var phoneNumber: String? = null,
        var address: String? = null,
        var social: String? = null,
        var realtimeLocation: String? = null
    )

    data class SocialMediaProfileInformation(
        var fb: String? = null,
        var twitter: String? = null,
        var google: String? = null,
        var linkedin: String? = null
    )

    data class DeviceInformation(
        var location: String? = null,
        var contacts: String? = null,
        var camera: String? = null,
        var storage: String? = null
    )

    data class AnalyticsInformation(
        var firebase: String? = null,
        var google: String? = null,
        var facebook: String? = null,
        var appsflyer: String? = null,
        var flurry: String? = null,
        var mixpanel: String? = null,
        var gameAnalytics: String? = null,
        var amazon: String? = null,
        var fabric: String? = null,
        var amplitude: String? = null,
        var countly: String? = null,
        var appsee: String? = null,
        var fathom: String? = null,
        var other: String? = null
    )

    data class EmailInformation(
        var mailChimp: String? = null,
        var aWeber: String? = null,
        var getResponse: String? = null,
        var other: String? = null
    )

    data class AdsInformation(
        var googleAdmob: String? = null,
        var bing: String? = null,
        var startApp: String? = null,
        var flurry: String? = null,
        var moPub: String? = null,
        var inMobi: String? = null,
        var adColony: String? = null,
        var appLovin: String? = null,
        var adButler: String? = null,
        var unityAds: String? = null,
        var facebook: String? = null,
        var amazon: String? = null,
        var other: String? = null
    )

    data class PaymentInformation(
        var bankTransfer: String? = null,
        var inAppPayment: String? = null,
        var stripe: String? = null,
        var googlePay: String? = null,
        var paytm: String? = null,
        var upi: String? = null,
        var wePay: String? = null,
        var worldPay: String? = null,
        var paypal: String? = null,
        var brainTree: String? = null,
        var fastSpring: String? = null,
        var sage: String? = null,
        var razorPay: String? = null,
        var ccAvenue: String? = null,
        var other: String? = null
    )

    data class RemarketingInformation(
        var googleAds: String? = null,
        var facebook: String? = null,
        var twitter: String? = null,
        var braintree: String? = null,
        var pinterest: String? = null,
        var adRoll: String? = null,
        var wePay: String? = null,
        var worldPay: String? = null,
        var paypal: String? = null,
        var brainTree: String? = null,
        var fastSpring: String? = null,
        var sage: String? = null,
        var razorPay: String? = null,
        var ccAvenue: String? = null,
        var other: String? = null
    )

    data class CaptchaProviderInformation(
        var captcha: String? = null,
        var googlePlaces: String? = null,
        var mouseFlow: String? = null,
        var freshDesk: String? = null
    )

    data class ContactInformation(
        var email: String? = null,
        var web: String? = null,
        var phone: String? = null,
        var postalAddress: String? = null
    )

    data class Address(
        var street: String? = null,
        var city: String? = null,
        var province: String? = null,
        var state: String? = null,
        var zip: String? = null,
        var country: String? = null
    )

    fun generate() {

    }
}
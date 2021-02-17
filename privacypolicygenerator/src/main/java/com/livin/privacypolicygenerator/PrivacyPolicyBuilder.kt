package com.livin.privacypolicygenerator

import android.content.Context
import com.livin.privacypolicygenerator.Utils.loadAssetTextAsString

class PrivacyPolicyBuilder(
    val context: Context?,
    val effectiveDate: String?,
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
    val reMarketingInformation: ReMarketingInformation?,
    val captchaProviderInformation: CaptchaProviderInformation?,
    val contactInformation: ContactInformation,
    val includeCCPAWording: Boolean = false,
    val includeGDPRWording: Boolean = false,
    val includeCalOPPAWording: Boolean = false,
    val singleOwner: Boolean = false,
    val businessOwner: Boolean = false,
    val isApp: Boolean = false,
    val isWeb: Boolean = false,
    val collectingUserData: Boolean = false,
    val collectingDeviceData: Boolean = false,
    val usingAnalytics: Boolean = false,
    val showingAds: Boolean = false,
    val sendingEmails: Boolean = false,
    val hasPayment: Boolean = false,
    val reMarketingData: Boolean = false,
    val collectingCookies: Boolean = false,
    val collectingChildData: Boolean = false
) {

    data class Builder(
        var model: String? = null,
        var color: String? = null,
        var type: String? = null
    ) {

        fun model(model: String) = apply { this.model = model }
        fun color(color: String) = apply { this.color = color }
        fun type(type: String) = apply { this.type = type }
        //fun build() = PrivacyPolicyBuilder(model, color, type)
    }

    data class Business(
        var businessName: String? = null,
        var businessUrl: String? = null,
        var businessAddress: String? = null,
        var businessEmail: String? = null,
        var businessContactNumber: String? = null,
        var businessCountry: String? = null
    )

    data class UserInformation(
        var name: Boolean = false,
        var email: Boolean = false,
        var phoneNumber: Boolean = false,
        var address: Boolean = false,
        var social: Boolean = false,
        var realtimeLocation: Boolean = false
    )

    data class SocialMediaProfileInformation(
        var fb: Boolean = false,
        var twitter: Boolean = false,
        var google: Boolean = false,
        var linkedin: Boolean = false
    )

    data class DeviceInformation(
        var location: Boolean = false,
        var contacts: Boolean = false,
        var camera: Boolean = false,
        var storage: Boolean = false
    )

    data class AnalyticsInformation(
        var firebase: Boolean = false,
        var google: Boolean = false,
        var facebook: Boolean = false,
        var appsflyer: Boolean = false,
        var flurry: Boolean = false,
        var mixpanel: Boolean = false,
        var gameAnalytics: Boolean = false,
        var amazon: Boolean = false,
        var fabric: Boolean = false,
        var amplitude: Boolean = false,
        var countly: Boolean = false,
        var appsee: Boolean = false,
        var fathom: Boolean = false,
        var other: Boolean = false
    )

    data class EmailInformation(
        var mailChimp: Boolean = false,
        var constantContract: Boolean = false,
        var aWeber: Boolean = false,
        var getResponse: Boolean = false,
        var other: Boolean = false
    )

    data class AdsInformation(
        var googleAdmob: Boolean = false,
        var bing: Boolean = false,
        var startApp: Boolean = false,
        var flurry: Boolean = false,
        var moPub: Boolean = false,
        var inMobi: Boolean = false,
        var adColony: Boolean = false,
        var appLovin: Boolean = false,
        var adButler: Boolean = false,
        var unityAds: Boolean = false,
        var facebook: Boolean = false,
        var amazon: Boolean = false,
        var other: Boolean = false
    )

    data class PaymentInformation(
        var bankTransfer: Boolean = false,
        var inAppPayment: Boolean = false,
        var stripe: Boolean = false,
        var googlePay: Boolean = false,
        var paytm: Boolean = false,
        var upi: Boolean = false,
        var wePay: Boolean = false,
        var worldPay: Boolean = false,
        var paypal: Boolean = false,
        var brainTree: Boolean = false,
        var fastSpring: Boolean = false,
        var sage: Boolean = false,
        var razorPay: Boolean = false,
        var ccAvenue: Boolean = false,
        var other: Boolean = false
    )

    data class ReMarketingInformation(
        var googleAds: Boolean = false,
        var facebook: Boolean = false,
        var twitter: Boolean = false,
        var bing: Boolean = false,
        var braintree: Boolean = false,
        var pinterest: Boolean = false,
        var adRoll: Boolean = false,
        var other: Boolean = false
    )

    data class CaptchaProviderInformation(
        var captcha: Boolean = false,
        var googlePlaces: Boolean = false,
        var mouseFlow: Boolean = false,
        var freshDesk: Boolean = false
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

    private fun getHeaderOwner(): String {
        var locatedAt = "located at "
        if (businessDetails?.businessUrl?.isNotEmpty() == true) {
            locatedAt += businessDetails.businessUrl
        }
        var htmlString = "Website, $locatedAt"
        if (isApp && isWeb) {
            htmlString = "Website and App, $locatedAt"
        } else if (isApp) {
            htmlString = "App, $locatedAt"
        } else if (isWeb) {
            htmlString = "Website, $locatedAt"
        }

        return htmlString
    }

    private fun getTools(): String {
        var tools = ""

        if (isApp && isWeb) {
            tools = "Website and App"
        } else if (isApp) {
            tools = "App"
        } else if (isWeb) {
            tools = "Website"
        }

        return tools
    }

    private fun getHeader(): String {
        val sb = StringBuilder()
        if (businessOwner) {
            sb.append(
                "<h2 class=pb-3>Privacy Policy</h2><p> This Privacy Policy (\"Privacy Policy\") applies to <strong>" + productName + "'s </strong>" +
                        " apps, services, features, software, and website (together, \"Services\") unless specified otherwise. " +
                        "Our Privacy Policy was posted on " + effectiveDate + ". It governs the privacy terms of our " + getHeaderOwner() + "," +
                        " and the tools we provide you (the " + getTools() + " or the Service)." +
                        "Any capitalized terms not defined in our Privacy Policy, have the meaning as specified in our Terms.</p>"
            )
        } else if (singleOwner) {
            sb.append(
                "<p>" + individualName + " built " + productName + " as " + getTools() + " to provide various services. This Privacy Policy (\"Privacy Policy\") applies to <strong>" + productName + "'s </strong>" +
                        " apps, services, features, software, and website (together, \"Services\") unless specified otherwise. " +
                        "Our Privacy Policy was posted on " + effectiveDate + ". It governs the privacy terms of our " + getHeaderOwner() + "," +
                        " and the tools we provide you (the " + getTools() + " or the Service)." +
                        "Any capitalized terms not defined in our Privacy Policy, have the meaning as specified in our Terms.</p>"
            )
        }
        return sb.toString()
    }

    private fun getCollectingDataHtml(): String {
        var sb = StringBuilder()
        if (collectingUserData) {
            sb.append("<li> <p><strong>Personal Information</strong></p>")
            sb.append("<p>While using our Service, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you (Personal Data). Personally identifiable information may include, but is not limited to: ")
            sb.append("<ul>")
            if (userInformation?.email == true) {
                sb.append("<li>Email address</li>")
            }
            if (userInformation?.name == true) {
                sb.append("<li>Name</li>")
            }

            if (userInformation?.address == true) {
                sb.append("<li>Address</li>")
            }

            if (userInformation?.phoneNumber == true) {
                sb.append("<li>Phone Number</li>")
            }

            if (userInformation?.realtimeLocation == true) {
                sb.append("<li>Real time location</li>")
            }

            if (userInformation?.social == true) {
                sb.append("<li>Social Media</li>")
                sb.append("<ul>")
                if (socialMediaProfileInformation?.fb == true) {
                    sb.append("<li>Facebook</li>")
                }

                if (socialMediaProfileInformation?.google == true) {
                    sb.append("<li>Google</li>")
                }

                if (socialMediaProfileInformation?.twitter == true) {
                    sb.append("<li>Twitter</li>")
                }

                if (socialMediaProfileInformation?.linkedin == true) {
                    sb.append("<li>LinkedIn</li>")
                }
                sb.append("</ul>")
            }
            sb.append("</ul>")
            sb.append(
                "</p><p>We may use your Personal Data to contact you with newsletters, marketing or promotional " +
                        "materials and other information that may be of interest to you. You may opt out of receiving any, " +
                        "or all, of these communications from us by following the unsubscribe link or instructions provided in" +
                        " any email we send.</p>"
            )
            sb.append("</li>")
        }

        if (collectingDeviceData) {
            sb.append("<li> <p><strong>Device and Connection Information</strong></p>")
            sb.append(
                "<p>We collect device-specific information when you install, access, or use our Services. This includes information such as hardware model," +
                        "operating system information, browser information, IP address, " +
                        "mobile network information including phone number, and device identifiers. We collect device "
            )
            sb.append("<ul>")
            if (deviceInformation?.location == true) {
                sb.append("<li>Location</li>")
            }
            if (deviceInformation?.camera == true) {
                sb.append("<li>Camera</li>")
            }

            if (deviceInformation?.contacts == true) {
                sb.append("<li>Contacts</li>")
            }

            if (deviceInformation?.storage == true) {
                sb.append("<li>SD card or Storage space</li>")
            }
            sb.append("</ul>")
            sb.append(
                "</p><p>We collect all this device location information whenever you select any of this specific service." +
                        "We may use this data to improve the service experience to our users.</p>"
            )
            sb.append("</li>")
        }

        if (isWeb) {
            sb.append("<li> <p><strong>Cookies Data</strong></p>")
            sb.append(
                "<p>We collect device-specific information when you install, access, or use our Services. This includes information such as hardware model," +
                        "operating system information, browser information, IP address, " +
                        "mobile network information including phone number, and device identifiers. We collect device "
            )
            sb.append(
                "<p>We use cookies to operate and provide our Services, including to provide our Services that are web-based, improve your experiences, " +
                        "understand how our Services are being used, and customize our Services.</p>" +
                        "<p>Cookies are files with small amount of data which may include an anonymous unique identifier." +
                        " Cookies are sent to your browser from a website and stored on your device. Tracking technologies" +
                        " also used are beacons, tags, and scripts to collect and track information and to improve and analyze our Service.</p>" +
                        "<p>You can instruct your device to refuse all cookies or to indicate when a cookie is being sent." +
                        " However, if you do not accept cookies, you may not be able to use some portions of our Service.</p>" +
                        "<p>Examples of Cookies we use: <strong>session cookies</strong> (we use these cookies to operate our Service). " +
                        "Additionally, we may use cookies to remember your choices, such as your language preferences, and otherwise to customize our Services for you.</p>"
            )
            sb.append("</li>")
        }

        if (!collectingDeviceData && !collectingDeviceData && !isWeb) {
            sb.append(
                "<p>We don't collect any specific data of the user or device, however we collect device-specific " +
                        "information when you install, access, or use our Services. This includes information such as hardware model, " +
                        "operating system information, browser information, IP address, mobile network information including phone number, " +
                        "and device identifiers. We collect device location information if you use our location features, " +
                        "We collect contacts if you use our contact related features, " +
                        "and all this information collected are for diagnostics and troubleshooting " +
                        "purposes such as if you are having trouble with our app’s features.</p>"
            )
        }
        return sb.toString()
    }

    private fun getGDPR(): String {
        val sb = StringBuilder()
        if (includeGDPRWording) {
            sb.append(
                "<h2 class=pt-3 pb-3>Legal Basis for Processing Personal Data Under General Data Protection Regulation (GDPR)</h2>" +
                        "<p>If you are from the European Economic Area (EEA), \" + productName + \" legal basis for collecting and using the personal information described in this Privacy Policy depends on the Personal Data we collect and the specific context in which we collect it.</p>\\n\" +\n" +
                        "<p>" + productName + " may process your Personal Data because:</p>" +
                        "<ul><li>We need to perform a contract with you</li>" +
                        "<li>You have given us permission to do so</li>" +
                        "<li>The processing is in our legitimate interests and it's not overridden by your rights</li>" +
                        "<li>For payment processing purposes</li>" +
                        "<li>To comply with the law</li>" +
                        "</ul>"
            )
            sb.append(
                "<h2 class=pt-3 pb-3>Your Data Protection Rights Under General Data Protection Regulation (GDPR)</h2>" +
                        "<p>If you are a resident of the European Economic Area (EEA), you have certain data protection rights. " + productName + " aims to take reasonable steps to allow you to correct, amend, delete, or limit the use of your Personal Data.</p>\n" +
                        "<p>If you wish to be informed what Personal Data we hold about you and if you want it to be removed from our systems, please contact us.</p>\n" +
                        "<p>In certain circumstances, you have the following data protection rights:</p><ul><li>" +
                        "<p><strong>The right to access, update or to delete</strong> the information we have on you.</p></li><li>\n" +
                        "<p><strong>The right of rectification.</strong> You have the right to have your information rectified if that information is inaccurate or incomplete.</p></li><li>" +
                        "<p><strong>The right to object.</strong> You have the right to object to our processing of your Personal Data.</p></li><li>\n" +
                        "<p><strong>The right of restriction.</strong> You have the right to request that we restrict the processing of your personal information.</p></li><li>\n" +
                        "<p><strong>The right to data portability.</strong> You have the right to be provided with a copy of your Personal Data in a structured, machine-readable and commonly used format.</p></li><li>\n" +
                        "<p><strong>The right to withdraw consent.</strong> You also have the right to withdraw your consent at any time where " + productName + " relied on your consent to process your personal information.</p></li></ul>\n" +
                        "<p>Please note that we may ask you to verify your identity before responding to such requests.</p>\n" +
                        "<p>You have the right to complain to a Data Protection Authority about our collection and use of your Personal Data. For more information, please contact your local data protection authority in the European Economic Area (EEA).</p>"
            )
        }
        return sb.toString()
    }

    private fun getCCPA(): String {
        val sb = StringBuilder()
        if (includeCCPAWording) {
            sb.append(
                "<h2 class=pt-3 pb-3>Do Not Sell My Personal Information Notice for California consumers under California Consumer Privacy Act (CCPA)</h2>" +
                        "<p>Under the CCPA, California consumers have the right to:</p><ul>" +
                        "<li>Request that a business that collects a consumer's personal data disclose the categories and specific pieces of personal data that a business has collected about consumers.</li>" +
                        "<li>Request that a business delete any personal data about the consumer that a business has collected.</li>" +
                        "<li>Request that a business that sells a consumer's personal data, not sell the consumer's personal data.</li></ul>" +
                        "<p>If you make a request, we have 30 days to respond to you. If you would like to exercise any of these rights, please contact us.</p>"
            )
        }
        return sb.toString()
    }

    private fun getContact(): String {
        val sb = StringBuilder()
        contactInformation.email?.takeIf { it.isNotEmpty() }?.apply {
            sb.append(
                "<p> <a href=mailto:$this>$this</a></p>"
            )
        }

        contactInformation.web?.takeIf { it.isNotEmpty() }?.apply {
            sb.append(
                "<p> <a href=$this>$this</a></p>"
            )
        }


        contactInformation.postalAddress?.takeIf { it.isNotEmpty() }?.apply {
            sb.append(
                " <p> $this</p>"
            )
        }

        contactInformation.phone?.takeIf { it.isNotEmpty() }?.apply {
            sb.append(
                " <p> $this</p>"
            )
        }

        return sb.toString()
    }

    private fun getPaymentHtml(): String {
        val sb = StringBuilder()
        if (hasPayment) {
            sb.append(
                "<h3 class=text-muted pt-2 pb-3>Payments</h3>" +
                        " <p>We may provide paid products and/or services within the Service. In that case, we use third-party services for " +
                        "payment processing (e.g. payment processors).</p>" +
                        "  <p>We will not store or collect your payment card details. That information is provided directly to our " +
                        "third-party payment processors whose use of your personal information is governed by their Privacy Policy. " +
                        "These payment processors adhere to the standards set by PCI-DSS as managed by the PCI Security Standards Council, " +
                        "which is a joint effort of brands like Visa, Mastercard, American Express and Discover. PCI-DSS requirements " +
                        "help ensure the secure handling of payment information.</p>" +
                        " <p>The payment processors we work with are:</p>" +
                        "<ul>"
            )

            if (paymentInformation?.paypal == true) {
                sb.append(
                    "<li><strong>PayPal or Braintree.</strong> Their Privacy Policy can be viewed at" +
                            " <a href=\"https://www.paypal.com/webapps/mpp/ua/privacy-full\">https://www.paypal.com/webapps/mpp/ua/privacy-full</a>" +
                            " </li>"
                )
            }

            if (paymentInformation?.fastSpring == true) {
                sb.append(
                    "<li><strong>FastSpring.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\" http://fastspring.com/privacy/\">http://fastspring.com/privacy/</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.googlePay == true) {
                sb.append(
                    "<li><strong>Google Pay.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\" https://payments.google.com/payments/apis-secure/u/0/get_legal_document?ldo=0&ldt=privacynotice&ldl=en-GB\">https://policies.google.com/privacy</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.paytm == true) {
                sb.append(
                    "<li><strong>Paytm.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://pages.paytm.com/privacy.html\">https://pages.paytm.com/privacy.html</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.upi == true) {
                sb.append(
                    "<li><strong>UPI.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://www.bhimupi.org.in/privacy-policy\">https://www.bhimupi.org.in/privacy-policy</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.wePay == true) {
                sb.append(
                    "<li><strong>WePay.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://go.wepay.com/privacy-policy/\">https://go.wepay.com/privacy-policy/</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.worldPay == true) {
                sb.append(
                    "<li><strong>WorldPay.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://online.worldpay.com/terms/privacy\">https://online.worldpay.com/terms/privacy</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.sage == true) {
                sb.append(
                    "<li><strong>Sage Pay.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://www.opayo.co.uk/policies\">https://www.opayo.co.uk/policies</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.ccAvenue == true) {
                sb.append(
                    "<li><strong>CC Avenue.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://www.ccavenue.com/privacy.jsp\">https://www.ccavenue.com/privacy.jsp</a>" +
                            "</li>"
                )
            }

            if (paymentInformation?.razorPay == true) {
                sb.append(
                    "<li><strong>Razorpay.</strong> Their Privacy Policy can be viewed at " +
                            "<a href=\"https://razorpay.com/privacy/\">https://razorpay.com/privacy/</a>" +
                            "</li>"
                )
            }

            sb.append("</ul>")
        }
        return sb.toString()
    }

    private fun getChildPrivacyHtml(): String {
        val sb = StringBuilder()
        if (!collectingChildData) {
            sb.append(
                "<h2 class=pt-3 pb-3>Children\'s Privacy</h2> <p>Our Service does not address anyone under the age of 18 (Children).</p><p>We do not knowingly collect " +
                        "personally identifiable information from anyone under the age of 18. If you are a parent or guardian and you are aware that your Children has provided " +
                        "us with Personal Data, please contact us. If we become aware that we have collected Personal Data from children without verification of parental consent, " +
                        "we take steps to remove that information from our servers.</p><h2 class=pt-3 pb-3>Changes To This Privacy Policy</h2>" +
                        "<p>We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.</p>" +
                        "<p>We will let you know via email and/or a prominent notice on our Service, prior to the change becoming effective and update the effective date at the top of this Privacy Policy.</p>" +
                        "<p>You are advised to review this Privacy Policy periodically for any changes. Changes to this Privacy Policy are effective when they are posted on this page.</p>"
            )
        } else {
            sb.append(
                "<h2 class=pt-3 pb-3>Children\'s Privacy</h2> " +
                        "<p>Children can enjoy content and features on " + productName + " Services without directly providing contact information." +
                        "If we knowingly collect, use, or disclose contact information collected from a child, we will provide notice and obtain parental consent in accordance with applicable law." +
                        "We do not condition a child’s participation in an online activity on the child’s providing more contact information than is reasonably necessary to participate in that activity.</p>" +
                        "<p>Information collected from children will be disclosed in the same ways described in the disclosure of information section of our general Privacy Policy. " +
                        "Third parties who receive contact information collected by " + productName + " have agreed to maintain the confidentiality, security, and integrity of that information." +
                        " Parents have the option not to consent to the disclosure of their child’s contact information to third parties. Parents may also consent to " + productName + "’s collection and use " +
                        "of their child’s contact information while refusing to permit the disclosure of that information to third parties. Children who are at least 13," +
                        " but less than 16, years old have certain additional rights under the California Consumer Privacy Act.\n" +
                        "\n"
            )
        }
        return sb.toString()
    }

    private fun getAnalyticsHtml(): String {
        val sb = StringBuilder()
        if (usingAnalytics) {
            sb.append(
                "<h3 class=text-muted pt-2 pb-3>Analytics</h3>" +
                        "<p>We may use third-party Service Providers to monitor and analyze the use of our Service.</p><ul>"
            )
            if (analyticsInformation?.google == true || analyticsInformation?.firebase == true || analyticsInformation?.fabric == true) {
                sb.append(
                    "  <li><p><strong>Google Analytics</strong></p>" +
                            "<p>Google Analytics is a web analytics service offered by Google that tracks and reports website traffic." +
                            " Google uses the data collected to track and monitor the use of our Service. This data is shared " +
                            "with other Google services. Google may use the collected data to contextualize and personalize the" +
                            "ads of its own advertising network.</p>" +
                            "<p>You can opt-out of having made your activity on the Service available to Google Analytics by installing " +
                            "the Google Analytics opt-out browser add-on. The add-on prevents the Google Analytics JavaScript (ga.js," +
                            "analytics.js, and dc.js) from sharing information with Google Analytics about visits activity.</p>" +
                            "<p>For more information on the privacy practices of Google, " +
                            "please visit the Google Privacy &amp; Terms web page: <a href=http://www.google.com/intl/en/policies/privacy/>http://www.google.com/intl/en/policies/privacy/</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.facebook == true) {
                sb.append(
                    "  <li><p><strong>Facebook Analytics</strong></p>" +
                            "<p>Facebook provide analytics and insights tools for those wanting to track user interaction on their Facebook business page." +
                            " Facebook Insights can be seen by all the admins of your page, and it can provide a wealth of information about your content and audience..</p>" +
                            "<p>Facebook analytics tells you who your audience is and how they’re interacting with your page.</p>" +
                            "<p>For more information on the privacy practices of Facebook, " +
                            "please visit the Facebook Privacy &amp; Terms web page: <a href=https://analytics.facebook.com/>https://analytics.facebook.com/</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.appsflyer == true) {
                sb.append(
                    "  <li><p><strong>AppsFlyer</strong></p>" +
                            "<p>For more information on the privacy practices of AppsFlyer, " +
                            "please visit the AppsFlyer Privacy &amp; Terms web page: <a href=https://www.appsflyer.com/privacy-policy//>https://www.appsflyer.com/privacy-policy/</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.flurry == true) {
                sb.append(
                    "  <li><p><strong>Flurry</strong></p>" +
                            "<p>For more information on the privacy practices of Flurry, " +
                            "please visit the Flurry Privacy &amp; Terms web page: <a href=https://www.verizonmedia.com/policies/us/en/verizonmedia/privacy/index.html/>https://www.verizonmedia.com/policies/us/en/verizonmedia/privacy/index.html</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.mixpanel == true) {
                sb.append(
                    "  <li><p><strong>Mixpanel</strong></p>" +
                            "<p>For more information on the privacy practices of Mixpanel, " +
                            "please visit the Mixpanel Privacy &amp; Terms web page: <a href=https://mixpanel.com/legal/privacy-hub//>https://mixpanel.com/legal/privacy-hub/</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.gameAnalytics == true) {
                sb.append(
                    "  <li><p><strong>Game Analytics</strong></p>" +
                            "<p>For more information on the privacy practices of Game Analytics, " +
                            "please visit the Game Analytics Privacy &amp; Terms web page: <a href=https://gameanalytics.com/privacy/>https://gameanalytics.com/privacy/</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.amazon == true) {
                sb.append(
                    "  <li><p><strong>Amazon</strong></p>" +
                            "<p>For more information on the privacy practices of Amazon, " +
                            "please visit the Amazon Privacy &amp; Terms web page: <a href=https://aws.amazon.com/privacy/?nc1=f_pr/>https://aws.amazon.com/privacy/?nc1=f_pr</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.amplitude == true) {
                sb.append(
                    "  <li><p><strong>Amplitude</strong></p>" +
                            "<p>For more information on the privacy practices of Amplitude, " +
                            "please visit the Amplitude Privacy &amp; Terms web page: <a href=https://amplitude.com/privacy/>https://amplitude.com/privacy</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.countly == true) {
                sb.append(
                    "  <li><p><strong>Countly</strong></p>" +
                            "<p>For more information on the privacy practices of Countly, " +
                            "please visit the Countly Privacy &amp; Terms web page: <a href=https://count.ly/legal/privacy-policy/>https://count.ly/legal/privacy-policy</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.appsee == true) {
                sb.append(
                    "  <li><p><strong>Appsee</strong></p>" +
                            "<p>For more information on the privacy practices of Appsee, " +
                            "please visit the Appsee Privacy &amp; Terms web page: <a href=https://www.webanalyticsworld.net/privacy/>https://www.webanalyticsworld.net/privacy</a></p>" +
                            " </li>"
                )
            }
            if (analyticsInformation?.fathom == true) {
                sb.append(
                    "  <li><p><strong>Fathom</strong></p>" +
                            "<p>For more information on the privacy practices of Fathom, " +
                            "please visit the Fathom Privacy &amp; Terms web page: <a href=https://usefathom.com/privacy/>https://usefathom.com/privacy</a></p>" +
                            " </li>"
                )
            }

            sb.append("</ul>")
        }

        return sb.toString()
    }

    private fun getAdsHtml(): String {
        val sb = StringBuilder()
        if (showingAds) {
            sb.append(
                "<h3 class=text-muted pt-2 pb-3>Ad Service</h3>" +
                        "<p>We may use third-party Ad Service Providers to display ads.</p><ul>"
            )
            if (adsInformation?.googleAdmob == true) {
                sb.append(
                    "  <li><p><strong>Google Ad Mob or Ad Sense</strong></p>" +
                            "<p>For more information on the privacy practices of Google, " +
                            "please visit the Google Privacy &amp; Terms web page: <a href=http://www.google.com/intl/en/policies/privacy/>http://www.google.com/intl/en/policies/privacy/</a></p>" +
                            " </li>"
                )
            }
            if (adsInformation?.facebook == true) {
                sb.append(
                    "  <li><p><strong>Facebook Ads</strong></p>" +
                            "<p>For more information on the privacy practices of Facebook, " +
                            "please visit the Facebook Privacy &amp; Terms web page: <a href=https://analytics.facebook.com/>https://analytics.facebook.com/</a></p>" +
                            " </li>"
                )
            }
            if (adsInformation?.bing == true) {
                sb.append(
                    "  <li><p><strong>Bing</strong></p>" +
                            "<p>For more information on the privacy practices of Bing, " +
                            "please visit the Bing Privacy &amp; Terms web page: <a href=https://privacy.microsoft.com/en-us/PrivacyStatement/>hhttps://privacy.microsoft.com/en-us/PrivacyStatement</a></p>" +
                            " </li>"
                )
            }
            if (adsInformation?.flurry == true) {
                sb.append(
                    "  <li><p><strong>Flurry</strong></p>" +
                            "<p>For more information on the privacy practices of Flurry, " +
                            "please visit the Flurry Privacy &amp; Terms web page: <a href=https://www.verizonmedia.com/policies/us/en/verizonmedia/privacy/index.html/>https://www.verizonmedia.com/policies/us/en/verizonmedia/privacy/index.html</a></p>" +
                            " </li>"
                )
            }

            sb.append("</ul>")
        }

        return sb.toString()
    }

    fun build(): String {
        val startHtmlString = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<style>" + loadAssetTextAsString(
            context!!,
            "template_1_style.css"
        ) + "</style><script>" +
                loadAssetTextAsString(context, "template_1_script.js") + "</script></head><body>" +
                "<div id=\"wrap\">\n" +
                "    <div class=\"page-content pt-5 pb-5\">\n" +
                "        <div class=\"container\">\n" +
                "            <div class=\"row\">\n" +
                "\n" +
                "                <div class=\"col-lg-9 line-h-more order-1 order-lg-2\">\n" +
                "\n" +
                "                    <div class=\"shadow-sm rounded bg-white pt-5 pl-5 pr-5 pb-4\">\n" +
                "                        " + getHeader() +
                "                        <h2 class=\"pt-3 pb-3\">Your Privacy</h2>\n" +
                "                        <p>Our " + getTools() + " follows all legal requirements to protect your privacy. Our Privacy Policy is a legal statement that explains how we may collect information from you," +
                "                            how we may share your information, and how you can limit our sharing of your information. You will see terms in our Privacy Policy that are capitalized. " +
                "                           These terms have meanings as described in the Definitions section below.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Definitions</h2>\n" +
                "                        <ul>\n" +
                "                            <li>\n" +
                "                                <strong>Personal Data:</strong> Personal Data means data about a living individual who can be identified from those data (or from those and other information either in our possession or likely to come into our possession).\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>Usage Data:</strong> Usage Data is data collected automatically either generated by the use of the Service or from the Service infrastructure itself (for example, the duration of a page visit).\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>Cookies:</strong> Cookies are small pieces of data stored on a User's device.\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>Data Controller:</strong> Data Controller means a natural or legal person who (either alone or jointly or in common with other persons) determines the purposes for which and the manner in which any personal data are, or are to be, processed. For the purpose of this Privacy Policy, we are a Data Controller of your data.\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>Data Processors (or Service Providers):</strong> Data Processor (or Service Provider) means any natural or legal person who processes the data on behalf of the Data Controller. We may use the services of various Service Providers in order to process your data more effectively.\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>Data Subject:</strong> Data Subject is any living individual who is the subject of Personal Data.\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>User:</strong> The User is the individual using our Service. The User corresponds to the Data Subject, who is the subject of Personal Data.\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Information Collection And Use</h2>\n" +
                "                        <p>We collect several different types of information for various purposes to provide and improve our Service to you.</p>\n" +
                "                        <h3 class=\"text-muted pt-2 pb-3\">Types of Data Collected</h3>\n" +
                "                        <ul>\n" + getCollectingDataHtml() + "</ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Use of Data</h2>\n" +
                "                        <p>We use the collected data for various purposes:</p>\n" +
                "                        <ul>\n" +
                "                            <li>To provide and maintain our Service</li>\n" +
                "                            <li>To notify you about changes to our Service</li>\n" +
                "                            <li>To allow you to participate in interactive features of our Service when you choose to do so</li>\n" +
                "                            <li>To provide customer support</li>\n" +
                "                            <li>To gather analysis or valuable information so that we can improve our Service</li>\n" +
                "                            <li>To monitor the usage of our Service</li>\n" +
                "                            <li>To detect, prevent and address technical issues</li>\n" +
                "                            <li>To provide you with news, special offers and general information about other goods, services and events which we offer that are similar to those that you have already purchased or enquired about unless you have opted not to receive such information</li>\n" +
                "                        </ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Retention of Data</h2>\n" +
                "                        <p>We will retain your Personal Data only for as long as is necessary for the purposes set out in this Privacy Policy. We will retain and use your Personal Data to the extent necessary to comply with our legal obligations (for example, if we are required to retain your data to comply with applicable laws), resolve disputes, and enforce our legal agreements and policies.</p>\n" +
                "                        <p>We will also retain Usage Data for internal analysis purposes. Usage Data is generally retained for a shorter period of time, except when this data is used to strengthen the security or to improve the functionality of our Service, or we are legally obligated to retain this data for longer time periods.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Transfer Of Data</h2>\n" +
                "                        <p>Your information, including Personal Data, may be transferred to — and maintained on — computers located outside of your state, province, country or other governmental jurisdiction where the data protection laws may differ than those from your jurisdiction.</p>\n" +
                "                        <p>We will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this Privacy Policy and no transfer of your Personal Data will take place to an organization or a country unless there are adequate controls in place including the security of your data and other personal information.</p>\n" +
                "                        <p>Your consent to this Privacy Policy followed by your submission of such information represents your agreement to that transfer.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Disclosure Of Data</h2>\n" +
                "                        <ul>\n" +
                "                            <li><strong>Business Transaction.</strong> If we are involved in a merger, acquisition or asset sale, your Personal Data may be transferred. We will provide notice before your Personal Data is transferred and becomes subject to a different Privacy Policy.</li>\n" +
                "                            <li><strong>Disclosure for Law Enforcement.</strong> Under certain circumstances, we may be required to disclose your Personal Data if required to do so by law or in response to valid requests by public authorities (e.g. a court or a government agency).</li>\n" +
                "                        </ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Security Of Data</h2>\n" +
                "                        <p>The security of your data is important to us, but remember that no method of transmission over the Internet, or method of electronic storage is 100% secure. While we strive to use commercially acceptable means to protect your Personal Data, we cannot guarantee its absolute security.</p>\n" +
                "                         " + getGDPR() + "" + getCCPA() + "<h2 class=\"pt-3 pb-3\">Service Providers</h2>\n" +
                "                        <p>We may employ third party companies and individuals to facilitate our Service (\"Service Providers\"), to provide the Service on our behalf, to perform Service-related services or to assist us in analyzing how our Service is used.</p>\n" +
                "                        <p>These third parties have access to your Personal Data only to perform these tasks on our behalf and are obligated not to disclose or use it for any other purpose.</p>\n" +
                "                         " + getAnalyticsHtml() + "" + getAdsHtml() + "" + getPaymentHtml() + "<h2 class=\"pt-3 pb-3\">Links To Other Sites</h2>\n" +
                "                        <p>Our Service may contain links to other sites that are not operated by us. If you click on a third party link, you will be directed to that third party's site. We strongly advise you to review the Privacy Policy of every site you visit.</p>\n" +
                "                        <p>We have no control over and assume no responsibility for the content, " +
                "                         privacy policies or practices of any third party sites or services.</p>" +
                "                           " + getChildPrivacyHtml() + "<h2 class=\"pt-3 pb-3\">Contact Us</h2>\n" +
                "                        <p>If you have any questions about this Privacy Policy, please contact us by using the contact information .</p>\n" + getContact() + "<br><div></div></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div></div>"

        val endHtmlString = "</body></html>"

        return startHtmlString + "\n" + endHtmlString
    }


}
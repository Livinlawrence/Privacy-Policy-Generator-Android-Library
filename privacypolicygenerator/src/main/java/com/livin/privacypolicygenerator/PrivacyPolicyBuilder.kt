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
    val isWeb: Boolean = false
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

    fun generate(): String {
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
                "                        <h2 class=\"pb-3\">Privacy Policy</h2><p>Our Privacy Policy was posted on " + effectiveDate + ". It governs the privacy terms of our " + getHeaderOwner() + ", and the tools we provide you (the " + getTools() + " or the \"Service\"). Any capitalized terms not defined in our Privacy Policy, have the meaning as specified in our Terms.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Your Privacy</h2>\n" +
                "                        <p>Our Website follows all legal requirements to protect your privacy. Our Privacy Policy is a legal statement that explains how we may collect information from you, how we may share your information, and how you can limit our sharing of your information. You will see terms in our Privacy Policy that are capitalized. These terms have meanings as described in the Definitions section below.</p>\n" +
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
                "                        <ul>\n" +
                "                            <li>\n" +
                "                                <p><strong>Personal Data</strong></p>\n" +
                "                                <p>While using our Service, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you (\"Personal Data\"). Personally identifiable information may include, but is not limited to: Email address, Name, Address, State, Province, ZIP/Postal code, City, Cookies and Usage Data.</p>\n" +
                "                                <p>We may use your Personal Data to contact you with newsletters, marketing or promotional materials and other information that may be of interest to you. You may opt out of receiving any, or all, of these communications from us by following the unsubscribe link or instructions provided in any email we send.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>Usage Data</strong>\n" +
                "                                </p><p>We may also collect information how the Service is accessed and used (\"Usage Data\"). This Usage Data may include information such as your computer's Internet Protocol address (e.g. IP address), browser type, browser version, the pages of our Service that you visit, the time and date of your visit, the time spent on those pages, unique device identifiers and other diagnostic data.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>Tracking &amp; Cookies Data</strong></p>\n" +
                "                                <p>We use cookies and similar tracking technologies to track the activity on our Service and hold certain information.</p>\n" +
                "                                <p>Cookies are files with small amount of data which may include an anonymous unique identifier. Cookies are sent to your browser from a website and stored on your device. Tracking technologies also used are beacons, tags, and scripts to collect and track information and to improve and analyze our Service.</p>\n" +
                "                                <p>You can instruct your browser to refuse all cookies or to indicate when a cookie is being sent. However, if you do not accept cookies, you may not be able to use some portions of our Service.</p>\n" +
                "                                <p>Examples of Cookies we use: <strong>session cookies</strong> (we use these cookies to operate our Service) and <strong>preference cookies</strong> (we use these cookies to remember your preferences and various settings).</p>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
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
                "                        <h2 class=\"pt-3 pb-3\">Legal Basis for Processing Personal Data Under General Data Protection Regulation (GDPR)</h2>\n" +
                "                        <p>If you are from the European Economic Area (EEA), TermsFeed legal basis for collecting and using the personal information described in this Privacy Policy depends on the Personal Data we collect and the specific context in which we collect it.</p>\n" +
                "                        <p>TermsFeed may process your Personal Data because:</p>\n" +
                "                        <ul>\n" +
                "                            <li>We need to perform a contract with you</li>\n" +
                "                            <li>You have given us permission to do so</li>\n" +
                "                            <li>The processing is in our legitimate interests and it's not overridden by your rights</li>\n" +
                "                            <li>For payment processing purposes</li>\n" +
                "                            <li>To comply with the law</li>\n" +
                "                        </ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Your Data Protection Rights Under General Data Protection Regulation (GDPR)</h2>\n" +
                "                        <p>If you are a resident of the European Economic Area (EEA), you have certain data protection rights. TermsFeed aims to take reasonable steps to allow you to correct, amend, delete, or limit the use of your Personal Data.</p>\n" +
                "                        <p>If you wish to be informed what Personal Data we hold about you and if you want it to be removed from our systems, please contact us.</p>\n" +
                "                        <p>In certain circumstances, you have the following data protection rights:</p>\n" +
                "                        <ul>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right to access, update or to delete</strong> the information we have on you.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right of rectification.</strong> You have the right to have your information rectified if that information is inaccurate or incomplete.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right to object.</strong> You have the right to object to our processing of your Personal Data.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right of restriction.</strong> You have the right to request that we restrict the processing of your personal information.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right to data portability.</strong> You have the right to be provided with a copy of your Personal Data in a structured, machine-readable and commonly used format.</p>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <p><strong>The right to withdraw consent.</strong> You also have the right to withdraw your consent at any time where TermsFeed relied on your consent to process your personal information.</p>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                        <p>Please note that we may ask you to verify your identity before responding to such requests.</p>\n" +
                "                        <p>You have the right to complain to a Data Protection Authority about our collection and use of your Personal Data. For more information, please contact your local data protection authority in the European Economic Area (EEA).</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">\"Do Not Sell My Personal Information\" Notice for California consumers under California Consumer Privacy Act (CCPA)</h2>\n" +
                "                        <p>Under the CCPA, California consumers have the right to:</p>\n" +
                "                        <ul>\n" +
                "                            <li>Request that a business that collects a consumer's personal data disclose the categories and specific pieces of personal data that a business has collected about consumers.</li>\n" +
                "                            <li>Request that a business delete any personal data about the consumer that a business has collected.</li>\n" +
                "                            <li>Request that a business that sells a consumer's personal data, not sell the consumer's personal data.</li>\n" +
                "                        </ul>\n" +
                "                        <p>If you make a request, we have 30 days to respond to you. If you would like to exercise any of these rights, please contact us.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Service Providers</h2>\n" +
                "                        <p>We may employ third party companies and individuals to facilitate our Service (\"Service Providers\"), to provide the Service on our behalf, to perform Service-related services or to assist us in analyzing how our Service is used.</p>\n" +
                "                        <p>These third parties have access to your Personal Data only to perform these tasks on our behalf and are obligated not to disclose or use it for any other purpose.</p>\n" +
                "                        <h3 class=\"text-muted pt-2 pb-3\">Analytics</h3>\n" +
                "                        <p>We may use third-party Service Providers to monitor and analyze the use of our Service.</p>\n" +
                "                        <ul>\n" +
                "                            <li>\n" +
                "                                <p><strong>Google Analytics</strong></p>\n" +
                "                                <p>Google Analytics is a web analytics service offered by Google that tracks and reports website traffic. Google uses the data collected to track and monitor the use of our Service. This data is shared with other Google services. Google may use the collected data to contextualize and personalize the ads of its own advertising network.</p>\n" +
                "                                <p>You can opt-out of having made your activity on the Service available to Google Analytics by installing the Google Analytics opt-out browser add-on. The add-on prevents the Google Analytics JavaScript (ga.js, analytics.js, and dc.js) from sharing information with Google Analytics about visits activity.</p>\n" +
                "                                <p>For more information on the privacy practices of Google, please visit the Google Privacy &amp; Terms web page: <a href=\"http://www.google.com/intl/en/policies/privacy/\">http://www.google.com/intl/en/policies/privacy/</a></p>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                        <h3 class=\"text-muted pt-2 pb-3\">Payments</h3>\n" +
                "                        <p>We may provide paid products and/or services within the Service. In that case, we use third-party services for payment processing (e.g. payment processors).</p>\n" +
                "                        <p>We will not store or collect your payment card details. That information is provided directly to our third-party payment processors whose use of your personal information is governed by their Privacy Policy. These payment processors adhere to the standards set by PCI-DSS as managed by the PCI Security Standards Council, which is a joint effort of brands like Visa, Mastercard, American Express and Discover. PCI-DSS requirements help ensure the secure handling of payment information.</p>\n" +
                "                        <p>The payment processors we work with are:</p>\n" +
                "                        <ul>\n" +
                "                            <li>\n" +
                "                                <strong>PayPal or Braintree.</strong> Their Privacy Policy can be viewed at <a href=\"https://www.paypal.com/webapps/mpp/ua/privacy-full\">https://www.paypal.com/webapps/mpp/ua/privacy-full</a>\n" +
                "                            </li>\n" +
                "                            <li>\n" +
                "                                <strong>FastSpring.</strong> Their Privacy Policy can be viewed at <a href=\" http://fastspring.com/privacy/\">http://fastspring.com/privacy/</a>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Links To Other Sites</h2>\n" +
                "                        <p>Our Service may contain links to other sites that are not operated by us. If you click on a third party link, you will be directed to that third party's site. We strongly advise you to review the Privacy Policy of every site you visit.</p>\n" +
                "                        <p>We have no control over and assume no responsibility for the content, privacy policies or practices of any third party sites or services.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Children's Privacy</h2>\n" +
                "                        <p>Our Service does not address anyone under the age of 18 (\"Children\").</p>\n" +
                "                        <p>We do not knowingly collect personally identifiable information from anyone under the age of 18. If you are a parent or guardian and you are aware that your Children has provided us with Personal Data, please contact us. If we become aware that we have collected Personal Data from children without verification of parental consent, we take steps to remove that information from our servers.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Changes To This Privacy Policy</h2>\n" +
                "                        <p>We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.</p>\n" +
                "                        <p>We will let you know via email and/or a prominent notice on our Service, prior to the change becoming effective and update the \"effective date\" at the top of this Privacy Policy.</p>\n" +
                "                        <p>You are advised to review this Privacy Policy periodically for any changes. Changes to this Privacy Policy are effective when they are posted on this page.</p>\n" +
                "                        <h2 class=\"pt-3 pb-3\">Contact Us</h2>\n" +
                "                        <p>If you have any questions about this Privacy Policy, please contact us by using the contact information <a href=\"/contact\">we provided on our Contact page</a>.</p>\n" +
                "                    </div>\n" +
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

    val html = "<!DOCTYPE html>\n" +
            "    <html>\n" +
            "    <head>\n" +
            "      <meta charset='utf-8'>\n" +
            "      <meta name='viewport' content='width=device-width'>\n" +
            "      <title>Privacy Policy</title>\n" +
            "      <style> body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; padding:1em; } </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "    <strong>Privacy Policy</strong> <p>\n" +
            "                  Livin built the ssPlease provide an App Name! app as\n" +
            "                  a Free app. This SERVICE is provided by\n" +
            "                  Livin at no cost and is intended for use as\n" +
            "                  is.\n" +
            "                </p> <p>\n" +
            "                  This page is used to inform visitors regarding my\n" +
            "                  policies with the collection, use, and disclosure of Personal\n" +
            "                  Information if anyone decided to use my Service.\n" +
            "                </p> <p>\n" +
            "                  If you choose to use my Service, then you agree to\n" +
            "                  the collection and use of information in relation to this\n" +
            "                  policy. The Personal Information that I collect is\n" +
            "                  used for providing and improving the Service. I will not use or share your information with\n" +
            "                  anyone except as described in this Privacy Policy.\n" +
            "                </p> <p>\n" +
            "                  The terms used in this Privacy Policy have the same meanings\n" +
            "                  as in our Terms and Conditions, which is accessible at\n" +
            "                  ssPlease provide an App Name! unless otherwise defined in this Privacy Policy.\n" +
            "                </p> <p><strong>Information Collection and Use</strong></p> <p>\n" +
            "                  For a better experience, while using our Service, I\n" +
            "                  may require you to provide us with certain personally\n" +
            "                  identifiable information, including but not limited to ss. The information that\n" +
            "                  I request will be retained on your device and is not collected by me in any way.\n" +
            "                </p> <div><p>\n" +
            "                    The app does use third party services that may collect\n" +
            "                    information used to identify you.\n" +
            "                  </p> <p>\n" +
            "                    Link to privacy policy of third party service providers used\n" +
            "                    by the app\n" +
            "                  </p> <ul><li><a href=\"https://www.google.com/policies/privacy/\" target=\"_blank\" rel=\"noopener noreferrer\">Google Play Services</a></li><!----><li><a href=\"https://firebase.google.com/policies/analytics\" target=\"_blank\" rel=\"noopener noreferrer\">Google Analytics for Firebase</a></li><li><a href=\"https://firebase.google.com/support/privacy/\" target=\"_blank\" rel=\"noopener noreferrer\">Firebase Crashlytics</a></li><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----></ul></div> <p><strong>Log Data</strong></p> <p>\n" +
            "                  I want to inform you that whenever you\n" +
            "                  use my Service, in a case of an error in the app\n" +
            "                  I collect data and information (through third party\n" +
            "                  products) on your phone called Log Data. This Log Data may\n" +
            "                  include information such as your device Internet Protocol\n" +
            "                  (“IP”) address, device name, operating system version, the\n" +
            "                  configuration of the app when utilizing my Service,\n" +
            "                  the time and date of your use of the Service, and other\n" +
            "                  statistics.\n" +
            "                </p> <p><strong>Cookies</strong></p> <p>\n" +
            "                  Cookies are files with a small amount of data that are\n" +
            "                  commonly used as anonymous unique identifiers. These are sent\n" +
            "                  to your browser from the websites that you visit and are\n" +
            "                  stored on your device's internal memory.\n" +
            "                </p> <p>\n" +
            "                  This Service does not use these “cookies” explicitly. However,\n" +
            "                  the app may use third party code and libraries that use\n" +
            "                  “cookies” to collect information and improve their services.\n" +
            "                  You have the option to either accept or refuse these cookies\n" +
            "                  and know when a cookie is being sent to your device. If you\n" +
            "                  choose to refuse our cookies, you may not be able to use some\n" +
            "                  portions of this Service.\n" +
            "                </p> <p><strong>Service Providers</strong></p> <p>\n" +
            "                  I may employ third-party companies and\n" +
            "                  individuals due to the following reasons:\n" +
            "                </p> <ul><li>To facilitate our Service;</li> <li>To provide the Service on our behalf;</li> <li>To perform Service-related services; or</li> <li>To assist us in analyzing how our Service is used.</li></ul> <p>\n" +
            "                  I want to inform users of this Service\n" +
            "                  that these third parties have access to your Personal\n" +
            "                  Information. The reason is to perform the tasks assigned to\n" +
            "                  them on our behalf. However, they are obligated not to\n" +
            "                  disclose or use the information for any other purpose.\n" +
            "                </p> <p><strong>Security</strong></p> <p>\n" +
            "                  I value your trust in providing us your\n" +
            "                  Personal Information, thus we are striving to use commercially\n" +
            "                  acceptable means of protecting it. But remember that no method\n" +
            "                  of transmission over the internet, or method of electronic\n" +
            "                  storage is 100% secure and reliable, and I cannot\n" +
            "                  guarantee its absolute security.\n" +
            "                </p> <p><strong>Links to Other Sites</strong></p> <p>\n" +
            "                  This Service may contain links to other sites. If you click on\n" +
            "                  a third-party link, you will be directed to that site. Note\n" +
            "                  that these external sites are not operated by me.\n" +
            "                  Therefore, I strongly advise you to review the\n" +
            "                  Privacy Policy of these websites. I have\n" +
            "                  no control over and assume no responsibility for the content,\n" +
            "                  privacy policies, or practices of any third-party sites or\n" +
            "                  services.\n" +
            "                </p> <p><strong>Children’s Privacy</strong></p> <p>\n" +
            "                  These Services do not address anyone under the age of 13.\n" +
            "                  I do not knowingly collect personally\n" +
            "                  identifiable information from children under 13. In the case\n" +
            "                  I discover that a child under 13 has provided\n" +
            "                  me with personal information, I immediately\n" +
            "                  delete this from our servers. If you are a parent or guardian\n" +
            "                  and you are aware that your child has provided us with\n" +
            "                  personal information, please contact me so that\n" +
            "                  I will be able to do necessary actions.\n" +
            "                </p> <p><strong>Changes to This Privacy Policy</strong></p> <p>\n" +
            "                  I may update our Privacy Policy from\n" +
            "                  time to time. Thus, you are advised to review this page\n" +
            "                  periodically for any changes. I will\n" +
            "                  notify you of any changes by posting the new Privacy Policy on\n" +
            "                  this page.\n" +
            "                </p> <p>This policy is effective as of 2021-01-25</p> <p><strong>Contact Us</strong></p> <p>\n" +
            "                  If you have any questions or suggestions about my\n" +
            "                  Privacy Policy, do not hesitate to contact me at ss@mail.com.\n" +
            "                </p> <p>This privacy policy page was created at <a href=\"https://privacypolicytemplate.net\" target=\"_blank\" rel=\"noopener noreferrer\">privacypolicytemplate.net </a>and modified/generated by <a href=\"https://app-privacy-policy-generator.nisrulz.com/\" target=\"_blank\" rel=\"noopener noreferrer\">App Privacy Policy Generator</a></p>\n" +
            "    </body>\n" +
            "    </html>\n" +
            "      "
}
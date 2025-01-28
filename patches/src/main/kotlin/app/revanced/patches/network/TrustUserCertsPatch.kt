package app.revanced.patches.network

import app.revanced.patcher.patch.resourcePatch
import org.w3c.dom.Element
import java.io.File

internal fun String.trimIndentMultiline() =
    this.split("\n")
        .joinToString("\n") { it.trimIndent() } // Remove the leading whitespace from each line.
        .trimIndent() // Remove the leading newline.

@Suppress("unused")
val trustUserCertsPatch = resourcePatch(
    name = "Trust User Certs",
    description = "This patch enables an app to trust user-added CAs",
) {
    execute {
        // Based on 
        // https://github.com/ReVanced/revanced-patches/blob/main/patches/src/main/kotlin/app/revanced/patches/all/misc/network/OverrideCertificatePinningPatch.kt
        // without opting into cleartext traffic, bypassing certificate pinning and enabling debugging
        val resXmlDirectory = get("res/xml")

        // Add android:networkSecurityConfig="@xml/network_security_config" and the "networkSecurityConfig" attribute if it does not exist.
        document("AndroidManifest.xml").use { document ->
            val applicationNode = document.getElementsByTagName("application").item(0) as Element

            if (!applicationNode.hasAttribute("networkSecurityConfig")) {
                document.createAttribute("android:networkSecurityConfig")
                    .apply { value = "@xml/network_security_config" }.let(applicationNode.attributes::setNamedItem)
            }
        }

        // In case the file does not exist create the "network_security_config.xml" file.
        File(resXmlDirectory, "network_security_config.xml").apply {
            writeText(
                """
                    <?xml version="1.0" encoding="utf-8"?>
                    <network-security-config>
                        <base-config>
                            <trust-anchors>
                                <certificates src="system" />
                                <certificates src="user" />
                            </trust-anchors>
                        </base-config>
                    </network-security-config>
                    """.trimIndentMultiline(),
            )
        }
    }
}

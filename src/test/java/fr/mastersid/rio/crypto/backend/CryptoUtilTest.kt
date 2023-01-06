package fr.mastersid.rio.crypto.backend

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CryptoUtilTest {

    @Test
    fun `cesar() returns string from a to z`() {
        val cryptoUtil = CryptoUtilImpl()
        val result = cryptoUtil.cesar("abcdefghijklmnopqrstuvwxyz")
        assertThat(result).isEqualTo("nopqrstuvwxyzabcdefghijklm")
    }

    @Test
    fun `cesar() returns string from A to Z`() {
        val cryptoUtil = CryptoUtilImpl()
        val result = cryptoUtil.cesar("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        assertThat(result).isEqualTo("NOPQRSTUVWXYZABCDEFGHIJKLM")
    }

    @Test
    fun `cesar() returns space`() {
        val cryptoUtil = CryptoUtilImpl()
        val result = cryptoUtil.cesar(" ")
        assertThat(result).isEqualTo(" ")
    }

    @Test
    fun `cesar() Should Throw Illegal Argument Exception for !`() {
        val cryptoUtil = CryptoUtilImpl()
        var result = ""
        try {
            result = cryptoUtil.cesar("!")
        } catch (e: java.lang.IllegalArgumentException) {

        }
        assertThat(result).isEqualTo("")
    }
}
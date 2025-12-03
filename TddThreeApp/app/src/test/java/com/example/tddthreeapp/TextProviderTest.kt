package com.example.tddthreeapp

import android.content.Context
import androidx.test.filters.SmallTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@SmallTest
class TextProviderTest {
    private val context: Context = mock()
    private val textProvider = TextProvider(context)

    @Test
    fun `getTextForButton returns correct text`() {
        // Assume string resource ID is 100 for simplicity in unit test
        whenever(context.getString(R.string.btn_label)).thenReturn("Next")
        assertEquals("Next", textProvider.getTextForButton())
    }
}
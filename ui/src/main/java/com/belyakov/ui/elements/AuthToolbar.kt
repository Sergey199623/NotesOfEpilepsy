package com.belyakov.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.noRippleClickable
import com.belyakov.notesforepilepsy.R

@Composable
fun AuthToolbar(
    onBack: (() -> Unit)? = null,
    onClose: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .noRippleClickable { onBack() },
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .noRippleClickable { onClose() },
            painter = painterResource(R.drawable.ic_cross_close),
            contentDescription = null
        )
    }
}
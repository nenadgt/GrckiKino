package com.grckikino.ui.compose.round

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grckikino.R
import com.grckikino.viewModels.round.QuotesViewModel

@Composable
fun QuotesView(viewModel: QuotesViewModel) {
    val quotePairs = viewModel.quotePairs

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(colorResource(id = R.color.qouta_header)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            quotePairs.forEach { pair ->
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    text = pair.first,
                    color = colorResource(id = R.color.round_text_color),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(colorResource(id = R.color.qouta_header)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            quotePairs.forEach { pair ->
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    text = pair.second,
                    color = colorResource(id = R.color.round_text_color),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }
    }
}
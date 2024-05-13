package com.grckikino.ui.compose.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grckikino.R
import com.grckikino.data.models.round.Round
import com.grckikino.helpers.DateTimeFormatHelper
import java.time.Instant

@Composable
fun RoundCard(round: Round, onClick: (drawId: Int) -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(id = R.color.background_color))
        .height(60.dp)
        .clickable { onClick(round.drawId) }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_color))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
                    .align(Alignment.CenterStart),
                text = DateTimeFormatHelper.format(Instant.ofEpochMilli(round.drawTime), "HH:mm"),
                fontSize = 18.sp,
                color = colorResource(id = R.color.text_color)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
                    .align(Alignment.CenterEnd),
                text = DateTimeFormatHelper.format(
                    Instant.ofEpochMilli(System.currentTimeMillis() - round.drawTime),
                    "HH:mm"
                ),
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                color = colorResource(id = R.color.text_color)
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.spacer_color))
            )
        }
    }
}
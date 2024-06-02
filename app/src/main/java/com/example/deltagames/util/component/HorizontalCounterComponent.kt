package com.example.deltagames.util.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.R


@Composable
fun HorizontalCounterComponent(finalValue: Int, valueMax: Int, onValueChange: (Int)-> Unit) {
    var counter by remember { mutableStateOf(finalValue) }
    Card(
        modifier = Modifier.padding(horizontal = 8.dp),
        elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { if (counter > 1){
                    counter--
                    onValueChange(counter)
                }
                          },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .height(38.dp)
                    .width(38.dp)
                    .padding(2.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
            ) {
                Text(
                    text = "-",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = counter.toString(),
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (counter < valueMax){
                        counter++
                        onValueChange(counter)
                    }
                          },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .height(38.dp)
                    .width(38.dp)
                    .padding(2.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
            ) {
                Text(
                    text = "+",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}


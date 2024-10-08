package com.camila.search.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camila.search.data.OptionData

@Composable
fun CardComponent(option: OptionData, isSelected: Boolean, onSelected: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onSelected()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = option.name,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Justify
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Opção selecionada",
                    tint = Color.Black,
                    modifier = Modifier
                        .background(
                            Color.Green.copy(alpha = 1f),
                            RoundedCornerShape(50)
                        )
                        .size(25.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(50))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockItemPreview() {
    CardComponent(
        option = OptionData(id = "1", name = "Opção 1"),
        isSelected = true,
        onSelected = { true }
    )
}
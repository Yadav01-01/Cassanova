package com.bussiness.cassanova.ui.screen.main

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.TextWhite
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


private val DarkBackground = Color(0xFF000000)
private val TextGray = Color(0xFF808080)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(): LocalDate? {
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black)
                .border(1.dp, TextAAColor, RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            CalendarView(
                currentMonth = currentMonth.value,
                selectedDate = selectedDate.value,
                onDateSelected = { date ->
                    selectedDate.value = date

                    Toast.makeText(context, "Selected: $date", Toast.LENGTH_SHORT).show()
                },
                onMonthChanged = { newMonth ->
                    currentMonth.value = newMonth
                }
            )
        }
    }
    return selectedDate.value
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    currentMonth: YearMonth,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChanged: (YearMonth) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MonthHeader(currentMonth, onMonthChanged)
        DayNamesRow()
        WeeksGrid(currentMonth, selectedDate, onDateSelected)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(
    month: YearMonth,
    onMonthChanged: (YearMonth) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, bottom = 20.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_previous_icon_arrow),
            contentDescription = "Previous Month",
            modifier = Modifier
                .size(13.dp)
                .clickable {
                    onMonthChanged(month.minusMonths(1))
                }
        )

        Text(
            text = "${
                month.month.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            } ${month.year}",
            color = TextWhite,
            fontSize = 19.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_bold))
        )

        Image(
            painter = painterResource(id = R.drawable.ic_next_icon_arrow),
            contentDescription = "Next Month",
            modifier = Modifier
                .size(13.dp)
                .clickable {
                    onMonthChanged(month.plusMonths(1))
                }
        )
    }
}

@Composable
private fun DayNamesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Standard calendar starts with Sunday
        //listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa").forEach { day ->
            listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su").forEach { day ->

            Text(
                text = day,
                color = TextWhite,
                fontSize = 19.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeeksGrid(
    month: YearMonth,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val weeks = remember(month) { generateCalendarWeeks(month) }

    Column(modifier = Modifier.fillMaxWidth()) {
        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { date ->
                    val isPast = date?.isBefore(LocalDate.now()) == true
                    DayCell(
                        date = date,
                        isCurrentMonth = date?.month == month.month,
                        isSelected = date == selectedDate,
                        isToday = date == LocalDate.now(),
                        isEnabled = !isPast,
                        onClick = { if (!isPast && date != null) onDateSelected(date) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DayCell(
    date: LocalDate?,
    isCurrentMonth: Boolean,
    isSelected: Boolean,
    isToday: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp)
            .clip(CircleShape)
            .clickable(
                enabled = date != null && isEnabled,
                onClick = onClick
            )
            .then(
                when {
                    isSelected -> {
                        Modifier.background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFC7A65E),
                                    Color(0xFFFBE29A),
                                    Color(0xFFBE9B43)
                                )
                            )
                        )
                    }
                    isToday && isCurrentMonth -> {
                        Modifier
                            .background(Color.Transparent)
                            .border(2.dp, Color(0xFFC7A65E), CircleShape)
                    }
                    else -> {
                        Modifier.background(Color.Transparent)
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (date != null) {
            Text(
                text = date.dayOfMonth.toString(),
                color = when {
                    !isEnabled -> Color(0xFF808080)
                    isSelected -> Color.Black
                    isToday && isCurrentMonth -> Color(0xFFC7A65E)
                    isCurrentMonth -> TextWhite
                    else -> TextGray
                },
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                fontWeight = if (isToday && isCurrentMonth) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun generateCalendarWeeks(yearMonth: YearMonth): List<List<LocalDate?>> {
    val weeks = mutableListOf<List<LocalDate?>>()
    var week = mutableListOf<LocalDate?>()

    val firstDayOfMonth = yearMonth.atDay(1)
    val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value // 1 (Monday) to 7 (Sunday)
    val daysToAddBefore = if (dayOfWeekValue == 7) 6 else dayOfWeekValue - 1

    val previousMonth = yearMonth.minusMonths(1)
    val daysInPreviousMonth = previousMonth.lengthOfMonth()

    // Add days from the previous month
    for (i in (daysInPreviousMonth - daysToAddBefore + 1)..daysInPreviousMonth) {
        week.add(previousMonth.atDay(i))
    }

    // Add current month's days
    for (day in 1..yearMonth.lengthOfMonth()) {
        week.add(yearMonth.atDay(day))
        if (week.size == 7) {
            weeks.add(week)
            week = mutableListOf()
        }
    }

    // Add next month's days to fill the last week
    val nextMonth = yearMonth.plusMonths(1)
    var nextMonthDay = 1
    while (week.size < 7 && week.isNotEmpty()) {
        week.add(nextMonth.atDay(nextMonthDay))
        nextMonthDay++
    }
    if (week.isNotEmpty()) {
        weeks.add(week)
    }

    return weeks
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, backgroundColor = 0xFF1A1A1A)
@Composable
fun CalendarScreenPreview() {
    MaterialTheme {
        CalendarScreen()
    }
}

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bussiness.cassanova.model.BookingItem
import com.bussiness.cassanova.ui.component.BookingCard
import com.bussiness.cassanova.ui.component.BottomSheetDialog
import com.bussiness.cassanova.ui.component.BottomSheetDialogProperties
import com.bussiness.cassanova.ui.component.sheet.CancellationReasonSheet
import com.bussiness.cassanova.ui.component.sheet.EventInterestSheet

@Composable
fun UpcomingBookingsScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
   // var selectedReason by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedReason by remember { mutableStateOf("Select Reason*") }
    val reasons = listOf(
        "Booking Made by Mistake",
        "Plans Postponed",
        "Unable to Reach Venue",
        "Unexpected Emergency",
        "Other"
    )

    val bookingList = listOf(
        BookingItem(
            tableId = "#A21",
            title = "Table ID - #A21",
            time = "Monday, 2 June – 10:00 AM",
            guestCount = "5",
            specialRequest = "Birthday Party",
            message = "We’ve locked in your spot. See You Soon!",
            highlightText = "See You Soon!",
            status = "Reserved"
        ),
        BookingItem(
            tableId = null,
            title = "Request Received",
            time = "Monday, 2 June – 10:00 AM",
            guestCount = "5",
            specialRequest = "Other",
            message = "You’ll receive a Confirmation shortly.",
            highlightText = "Confirmation",
            status = "In Process"
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Blur when showBottomSheet is true
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .blur(radius = if (showBottomSheet) 16.dp else 0.dp)
        ) {
            LazyColumn {
                items(bookingList) { booking ->
                    BookingCard(
                        booking = booking,
                        onStatusClick = {},
                        onCancelClick = {
                            showBottomSheet = true
                        }
                    )
                }
            }
        }

        // Dialog is shown conditionally and overlays the content
        if (showBottomSheet) {
            BottomSheetDialog(
                onDismissRequest = {
                    Log.d("[BottomSheetDialog]", "onDismissRequest")
                    showBottomSheet = false
                },
                properties = BottomSheetDialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false,
                    dismissWithAnimation = true,
                    enableEdgeToEdge = false,
                )
            ) {
                CancellationReasonSheet(
                    selectedReason = selectedReason,
                    description = description,
                    onReasonChange = { selectedReason = it },
                    onDescriptionChange = { description = it },
                    onDismiss = {
                        showBottomSheet = false
                        selectedReason = ""
                        description = ""
                    },
                    onSubmitClick = {
                        showBottomSheet = false
                        // handle cancel submit logic
                    },
                    reasons = reasons
                )
            }

//            CancellationReasonSheet(
//                selectedReason = selectedReason,
//                description = description,
//                onReasonChange = { selectedReason = it },
//                onDescriptionChange = { description = it },
//                onDismiss = {
//                    showBottomSheet = false
//                    selectedReason = ""
//                    description = ""
//                },
//                onSubmitClick = {
//                    showBottomSheet = false
//                    // handle cancel submit logic
//                }
//            )
        }
    }
}
